package Managers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import Display.Display;
import Display.Managerslist;
import UserApp.App;

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
        ArrayList<ArrayList<String>> arrayList= App.getownNFT(App.get_user().get_id());
        int posy=0;
        for(int i=0;i<arrayList.size();i++){
            String text=arrayList.get(i).get(0);
            String imageString = arrayList.get(i).get(1);
            byte[] imageBytes = Base64.getDecoder().decode(imageString);
            String name=null,id=null,precio=null,frase="";
            for(int j=0;j<text.length();j++){
                char temp=text.charAt(j);
                if(temp==';'){
                    if(name==null){
                        name=frase;
                        frase="";
                    }else if(precio==null){   
                        precio=frase;
                        frase="";
                    }else id=frase;
                }else frase+=temp;
            }
            Image image;
            try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes)) {
                image = ImageIO.read(bis);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            Image resizedImage = image.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            JButton button= new JButton(name);
            JLabel price= new JLabel(precio);
            JSplitPane pane1= new JSplitPane(1,button,price);
            ImageIcon imageIcon = new ImageIcon(resizedImage);
            JLabel label = new JLabel(imageIcon);
            JSplitPane pane2= new JSplitPane(1,pane1,label);
            label.setSize(30, 30);
            price.setSize(170, 30);
            button.setSize(400, 30);
            pane1.setSize(630, 30);
            pane1.setEnabled(false);
            pane1.setDividerLocation(400);
            pane2.setEnabled(false);
            pane2.setDividerLocation(500);
            pane2.setBounds(0, posy, 630, 30);
            button.setActionCommand(id);
            button.addActionListener(this);
            resultadosPanel.add(pane2);
            posy+=31;
        }
        if (resultadosPanel.getPreferredSize().height > scrollPane.getViewport().getSize().height) {
            int newHeight = resultadosPanel.getPreferredSize().height + 50;
            int currentWidth = scrollPane.getViewport().getViewSize().width;
            scrollPane.setPreferredSize(new Dimension(currentWidth, newHeight));
            scrollPane.revalidate();
            display.reload();
        }
        display.reload();
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
        }else if(e.getActionCommand()=="Eliminar"){

        }else{
            App.getNFT_profile(Integer.parseInt(e.getActionCommand()));
            Display.getinstance().setManager(Managerslist.NFTPERFIL);
        }
        
    }
    
}
