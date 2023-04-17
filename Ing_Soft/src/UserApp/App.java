package UserApp;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Display.Display;

public class App {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    public static void main(String[] args) throws Exception {
        Display.getinstance();
        
    }
    public static boolean conected(){
        try {
            socket=new Socket("localhost",48816);
            in=new DataInputStream (socket.getInputStream());
            out=new DataOutputStream(socket.getOutputStream());
            out.writeInt(0);
            if(in.readInt()==101)return true;
            else return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
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
    public static boolean conect(String email,String password){
        if(conected()==true){
            try {
                request();
                out.writeInt(2);
                out.writeUTF(email);
                out.writeUTF(password);
                int num=in.readInt();
                if(num==2)return true;
                else return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }else{
            return false;
        }
    }
}
