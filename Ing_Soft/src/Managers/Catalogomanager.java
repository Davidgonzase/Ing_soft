package Managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Display.Display;
import UserApp.User;

public class Catalogomanager extends Manager{
    public Catalogomanager(Display display) {
        super(display);
    }
    
    @Override
    public void initialize() {
        panel.setSize(720, 720);
        panel.setLayout(null);
        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setSize(535,595);
        resultadosPanel.setLayout(null);
        resultadosPanel.setBackground(new Color(196,196,196));

        JScrollPane scrollPane = new JScrollPane(resultadosPanel);
        scrollPane.setBounds(171,0,535,595);
        panel.add(scrollPane);

        JButton añadir = createButton(180,"Añadir");
        panel.add(añadir);

        JButton eliminar = createButton(500,"Eliminar");
        panel.add(eliminar);
    }
    
    public JButton createButton(int posx, String texto){
        JButton jButton= new JButton(texto);
        jButton.setBounds(posx,600,170,60);
        jButton.setFont(new Font("Arial", Font.PLAIN, 30));
        jButton.setBackground(Color.RED);
        jButton.setForeground(Color.white);
        return jButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
    
}
