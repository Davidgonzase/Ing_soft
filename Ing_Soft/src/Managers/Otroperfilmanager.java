package Managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Display.Display;
import Display.Managerslist;
import UserApp.User;

public class Otroperfilmanager extends Manager{

    private User user;
    public Otroperfilmanager(Display display,User user) {
        super(display);
        this.user=user;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Atras")){
            Display.getinstance().setManager(Managerslist.BUSQUEDA);
        }
    }

    @Override
    public void initialize() {
        panel.setSize(720,720);
        panel.setLayout(null);

        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flechaizquierda.png")));
        goBack.setActionCommand("Atras");
        goBack.addActionListener(this);
        panel.add(goBack);

        JPanel otheruserPanel = new JPanel();
        otheruserPanel.setBounds(120,90,430,550);
        otheruserPanel.setLayout(null);
        otheruserPanel.setBackground(new Color(196,196,196));
        panel.add(otheruserPanel);

        JLabel nftImage = new JLabel(/*new ImageIcon()*/);
        nftImage.setBounds(90,70,250,250);
        nftImage.setBackground(Color.WHITE);
        nftImage.setOpaque(true);
        otheruserPanel.add(nftImage);

        JLabel NombreNFT = createLabel(user.get_username(),350,30);
        otheruserPanel.add(NombreNFT);

        JTextArea descripción = new JTextArea(user.get_description());
        descripción.setBounds(90,400,250,100);
        descripción.setBackground(Color.white);
        descripción.setOpaque(true);
        otheruserPanel.add(descripción);

        JButton catalogoButton = new JButton("Catálogo");
        catalogoButton.setBounds(90,500,90,30);
        otheruserPanel.add(catalogoButton);

        JButton contactarButton = new JButton("Contactar");
        contactarButton.setBounds(250,500,90,30);
        otheruserPanel.add(contactarButton);

    }
    private JLabel createLabel(String texto, int posy, int tamaño){
        JLabel label = new JLabel(texto);
        label.setBounds(90,posy,400,60);
        label.setFont(new Font("Arial", Font.PLAIN, tamaño));
        return label;
    }
    
}
