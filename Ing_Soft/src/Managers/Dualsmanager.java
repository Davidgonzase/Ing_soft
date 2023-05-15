package Managers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.*;

import Display.Display;
import Display.Managerslist;

public class Dualsmanager extends Manager{
    private JComponent currentpanel=null;
    private JSplitPane pane;
    private JButton currentbutton;
    private JButton perfilButton,catalogoButton,chatsButton,busquedaButton,cerrarButton;
    private boolean side=false;
    private JButton location= new JButton();
    private ImageIcon image = new ImageIcon("src\\img\\arrow.png");
    private ImageIcon image1 = new ImageIcon("src\\img\\arrow1.png");
    private Color colorpanel=new Color(207, 212, 227);
    public Dualsmanager(Display display){
        super(display);
     }
    
    @Override
    public void initialize() {
        panel.setLayout(null);
        JLabel decoracion = new JLabel();
        decoracion.setBounds(0,0,170,60);
        decoracion.setBackground(color);
        decoracion.setOpaque(true);
        panel.add(decoracion);

        perfilButton= createButton(100,"Perfil");
        panel.add(perfilButton);
        perfilButton.setBackground(color);
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
        backdecoracion.setBackground(colorpanel);
        backdecoracion.setOpaque(true);
        panel.add(backdecoracion);
        pane=new JSplitPane(1, panel, currentpanel);
        display.clean(pane);
        pane.setDividerLocation(170);
        busquedaButton.setBackground(colorpanel);
        catalogoButton.setBackground(colorpanel);
        chatsButton.setBackground(colorpanel);
        pane.setDividerSize(2);
        location.setBounds(0,330,25,40);
        location.setIcon(image1);
        location.setActionCommand("Side");
        location.addActionListener(this);

        pane.setEnabled(false);
        
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
        }else if(e.getActionCommand()=="Side"){
            if(side==false){
                pane.setDividerLocation(0);
                location.setIcon(image);
                side=true;
            }else{
                pane.setDividerLocation(170);
                location.setIcon(image1);
                side=false;
            }
        }
    }

    private void chg(JButton button,Managerslist manager){
        if(display.gManagerslist()!=manager){
            currentbutton.setBackground(colorpanel);
            currentbutton=button;
            currentbutton.setBackground(color);
            display.setManager(manager);
        }
    }

    public void clean(JComponent npanel){
        if(currentpanel==null){
            currentpanel=npanel;
            currentpanel.add(location);
            pane.add(currentpanel);
        }else{
            pane.remove(currentpanel);
            currentpanel=npanel;
            currentpanel.add(location);
            pane.add(currentpanel);
            display.reload();
        }
    }
}
