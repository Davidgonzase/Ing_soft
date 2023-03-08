import java.awt.Color;
import javax.swing.*;

public class Display{
    private static Display instance;
    private JPanel paneles[];
    private User user;
    private JFrame frame;

    Color colorFondo = new Color(85,86,83);
    private Display(){
        frame=new JFrame("NFTrader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        login();
    }
    private JPanel Startingpanel(){
        JPanel panel=new JPanel();
        user=new User();
        return panel;
    }
    public static Display getinstance(){
        if(instance==null){
            instance=new Display();
        }
        return instance;
    }
    public void clean(JPanel panel){
        if(frame.getContentPane()!=null){
            frame.getContentPane().removeAll();
            frame.setContentPane(panel);
            SwingUtilities.updateComponentTreeUI(frame);
        }else frame.setContentPane(panel);
    }
    public void login(){
        JPanel panel=new JPanel();
        frame.setSize(300,520);
        frame.setResizable(false);
        panel.setBackground(colorFondo);
        JButton Loginb=new JButton("LOGIN");
        JButton Register=new JButton("Register");
        Loginb.addActionListener(new Loginmanager());
        Loginb.setSize(100,40);
        Loginb.setFocusable(false);
        panel.add(Loginb);
        panel.setLayout();
        clean(panel);
    }
    public void Register(){
        JPanel panel=new JPanel();
        frame.setSize(300,520);
        frame.setResizable(false);
        panel.setBackground(colorFondo);
        JButton Registerb=new JButton("Login");
        JButton Login=new JButton("Register");
        Registerb.addActionListener(new Loginmanager());
        Registerb.setSize(60,40);
        Registerb.setFocusable(false);
        Login.addActionListener(new Loginmanager());
        Login.setSize(60,50);
        Login.setFocusable(false);
        panel.add(Registerb);
        panel.add(Login);
        panel.setLayout(null);
        clean(panel);
    }


}
