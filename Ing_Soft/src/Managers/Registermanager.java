package Managers;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.time.chrono.ThaiBuddhistChronology;

import javax.swing.JButton;

import Display.Display;
import Display.Managerslist;

public class Registermanager extends Manager{
    private javax.swing.JLabel ApellidoLabel;
    private javax.swing.JTextField ApellidoTextArea;
    private javax.swing.JLabel CorreoLabel;
    private javax.swing.JTextField CorreoTextArea;
    private javax.swing.JLabel NombreLabel;
    private javax.swing.JTextField NombreTextArea;
    private javax.swing.JLabel RegisterTitle;
    private javax.swing.JButton ReturnButton;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextArea;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextArea;
    public Registermanager(Display display){
        super(display);
     }
    @Override
    public void initialize(){
        RegisterTitle = new javax.swing.JLabel();
        NombreLabel = new javax.swing.JLabel();
        NombreTextArea = new javax.swing.JTextField();
        ApellidoLabel = new javax.swing.JLabel();
        ApellidoTextArea = new javax.swing.JTextField();
        CorreoLabel = new javax.swing.JLabel();
        CorreoTextArea = new javax.swing.JTextField();
        usernameTextArea = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        passwordTextArea = new javax.swing.JPasswordField();
        registerButton = new javax.swing.JButton();
        ReturnButton = new javax.swing.JButton();

        RegisterTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        RegisterTitle.setText("Crear cuenta");

        NombreLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NombreLabel.setText("Nombre");

        NombreTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NombreTextArea.setForeground(new java.awt.Color(153, 153, 153));
        NombreTextArea.setText("Nombre");
        NombreTextArea.addActionListener(this);

        ApellidoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ApellidoLabel.setText("Apellidos");

        ApellidoTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ApellidoTextArea.setForeground(new java.awt.Color(153, 153, 153));
        ApellidoTextArea.setText("Apellidos");
        ApellidoTextArea.addActionListener(this);

        CorreoLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CorreoLabel.setText("Correo");

        CorreoTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CorreoTextArea.setForeground(new java.awt.Color(153, 153, 153));
        CorreoTextArea.setText("Correo");
        CorreoTextArea.addActionListener(this);

        usernameTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameTextArea.setForeground(new java.awt.Color(153, 153, 153));
        usernameTextArea.setText("Nombre de usuario");
        usernameTextArea.addActionListener(this);

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        usernameLabel.setText("Nombre de usuario");

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordLabel.setText("Password");

        passwordTextArea.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        passwordTextArea.setForeground(new java.awt.Color(153, 153, 153));
        passwordTextArea.setText("jPasswordField1");

        registerButton.setText("Registrarse");
        registerButton.addActionListener(this);

        ReturnButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flechaizquierda.png"))); // 
        ReturnButton.setActionCommand("Login");
        ReturnButton.addActionListener(this);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(passwordTextArea)
                    .addComponent(NombreLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NombreTextArea)
                    .addComponent(ApellidoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ApellidoTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(CorreoLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CorreoTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(usernameTextArea, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(28, 28, 28))
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(registerButton))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(ReturnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(RegisterTitle)))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RegisterTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReturnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NombreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NombreTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ApellidoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ApellidoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CorreoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CorreoTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTextArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(registerButton)
                .addContainerGap(33, Short.MAX_VALUE))
        );

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
        display.chgFrame(null, false);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getActionCommand()=="Login"){
            display.setManager(Managerslist.LOGIN);
        }
        if(e.getActionCommand()=="Registrarse"){
            NombreTextArea.getText();
            String password=new String(passwordTextArea.getPassword());
            System.out.println(UserApp.App.nc(NombreTextArea.getText(),ApellidoTextArea.getText(),CorreoTextArea.getText(),usernameTextArea.getText(),password));
        }
    }
 }