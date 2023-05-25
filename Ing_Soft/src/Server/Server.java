package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.JFrame;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

class Server{
    private Socket currenSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private  Connection con=null;
    private JFrame frame;
    private byte[] Byte=null;
    public Server(){
        frame = new JFrame(); // GUI gui = new GUI() as well
        // default value JFrame.HIDE_ON_CLOSE
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true);
        System.out.println("Starting Server...");
        setconnection("jdbc:mysql://localhost:3306/ingsoft","Ing_s", "Ing_s");
        if (con != null) {
            System.out.println("Conexion established");
        }
        try {
            ServerSocket serverSocket = new ServerSocket(48816);
            Boolean on=true;
            while(on){
                currenSocket=serverSocket.accept();
                in=new DataInputStream(currenSocket.getInputStream());
                out=new DataOutputStream(currenSocket.getOutputStream());
                out.writeUTF(request());
                currenSocket.close();
            }
            serverSocket.close();
        } catch (IOException exception) {
            System.err.println(exception.toString());
            exception.printStackTrace();
        }
    }
    private String request(){
        String query;
        try {
            query=in.readUTF();
            int caso=0;
            ArrayList<String> arrayList=parse(query);
            if(arrayList.size()>0){
                String num=arrayList.get(0);
                caso=Integer.parseInt(num);
            }
            switch (caso){
                //Conexion al servidor
                case 0:
                    if(arrayList.size()!=3)return "Error de sentencia";
                    String email=arrayList.get(1);
                    String password=arrayList.get(2); 
                    query = "Select Password from user where Email = '"+email+"';";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        ResultSet rs = stmt.executeQuery(query);
                        String pass ="";
                        while (rs.next()) {
                            pass=rs.getString("Password");
                        }
                        if(pass.equals(password)){
                            return "Afirmativo";
                        }else{
                            return "Contraseña incorrecta";
                        }
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Error";
                    }
                case 1:
                    if(arrayList.size()!=6){
                        return "Error de sentencia";
                    }
                    try (Statement stmt = (Statement) con.createStatement()) {
                        query="select count(UserID) from user join profile on UserId=ProfileID where (Email ='"+arrayList.get(3)+"' or Username='"+arrayList.get(4)+"');";
                        ResultSet rs = stmt.executeQuery(query);
                        int num=-1;
                        while (rs.next()) {
                           num=rs.getInt("count(UserID)");
                        }
                        if(num==0){
                            query="insert into `profile`(Username, Description, Admin) values ('"+arrayList.get(4)+"','No hay descripción',0);";
                            stmt.execute(query);
                            query="select max(ProfileID) from profile;";
                            rs=stmt.executeQuery(query);
                            while (rs.next()) {
                                num=rs.getInt("max(ProfileID)");
                            }
                            query="insert into `user`(UserID, Email, Name,Surname,Password) values ("+num+",'"+arrayList.get(3)+"','"+arrayList.get(1)+"','"+arrayList.get(2)+"','"+arrayList.get(5)+"');";
                            stmt.execute(query);
                            return "Afirmativo";
                        }else{
                            return "Este usuario ya existe";
                        }
                        
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Error";
                    }
                case 2:
                    if(arrayList.size()!=2)return "Error de sentencia";
                    query = "select UserID, Email,Username, Description, Image from user join profile on UserID=ProfileID where email='"+arrayList.get(1)+"';";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        ResultSet rs = stmt.executeQuery(query);
                        String pass ="";
                        while (rs.next()) {
                            pass=rs.getString("UserId")+";"+rs.getString("Email")+";"+rs.getString("Username")+";"+rs.getString("Description")+";";
                            Blob blob = (Blob) rs.getBlob("Image");
                            if(blob!=null){
                                byte[] bytes = blob.getBytes(1, (int)blob.length());
                                for(int i=0;i<bytes.length;i++){
                                    pass+=bytes[i];
                                }   
                            }else{
                                pass+="null";
                            }
                            pass+=";";
                        }
                        return pass;
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Error";
                    }
                case 3:
                    if(arrayList.size()!=2)return "Error de sentencia";
                    query = "select ProfileID, Username from profile where Username like '%"+arrayList.get(1)+"%';";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        ResultSet rs = stmt.executeQuery(query);
                        String pass ="";
                        while (rs.next()) {
                            pass+=rs.getString("ProfileID")+","+rs.getString("Username")+";";
                        }
                        return pass;
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Error";
                    }
                case 4:
                    if(arrayList.size()!=2)return "Error de sentencia";
                    query = "select name,price,image from nft where Name like %"+arrayList.get(1)+"%';";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        ResultSet rs = stmt.executeQuery(query);
                        String pass ="";
                        while (rs.next()) {
                            pass=rs.getString("name")+","+rs.getString("price")+",";
                            Blob blob = (Blob) rs.getBlob("Image");
                            if(blob!=null){
                                byte[] bytes = blob.getBytes(1, (int)blob.length());
                                for(int i=0;i<bytes.length;i++){
                                    pass+=bytes[i];
                                }   
                            }else{
                                pass+="null";
                            }
                            pass+=";";
                        }
                        return pass;
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Error";
                    }
                case 5:
                    if(arrayList.size()!=2)return "Error de sentencia";
                        query = "select UserID, Email,Username, Description, Image from user join profile on UserID=ProfileID where UserID='"+arrayList.get(1)+"';";
                        try (Statement stmt = (Statement) con.createStatement()) {
                            ResultSet rs = stmt.executeQuery(query);
                            String pass ="";
                            while (rs.next()) {
                                pass=rs.getString("UserId")+";"+rs.getString("Email")+";"+rs.getString("Username")+";"+rs.getString("Description")+";";
                                Blob blob = (Blob) rs.getBlob("Image");
                                if(blob!=null){
                                    byte[] bytes = blob.getBytes(1, (int) blob.length());
                                    String blobsString=new String(bytes);
                                    pass+=blobsString+";";
                                }else{
                                    pass+="null";
                                }
                            }
                            
                            return pass;
                        } catch (SQLException e) {
                            System.err.println("Error "+e.getSQLState());
                            return "Error";
                        }
                case 6:
                    if(arrayList.size()!=4)return "Error de sentencia";
                    query = "INSERT INTO `NFT` (Name, Description, User_UserID, Image) VALUES (?, ?, ?, ?)";
                    try {
                        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
                        stmt.setString(1, arrayList.get(1));
                        stmt.setString(2, arrayList.get(2));
                        stmt.setString(3, arrayList.get(3));
                        stmt.setBytes(4, Byte);
                    
                        stmt.execute();
                        Byte = null;
                    
                        return "Afirmativo";
                    } catch (SQLException e) {
                        System.err.println("Error: " + e.getSQLState());
                        return "Error";
                    }
                case 7:
                    if(arrayList.size()!=2)return "null";
                    query = "select NFTID, Name, Price, Image from NFT where Name like '%"+arrayList.get(1)+"%';";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        ResultSet rs = stmt.executeQuery(query);
                        String pass ="";
                        while (rs.next()) {
                            pass=rs.getString("Name")+";"+rs.getString("Price")+";"+rs.getString("NFTID")+";";
                            out.writeUTF(pass);
                            Blob blob = (Blob) rs.getBlob("Image");
                            byte[] bytes= blob.getBytes(1, (int) blob.length()); 
                            String imageString = Base64.getEncoder().encodeToString(bytes);
                            out.writeUTF(imageString);
                        }
                        return "null";
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Error";
                    }
                case 8:
                    if(arrayList.size()!=2)return "null";
                    query = "select NFTID, Name, Price, Image from NFT where User_UserID = "+arrayList.get(1)+";";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        ResultSet rs = stmt.executeQuery(query);
                        String pass ="";
                        while (rs.next()) {
                            pass=rs.getString("Name")+";"+rs.getString("Price")+";"+rs.getString("NFTID")+";";
                            out.writeUTF(pass);
                            Blob blob = (Blob) rs.getBlob("Image");
                            byte[] bytes= blob.getBytes(1, (int) blob.length()); 
                            String imageString = Base64.getEncoder().encodeToString(bytes);
                            out.writeUTF(imageString);
                        }
                        return "null";
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Error";
                    }
                case 9:
                    if(arrayList.size()!=2)return "null";
                    query = "select Name, NFTID, Description, Price, image from NFT where NFTID = "+arrayList.get(1)+";";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        ResultSet rs = stmt.executeQuery(query);
                        String pass ="";
                        String imageString="";
                        while (rs.next()) {
                            pass=rs.getString("Name")+";"+rs.getString("NFTID")+";"+rs.getString("Description")+";"+rs.getString("Price")+";";
                            Blob blob = (Blob) rs.getBlob("Image");
                            byte[] bytes= blob.getBytes(1, (int) blob.length()); 
                            imageString = Base64.getEncoder().encodeToString(bytes);
                        }
                        return pass+imageString+";";
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Error";
                    }
                case 10:
                    if(arrayList.size()!=2)return "null";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        query="SELECT c.IdChat, u.Name FROM chat AS c JOIN User AS u WHERE c.User_UserID1 = "+arrayList.get(1)+" or c.User_UserID = "+arrayList.get(1)+";";
                        String res="";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) { 
                            res+=rs.getString("IdChat")+";"+rs.getString("Name")+";";
                        }
                        return res;
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Ha ocurrido un error";
                    }
                case 11:
                    if(arrayList.size()!=2)return "null";
                    query="Select Mensaje from Msg where Chat_idChat = "+arrayList.get(1)+";";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        String res="";
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            res+=rs.getString("Mensaje")+";";
                        }
                        return res;
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Ha ocurrido un error";
                    }
                case 12:
                    if(arrayList.size()!=3)return "null";
                    query="insert  into msg (Mensaje, Chat_IdChat) values('"+arrayList.get(1)+"','"+arrayList.get(2)+"')';";
                    try (Statement stmt = (Statement) con.createStatement()) {
                        stmt.execute(query);
                        return "Afirmativo";
                    } catch (SQLException e) {
                        System.err.println("Error "+e.getSQLState());
                        return "Ha ocurrido un error";
                    }
            }
        }catch (IOException e) {
            e.printStackTrace();
            return "Error";
        }
        return "Error";
        
    }
    private void setconnection(String url,String user, String password){
        try {
            con=DriverManager.getConnection(url,user,password);
        } catch (SQLException exception) {
            System.out.println("Cannot establish connection to server");
            exception.printStackTrace();
        }
    }
    private ArrayList<String> parse(String stc){
        ArrayList<String> arrayList=new ArrayList<String>();
        String frase="";
        int last=0;
        for(int i=0;i<stc.length();i++){
            char temp=stc.charAt(i);
            if(temp==';'){
                arrayList.add(frase);
                frase="";
                last=i+1;
                break;
            }else frase+=temp;
        }
        int num=0;
        if(arrayList.size()>0){
            num=Integer.parseInt(arrayList.get(0));
        }
        if(num==6){
            int cont=0;
            for(int i=last;i<stc.length();i++){
                char temp=stc.charAt(i);
                if(temp==';'&&cont<=2){
                    arrayList.add(frase);
                    frase="";
                    cont++;
                }else if(cont==3){
                    Byte= Base64.getDecoder().decode(stc.substring(i));
                    return arrayList;
                }else{
                    frase+=temp;
                }
            }
            return null;
        }else{
            for(int i=last;i<stc.length();i++){
                char temp=stc.charAt(i);
                if(temp==';'){
                    arrayList.add(frase);
                    frase="";
                }else frase+=temp;
            }
            return arrayList;
        }
    }
}