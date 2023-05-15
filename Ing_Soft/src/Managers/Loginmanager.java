package Managers;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

import Display.Display;
import Display.Managerslist;
import UserApp.App;


public class Loginmanager extends Manager{
   private javax.swing.JButton createaccountButton;
   private javax.swing.JPasswordField inputpassword;
   private javax.swing.JTextField inputusername;
   private javax.swing.JButton LoginButton;
   private javax.swing.JLabel PasswordLabel;
   private javax.swing.JLabel Usernamelabel;
   private JLabel Logo;
   private JLabel errortext;
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
      errortext= new JLabel();
      createaccountButton = new javax.swing.JButton();

      Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N

      inputusername.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputusername.setText("Usuario");
      inputpassword.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      inputpassword.setText("Contraseña");
      inputpassword.addActionListener(this);

      PasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
      PasswordLabel.setText("Contraseña");
      PasswordLabel.setForeground(Color.WHITE);

      Usernamelabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
      Usernamelabel.setText("Usuario");
      Usernamelabel.setForeground(Color.WHITE);


      LoginButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
      LoginButton.setText("Entrar");
      LoginButton.addActionListener(this);

      createaccountButton.setForeground(Color.BLACK);
      createaccountButton.setText("Crear cuenta");
      createaccountButton.addActionListener(this);
      createaccountButton.setBorderPainted(false);

      errortext.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
      errortext.setText("");
      errortext.setForeground(Color.RED);
      ponerTextFieldBonito(inputusername,"Usuario");
      ponerTextFieldBonito(inputpassword,"Contraseña");
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

      c.gridx = 0;
      c.gridy = 7;
      panel.add(errortext, c);

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
            errortext.setText("Usuario no valido");
         }
         String password=new String(inputpassword.getPassword());
         errortext.setText("hola");
         if(UserApp.App.conect((String)inputusername.getText(),password)==true){
            panel.setLayout(null);
            Display.getinstance().setManager(Managerslist.DUAL);
            App.getmainuser((String)inputusername.getText());
            Display.getinstance().setManager(Managerslist.PERFIL);
         }else{
            errortext.setText("Ha ocurrido un error");
         }
      }else if(e.getActionCommand()=="Crear cuenta"){
         Display.getinstance().setManager(Managerslist.REGISTER);
      }
   }
   private void ponerTextFieldBonito(JTextField textField, String string){
      textField.addFocusListener(new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
              if (textField.getText().equals(string)) {
                  textField.setText("");
              }
          }

          @Override
          public void focusLost(FocusEvent e) {
              if (textField.getText().isEmpty()) {
                  textField.setText(string);
              }
          }
      });
  }
}