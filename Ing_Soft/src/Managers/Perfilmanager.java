package Managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

import Display.Display;
import UserApp.User;

public class Perfilmanager extends Manager{
    private User user;
    public Perfilmanager(Display display,User user) {
        super(display);
        this.user=user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    
    @Override
    public void initialize() {
        panel.setSize(720,720);
        JLabel foto=new JLabel(/*new ImageIcon()*/);
        foto.setBounds(120,30,250,250);
        foto.setBackground(Color.WHITE);
        foto.setOpaque(true);
        panel.add(foto);

        JLabel username = new JLabel(user.get_username());
        username.setBounds(415,30,170,60);
        username.setFont(new Font("Arial", Font.PLAIN, 30));
        panel.add(username);

        JButton catalogo = createButton(100, "Catalogo");
        panel.add(catalogo);

        JButton Contactar = createButton(170, "Contactar");
        panel.add(Contactar);

        JLabel descripcion=new JLabel(user.get_description());
        descripcion.setBounds(80,340,520,250);
        descripcion.setBackground(Color.WHITE);
        descripcion.setOpaque(true);
        panel.add(descripcion);
    }
    public JButton createButton(int posy, String texto){
        JButton jButton= new JButton(texto);
        jButton.setBounds(400,posy,170,60);
        jButton.setBackground(Color.RED);
        jButton.setForeground(Color.CYAN);
        return jButton;
    }
}
