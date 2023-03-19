package Managers;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import Display.Display;
import Display.Managerslist;

public class Loginmanager extends Manager{
   public Loginmanager(Display display){
      super(display);
   }
   @Override
   public void initialize() {
      display.chgFrame(new Dimension(300,520),false);
      JButton Loginb=new JButton(Buttons.LOGIN.toString());
      JButton Register=new JButton("Register");
      Loginb.addActionListener(this);
      Loginb.setSize(100,40);
      Loginb.setFocusable(false);
      panel.add(Loginb);
      panel.setLayout(null);
      display.clean(panel);
   }
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getActionCommand()==Buttons.LOGIN.toString()){
         Display.getinstance().setManager(Managerslist.REGISTER);
      }
   }
}