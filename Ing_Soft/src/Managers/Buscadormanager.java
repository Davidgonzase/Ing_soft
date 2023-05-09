package Managers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Display.Display;

public class Buscadormanager extends Manager{
    public Buscadormanager(Display display) {
        super(display);
    }
    
    @Override
    public void initialize() {
        panel.setSize(720,720);
        panel.setLayout(null);
        
        JTextField buscador = new JTextField("Buscar");
        buscador.setBounds(139,40,400,30);
        buscador.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (buscador.getText().equals("Buscar")) {
                    buscador.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (buscador.getText().isEmpty()) {
                    buscador.setText("Buscar");
                }
            }
        });
        panel.add(buscador);

        JButton buscarButton=new JButton();
        buscarButton.setBounds(539,40,30,30);
        buscarButton.setIcon(new ImageIcon("src\\images\\lupa.png"));
        panel.add(buscarButton);

        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setSize(600,595);
        resultadosPanel.setLayout(null);
        resultadosPanel.setBackground(new Color(196,196,196));

        JScrollPane scrollPane = new JScrollPane(resultadosPanel);
        scrollPane.setBounds(50,90,600,595);
        panel.add(scrollPane);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
