package Managers;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import Display.Display;
import Display.Managerslist;

public class Registermanager extends Manager{
    public Registermanager(Display display){
        super(display);
     }
    @Override
    public void initialize(){
        display.chgFrame(new Dimension(300,520),false);
        JButton Registerb=new JButton(Buttons.LOGIN.toString());
        JButton Login=new JButton("Register");
        Registerb.addActionListener(this);
        Registerb.setSize(60,40);
        Registerb.setFocusable(false);
        Login.addActionListener(this);
        Login.setSize(60,50);
        Login.setFocusable(false);
        panel.add(Registerb);
        panel.add(Login);
        panel.setLayout(null);
        display.clean(panel);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()==Buttons.LOGIN.toString()){
            Display.getinstance().setManager(Managerslist.LOGIN);
        }
    }
 }