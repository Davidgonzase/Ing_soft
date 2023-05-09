package Managers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.Border;

import Display.Display;
import Display.Managerslist;

public class Dualsmanager extends Manager{
    private JComponent currentpanel=null;
    private JSplitPane pane;
    private JButton currentbutton;
    private JButton perfilButton,catalogoButton,chatsButton,busquedaButton,cerrarButton;
    public Dualsmanager(Display display){
        super(display);
     }
    
    @Override
    public void initialize() {
        panel.setLayout(null);
        JLabel decoracion = new JLabel();
        decoracion.setBounds(0,0,170,60);
        decoracion.setBackground(Color.red);
        decoracion.setOpaque(true);
        panel.add(decoracion);

        perfilButton= createButton(100,"Perfil");
        panel.add(perfilButton);
        perfilButton.setBackground(Color.red);
        perfilButton.setFocusable(false);
        perfilButton.addActionListener(this);
        currentbutton=perfilButton;

        catalogoButton= createButton(170,"Mi catálogo");
        panel.add(catalogoButton);
        catalogoButton.setFocusable(false);
        catalogoButton.addActionListener(this);

        chatsButton= createButton(240,"Chats");
        panel.add(chatsButton);
        chatsButton.setFocusable(false);
        chatsButton.addActionListener(this);

        busquedaButton= createButton(310,"Busqueda");
        panel.add(busquedaButton);
        busquedaButton.setFocusable(false);
        busquedaButton.addActionListener(this);

        cerrarButton= createButton(610,"Cerrar sesión");
        panel.add(cerrarButton);
        cerrarButton.setFocusable(false);
        cerrarButton.addActionListener(this);

        JLabel backdecoracion = new JLabel();
        backdecoracion.setBounds(0,0,170,720);
        backdecoracion.setBackground(Color.white);
        backdecoracion.setOpaque(true);
        panel.add(backdecoracion);
        pane=new JSplitPane(1, panel, currentpanel);
        display.clean(pane);
        pane.setDividerLocation(170);
        busquedaButton.setBackground(Color.white);
        catalogoButton.setBackground(Color.white);
        chatsButton.setBackground(Color.white);
        pane.setDividerSize(0);
        display.chgFrame(new Dimension(890, 720), false);
    }
    private JButton createButton(int posy, String texto){
        JButton jButton= new JButton(texto);
        jButton.setBounds(0,posy,170,60);
        return jButton;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="Perfil"){
            chg(perfilButton, Managerslist.PERFIL);
        }else if(e.getActionCommand()=="Mi catálogo"){
            chg(catalogoButton, Managerslist.CATALOGO);
        }else if(e.getActionCommand()=="Chats"){
            chg(chatsButton, Managerslist.CHATS);
        }else if(e.getActionCommand()=="Busqueda"){
            chg(busquedaButton, Managerslist.BUSQUEDA);
        }
    }

    private void chg(JButton button,Managerslist manager){
        if(display.gManagerslist()!=manager){
            currentbutton.setBackground(Color.white);
            currentbutton=button;
            currentbutton.setBackground(Color.red);
            display.setManager(manager);
        }
    }

    public void clean(JComponent npanel){
        if(currentpanel==null){
            currentpanel=npanel;
            pane.add(currentpanel);
        }else{
            pane.remove(currentpanel);
            currentpanel=npanel;
            pane.add(currentpanel);
            display.reload();
        }
    }
}
