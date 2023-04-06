package Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Server{
    private Socket currenSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private  Connection con=null;
    private Socket[] sockets=new Socket[10];
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
                boolean Disponible=false;
                in=new DataInputStream(currenSocket.getInputStream());
                out=new DataOutputStream(currenSocket.getOutputStream());
                for(int i=0;i<5;i++){
                    if(sockets[i]==null)Disponible=true;
                    if(sockets[i]==currenSocket)request();
                }
                if(Disponible==false){
                    out.writeInt(1);
                    currenSocket.close();
                }else{
                    request();
                }
            }
        } catch (IOException exception) {
            System.err.println(exception.toString());
            exception.printStackTrace();
        }
    }
    private void request(){
        int input;
        try {
            input=in.readInt();
            System.out.println(input);
            switch (input){
                case 0:
                int free=-1;
                boolean notin=true,ft=true;
                for(int i=0;i<sockets.length;i++){
                    if(ft&&sockets[i]==null){
                        free=i;
                        ft=false;
                    }
                    if(sockets[i]==currenSocket){
                        notin=false;
                    }
                }
                if(notin=true&&free>=0){
                    sockets[free]=currenSocket;
                    out.writeInt(101);
                }else{
                    out.writeInt(-1);
                }
                break;
                case 1:
                for(int i=0;i<sockets.length;i++){
                    if(sockets[i]==currenSocket){
                        currenSocket.close();
                        sockets[i].close();
                        sockets[i]=null;
                        break;
                    }
                }
                break;
                case 2:
                
                break;
                case 3:

                break;
                case 4:

                break;

    
            }
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }            
    }
    private void setconnection(String url,String user, String password){
        try {
            con=DriverManager.getConnection(url,user,password);
        } catch (SQLException exception) {
            System.out.println("Cannot establish connection to server");
            exception.printStackTrace();
        }
    }
}