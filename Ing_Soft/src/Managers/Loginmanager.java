package Managers;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

import Display.Display;
import Display.Managerslist;
import UserApp.App;


public class Loginmanager extends Manager{
   private final Dimension dimension=new Dimension(300,520);
   private javax.swing.JButton createaccountButton;
   private javax.swing.JPasswordField inputpassword;
   private javax.swing.JTextField inputusername;
   private javax.swing.JButton LoginButton;
   private javax.swing.JLabel PasswordLabel;
    private javax.swing.JLabel Usernamelabel;
   private JLabel Logo;
   public Loginmanager(Display display){
      super(display);
   }
   @Override
   public void initialize() {
      
      Logo = new javax.swing.JLabel();
      inputusername = new javax.swing.JTextField();
      inputpassword = new javax.swing.JPasswordField();
      PasswordLabel = new javax.swing.JLabel();
      Usernamelabel = new javax.swing.JLabel();
      LoginButton = new javax.swing.JButton();
      createaccountButton = new javax.swing.JButton();

      Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo-Pepsi.png"))); // NOI18N

      inputusername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputusername.setForeground(new java.awt.Color(153, 153, 153));
      inputusername.setText("Usuario");
      inputpassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputpassword.setForeground(new java.awt.Color(153, 153, 153));
      inputpassword.setText("Contraseña");
      inputpassword.addActionListener(this);

      PasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
      PasswordLabel.setText("Contraseña");

      Usernamelabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
      Usernamelabel.setText("Usuario");

      LoginButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
      LoginButton.setText("Entrar");
      LoginButton.addActionListener(this);

      createaccountButton.setForeground(new java.awt.Color(51, 204, 255));
      createaccountButton.setText("Crear cuenta");
      createaccountButton.addActionListener(this);
      createaccountButton.setBorderPainted(false);
      javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
      panel.setLayout(new GridBagLayout());

      GridBagConstraints c = new GridBagConstraints();
      c.gridx = 0;
      c.gridy = 0;
      c.anchor = GridBagConstraints.CENTER;
      c.insets = new Insets(10, 10, 10, 10);

      panel.add(Logo, c);

      c.gridx = 0;
      c.gridy = 1;
      c.fill = GridBagConstraints.HORIZONTAL;
      panel.add(Usernamelabel, c);

      c.gridx = 0;
      c.gridy = 2;
      panel.add(inputusername, c);

      c.gridx = 0;
      c.gridy = 3;
      panel.add(PasswordLabel, c);

      c.gridx = 0;
      c.gridy = 4;
      panel.add(inputpassword, c);

      c.gridx = 0;
      c.gridy = 5;
      panel.add(createaccountButton, c);

      c.gridx = 0;
      c.gridy = 6;
      panel.add(LoginButton, c);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(display.getCP());
        display.getCP().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
      display.clean(panel);
      display.chgFrame(new Dimension(300, 600), false);
   }
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getActionCommand()=="Entrar"){
         if((String)inputusername.getText()==""){
               
         }
         String password=new String(inputpassword.getPassword());
         if(UserApp.App.conect((String)inputusername.getText(),password)==true){
            panel.setLayout(null);
            Display.getinstance().setManager(Managerslist.DUAL);
            App.getmainuser((String)inputusername.getText());
            Display.getinstance().setManager(Managerslist.PERFIL);
         }else{
            System.out.println("no correcto");
         }
      }else if(e.getActionCommand()=="Crear cuenta"){
         Display.getinstance().setManager(Managerslist.REGISTER);
      }
   }
}