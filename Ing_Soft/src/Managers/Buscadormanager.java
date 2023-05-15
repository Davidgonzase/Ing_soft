package Managers;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import Display.Display;
import Display.Managerslist;
import UserApp.App;

public class Buscadormanager extends Manager{
    public Buscadormanager(Display display) {
        super(display);
    }
    boolean tipobusqueda=true;
    JTextField buscador;
    JPanel resultadosPanel;
    JScrollPane scrollPane;
    JButton nftSearch=new JButton("User");
    JButton userSearch=new JButton("NFT");

    @Override
    public void initialize() {
        panel.setSize(720,720);
        panel.setLayout(null);

        buscador = new JTextField("Buscar");
        buscador.setBounds(139,40,400,30);
        buscador.addActionListener(this);
        buscador.setActionCommand("buscar");
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
        buscarButton.setIcon(new ImageIcon("src\\img\\lupa.png"));
        buscarButton.addActionListener(this);
        buscarButton.setActionCommand("buscar");
        panel.add(buscarButton);

        JLabel label =new JLabel("Buscar:");
        label.setBounds(570,15,70,15);
        panel.add(label);

        nftSearch.setBounds(570,30,70,20);
        nftSearch.setBackground(color);
        nftSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipobusqueda=true;
                resultadosPanel.removeAll();
                display.reload();
                nftSearch.setBackground(color);
                userSearch.setBackground(Color.lightGray);
            }
        });
        panel.add(nftSearch);

        userSearch.setBounds(570,60,70,20);
        panel.add(userSearch);
        userSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tipobusqueda=false;
                resultadosPanel.removeAll();
                display.reload();
                nftSearch.setBackground(Color.lightGray);
                userSearch.setBackground(color);
            }
        });

        resultadosPanel = new JPanel();
        resultadosPanel.setSize(600,595);
        resultadosPanel.setLayout(new BoxLayout(resultadosPanel, BoxLayout.PAGE_AXIS));
        resultadosPanel.setBackground(new Color(196,196,196));

        scrollPane = new JScrollPane(resultadosPanel);
        scrollPane.setBounds(50,90,600,595);
        scrollPane.setPreferredSize(new Dimension(600, 595));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand()=="buscar"){
            paneles();
        }
    }
    private void paneles(){
        resultadosPanel.removeAll();
                int posy=0;
                if(tipobusqueda){
                    String parte1="";
                    String parte2="";
                    boolean permt=false;
                    String stc=App.getusers(buscador.getText());
                    System.out.println(stc);
                    for(int i=0;i<stc.length();i++){
                        char temp=stc.charAt(i);
                        if(temp==','){
                            permt=true;
                        }else if(temp==';'){
                            JButton button = new JButton(parte2);
                            button.setBackground(color);
                            button.setBorderPainted(false);
                            button.setSize(630,30);
                            button.setFont(new Font("Arial", Font.PLAIN, 30));
                            button.setActionCommand(parte1);
                            button.setBounds(0,posy,630,30);
                            resultadosPanel.add(button);
                            button.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    App.getotheruser(Integer.parseInt(e.getActionCommand()));
                                    display.setManager(Managerslist.OTROPERFIL);
                                }
                            });
                            posy+=31;
                            parte1="";
                            parte2="";
                            permt=false;
                        }else{
                            if(permt){
                                parte2+=temp;
                            }else{
                                parte1+=temp;
                            }
                        }
                    }
                }else{
                    ArrayList<ArrayList<String>> arrayList= App.getNFT(buscador.getText());
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
                        ByteArrayInputStream inStreambj = new ByteArrayInputStream(imageBytes);
                        Image image;
                        try (ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes)) {
                            image = ImageIO.read(bis);
                        } catch (IOException e) {
                            e.printStackTrace();
                            return;
                        }
                        ImageIcon imageIcon = new ImageIcon(image);
                        JLabel label = new JLabel(imageIcon);
                        resultadosPanel.add(label);
                    posy+=31;
                    }
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
}
