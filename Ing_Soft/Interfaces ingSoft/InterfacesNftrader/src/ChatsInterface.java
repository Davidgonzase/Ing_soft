import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatsInterface extends JFrame {
    private int labelCounter = 0;
    public ChatsInterface(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Buscador.class.getName()).log(Level.SEVERE, null, ex);
        }
        setSize(720,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel chatsPanel = new JPanel();
        chatsPanel.setSize(720,720);
        chatsPanel.setLayout(null);
        add(chatsPanel);

        JPanel otherschatsPanel = new JPanel();
        otherschatsPanel.setSize(250,720);
        otherschatsPanel.setLayout(null);

        JScrollPane otherschatsScrollPane = new JScrollPane(otherschatsPanel);
        otherschatsScrollPane.setBounds(0,0,250,720);
        otherschatsScrollPane.setPreferredSize(new Dimension(183,720));
        otherschatsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatsPanel.add(otherschatsScrollPane);

        JLabel label = new JLabel("Nombre del chat");
        label.setBounds(343,0,355,30);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setBackground(Color.RED);
        label.setOpaque(true);
        chatsPanel.add(label);

        JPanel chatPanel = new JPanel();
        chatPanel.setSize(355,650);
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        JScrollPane mainChatScroll= new JScrollPane(chatPanel);
        mainChatScroll.setBounds(343,30,355,620);
        mainChatScroll.setPreferredSize(new Dimension(355,650));
        mainChatScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatsPanel.add(mainChatScroll);

        JTextField writeMessages = new JTextField();
        writeMessages.setBounds(343,650,320,30);
        chatsPanel.add(writeMessages);

        JButton sendButton = new JButton();
        sendButton.setBounds(660,650,35,30);
        sendButton.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\Interfaz_Chats\\Images\\send_arrow.png"));
        chatsPanel.add(sendButton);

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
        setVisible(true);
    }
}
