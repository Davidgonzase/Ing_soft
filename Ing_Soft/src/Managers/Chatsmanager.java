package Managers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Display.Display;
import UserApp.App;

public class Chatsmanager extends Manager{
    private int labelCounter = 0;
    public Chatsmanager(Display display) {
        super(display);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
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
        JPanel chatPanel = new JPanel();
        chatPanel.setSize(355,650);
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        JScrollPane mainChatScroll= new JScrollPane(chatPanel);
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

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el mensaje del JTextField
                String message = writeMessages.getText();
                if (!message.isEmpty()) { // Verificar que el mensaje no esté vacío
                    // Crear el JLabel con el mensaje
                    JLabel messageLabel = new JLabel(message);
                    messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                    messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
                    messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                    chatPanel.add(messageLabel); // Agregar el JLabel al JPanel
                    labelCounter++; // Incrementar el contador de JLabels
                    writeMessages.setText(""); // Borrar el texto del JTextField

                    // Si se han agregado más JLabels que los que caben en el JPanel, habilitar el scroll
                    if (labelCounter > (chatPanel.getHeight() / messageLabel.getPreferredSize().height)) {
                        mainChatScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                    }
                }
            }
        });
    }

}