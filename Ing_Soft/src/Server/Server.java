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

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Statement;

class Server{
    private Socket currenSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private  Connection con=null;
    public Server(){
        System.out.println("Starting Server...");
        setconnection("jdbc:mysql://localhost:3306/ingsoft","Ing_s", "Ing_s");
        if (con != null) {
            System.out.println("Conexion established");
        }
        try {
            ServerSocket serverSocket = new ServerSocket(48816);
            while(true){
                currenSocket=serverSocket.accept();
                in=new DataInputStream(currenSocket.getInputStream());
                out=new DataOutputStream(currenSocket.getOutputStream());
                out.writeUTF(request());
            }
        } catch (IOException exception) {
            System.err.println(exception.toString());
            exception.printStackTrace();
        }
    }
    private String request(){
        String query;
        ArrayList<String> arrayList=new ArrayList<String>();
        try {
            query=in.readUTF();
            int caso=0;
            parse(arrayList, query);
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
    private void parse(ArrayList<String> arrayList,String stc){
        String frase="";
        for(int i=0;i<stc.length();i++){
            char temp=stc.charAt(i);
            if(temp==';'){
                arrayList.add(frase);
                frase="";
            }else frase+=temp;
        }
    }
    private boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}