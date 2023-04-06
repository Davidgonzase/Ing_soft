package UserApp;
import java.awt.Dimension;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import Display.Display;
import Server.User;

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
}
