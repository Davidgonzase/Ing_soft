import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatsInterface extends JFrame {
    private int labelCounter = 0;
    public ChatsInterface(){
        setSize(720,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(720,720);
        mainPanel.setLayout(null);
        add(mainPanel);

        JPanel otherschatsPanel = new JPanel();
        otherschatsPanel.setSize(183,720);
        otherschatsPanel.setLayout(null);

        JScrollPane otherschatsScrollPane = new JScrollPane(otherschatsPanel);
        otherschatsScrollPane.setBounds(170,0,183,720);
        otherschatsScrollPane.setPreferredSize(new Dimension(183,720));
        otherschatsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(otherschatsScrollPane);

        JLabel label = new JLabel("Nombre del chat");
        label.setBounds(353,0,355,30);
        label.setFont(new Font("Arial", Font.PLAIN, 30));
        label.setBackground(Color.RED);
        label.setOpaque(true);
        mainPanel.add(label);

        JPanel chatPanel = new JPanel();
        chatPanel.setSize(355,650);
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        JScrollPane mainChatScroll= new JScrollPane(chatPanel);
        mainChatScroll.setBounds(353,30,355,620);
        mainChatScroll.setPreferredSize(new Dimension(355,650));
        mainChatScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        mainPanel.add(mainChatScroll);

        JTextField writeMessages = new JTextField();
        writeMessages.setBounds(353,650,320,30);
        mainPanel.add(writeMessages);

        JButton sendButton = new JButton();
        sendButton.setBounds(670,650,35,30);
        sendButton.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\Interfaz_Chats\\Images\\send_arrow.png"));
        mainPanel.add(sendButton);

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
