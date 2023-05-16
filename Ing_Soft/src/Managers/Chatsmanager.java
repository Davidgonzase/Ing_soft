package Managers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

import Display.Display;
import UserApp.App;
import UserApp.Messages;
import UserApp.User;

public class Chatsmanager extends Manager implements Runnable{
    private int labelCounter = 0;
    private Thread messagesThread;
    private ArrayList<Messages> listamensajes;
    private int currentChat=1;
    private JPanel chatPanel;
    private JScrollPane mainChatScroll;
    public Chatsmanager(Display display) {
        super(display);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentChat=Integer.parseInt(e.getActionCommand());
        paneles(mainChatScroll, chatPanel);
    }

    @Override
    public void initialize() {
        panel.setSize(720,720);
        JPanel otherspanel = new JPanel();
        otherspanel.setSize(250,720);
        otherspanel.setLayout(null);

        JScrollPane otherschatsScrollPane = new JScrollPane(otherspanel);
        otherschatsScrollPane.setBounds(0,0,250,720);
        otherschatsScrollPane.setPreferredSize(new Dimension(183,720));
        otherschatsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(otherschatsScrollPane);

        JLabel label = new JLabel("Nombre del chat");
        label.setBounds(343,0,355,30);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setBackground(Color.RED);
        label.setOpaque(true);
        panel.add(label);

        chatPanel = new JPanel();
        chatPanel.setSize(355,650);
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        mainChatScroll= new JScrollPane(chatPanel);
        mainChatScroll.setBounds(343,30,355,620);
        mainChatScroll.setPreferredSize(new Dimension(355,650));
        mainChatScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(mainChatScroll);

        JTextField writeMessages = new JTextField();
        writeMessages.setBounds(343,650,320,30);
        panel.add(writeMessages);

        JButton sendButton = new JButton();
        sendButton.setBounds(660,650,35,30);
        sendButton.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\Interfaz_Chats\\Images\\send_arrow.png"));
        panel.add(sendButton);
        paneles_chat(otherschatsScrollPane, otherspanel);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentChat!=0){
                    String message = App.get_user().get_username()+":"+writeMessages.getText();
                    App.introducirmensajes(currentChat, message);
                    paneles(mainChatScroll, chatPanel);
                }
            }
        });
    }

    private void paneles_chat(JScrollPane scrollPane,JPanel resultadosPanel){
        int inty=0;
        String src= App.getListaChats(App.get_user().get_id());
        String id=null,chat=null,frase="";
        System.out.println(src);
        for(int i=0;i<src.length();i++){
            char temp=src.charAt(i);
            if(temp==';'){
                if(id==null){
                    id=frase;
                    frase="";
                }else{
                    chat=frase;
                    frase="";
                    JButton button= new JButton(chat);
                    button.setActionCommand(id);
                    button.addActionListener(this);
                    button.setBounds(0,inty,240, 30);
                    resultadosPanel.add(button);
                    inty+=31;
                    id=null;
                    chat="";
                }
            }else{
                frase+=temp;
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

    private void paneles(JScrollPane scrollPane,JPanel resultadosPanel){
        resultadosPanel.removeAll();
        String src=App.generarStringmensajes(currentChat);
        int inty=0;
        String username=null,msg=null,frase="";
        for(int i=0;i<src.length();i++){
            char temp=src.charAt(i);
            if(temp==';'){
                    msg=frase;
                    frase="";
                    JButton button= new JButton(msg);
                    button.setActionCommand(username);
                    button.addActionListener(this);
                    if(username==App.get_user().get_username()){
                        button.setBounds(120,inty,240, 30);
                    }else{
                        button.setBounds(0,inty,240, 30);
                    }
                    resultadosPanel.add(button);
                    inty+=31;
                    username="";
                    msg="";
            }else if(temp==':'){
                username=frase;
                frase="";
            }else{
                frase+=temp;
            }
        }
        if (resultadosPanel.getPreferredSize().height > scrollPane.getViewport().getSize().height) {
            int newHeight = resultadosPanel.getPreferredSize().height + 50;
            int currentWidth = scrollPane.getViewport().getViewSize().width;
            scrollPane.setPreferredSize(new Dimension(currentWidth, newHeight));
            scrollPane.revalidate();
            scrollPane.getViewport().setViewPosition(new Point(currentWidth,newHeight));
            display.reload();
        }
        display.reload();
    }

    @Override
    public void run() {
        for (int i=0; i<listamensajes.size(); i++){
            if (!listamensajes.get(i).getEscrito()){
                JLabel messageLabel = new JLabel(listamensajes.get(i).getMessage());
                messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                chatPanel.add(messageLabel); // Agregar el JLabel al JPanel
                labelCounter++; // Incrementar el contador de JLabels
                if (labelCounter > (chatPanel.getHeight() / messageLabel.getPreferredSize().height)) {
                    mainChatScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                }
            }
        }
    }
}