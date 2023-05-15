package Managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Display.Display;
import Display.Managerslist;

public class Catalogomanager extends Manager{
    public Catalogomanager(Display display) {
        super(display);
    }
    
    @Override
    public void initialize() {
        panel.setBounds(170,0,720,720);
        panel.setLayout(null);

        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setSize(600,595);
        resultadosPanel.setLayout(null);
        resultadosPanel.setBackground(Color.white);

        JScrollPane scrollPane = new JScrollPane(resultadosPanel);
        scrollPane.setBounds(50,0,600,595);
        panel.add(scrollPane);

        JButton añadir = createButton(100,"Añadir");
        añadir.addActionListener(this);
        panel.add(añadir);

        JButton eliminar = createButton(420,"Eliminar");
        panel.add(eliminar);
    }
    public JButton createButton(int posx, String texto){
        JButton jButton= new JButton(texto);
        jButton.setBounds(posx,600,170,60);
        jButton.setFont(new Font("Arial", Font.PLAIN, 30));
        jButton.setBackground(color);
        jButton.setForeground(Color.black);
        return jButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Añadir"){
            Display.getinstance().setManager(Managerslist.REGISTRARNFT);
        }
        
    }
    
}
