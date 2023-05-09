package UserApp;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Display.Display;

public class App {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static User user=null;
    public static User get_user(){
        return user;
    } 


    public static void main(String[] args) throws Exception {
        Display.getinstance();
        
    }
    private static void request(){
        try {
            socket=new Socket("localhost",48816);
            in=new DataInputStream (socket.getInputStream());
            out=new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String nc(String nombre,String apellidos,String correo,String nuser,String password){
        String st="1;"+nombre+";"+apellidos+";"+correo+";"+nuser+";"+password+";";
        try {
            request();
            out.writeUTF(st);
            return in.readUTF();
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
            return "Error";
        }
    }
    public static boolean conect(String nuser,String password){
        String st="0;"+nuser+";"+password+";";
        try {
            request();
            out.writeUTF(st);
            if(in.readUTF().equals("Afirmativo")){
                return true;
            }
            return false;
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
            return false;
        }
    }
    public static void getmainuser(String correo){
        String st="2;"+correo+";";
        try {
            request();
            out.writeUTF(st);
            ArrayList<String> colector=new ArrayList<String>();
            st=in.readUTF();
            parse(colector,st);
            System.out.println(st);
            byte[] bytes=colector.get(3).getBytes(); 
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            user=new User(colector.get(0),colector.get(1),colector.get(2),colector.get(3),bis);
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
            user= null;
        }
    }

    private static void parse(ArrayList<String> arrayList,String stc){
        String frase="";
        for(int i=0;i<stc.length();i++){
            char temp=stc.charAt(i);
            if(temp==';'){
                arrayList.add(frase);
                frase="";
            }else frase+=temp;
        }
    }
}
