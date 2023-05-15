package UserApp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;

import Display.Display;

public class App {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static User user=null;
    private static User loadeduser=null;
    public static User get_user(){
        return user;
    } 
    public static User get_loadedUser(){
        return loadeduser;
    }

    public static void main(String[] args) throws Exception {
        Display.getinstance();
        
    }
    private static boolean request(){
        try {
            socket=new Socket("192.168.104.123",48816);
            if(socket.isConnected()){
                in=new DataInputStream (socket.getInputStream());
                out=new DataOutputStream(socket.getOutputStream());
                return true;
            }else return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String nc(String nombre,String apellidos,String correo,String nuser,String password){
        String st="1;"+nombre+";"+apellidos+";"+correo+";"+nuser+";"+password+";";
        try {
            if(!request())return "Error";
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
            if(!request())return false;
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
            if(!request())return;
            out.writeUTF(st);
            ArrayList<String> colector=new ArrayList<String>();
            st=in.readUTF();
            parse(colector,st);
            System.out.println(st);
            byte[] bytes=colector.get(3).getBytes(); 
            user=new User(colector.get(0),colector.get(1),colector.get(2),colector.get(3),bytes);
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
            user= null;
        }
    }
    public static void getotheruser(int id){
        String st="5;"+id+";";
        try {
            if(!request())return;
            out.writeUTF(st);
            ArrayList<String> colector=new ArrayList<String>();
            st=in.readUTF();
            parse(colector,st);
            System.out.println(st);
            byte[] bytes=colector.get(3).getBytes(); 
            loadeduser=new User(colector.get(0),colector.get(1),colector.get(2),colector.get(3),bytes);
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
            user= null;
        }
    }
    public static String getusers(String texto){
        String st="3;"+texto+";";
        try {
            if(!request())return null;
            out.writeUTF(st);
            st=in.readUTF();
            return st;
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<ArrayList<String>> getNFT(String texto){
        String st="7;"+texto+";";
        try {
            if(!request())return null;
            out.writeUTF(st);
            String frase=in.readUTF();
            boolean v=true;
            ArrayList<ArrayList<String>> arrayList= new ArrayList<>();
            ArrayList<String> temp= new ArrayList<>();
            while(!frase.equals("null")){
                if(v){
                    temp.add(0,frase);
                    v=false;
                }else{
                    temp.add(1, frase);
                    v=true;
                    arrayList.add(temp);
                    temp=new ArrayList<>();
                }
                frase=in.readUTF();
            }
            return arrayList;
        } catch (IOException e) {
            System.out.println("ERROR");
            e.printStackTrace();
            return null;
        }
    }

    public static String newnft(String nombre,String descripcion,String user,byte[] image){
        String imageString = Base64.getEncoder().encodeToString(image);
        String st="6;"+nombre+";"+descripcion+";"+user+";"+imageString;
        try {
            if(!request())return "Error";
            out.writeUTF(st);
            return in.readUTF();
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
            return "Error";
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


