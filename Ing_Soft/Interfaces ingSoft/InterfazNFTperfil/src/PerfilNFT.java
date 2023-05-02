import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PerfilNFT extends JFrame {
    public PerfilNFT(){
        setSize(720,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(720,720);
        mainPanel.setLayout(null);
        add(mainPanel);

        JButton goBack = new JButton();
        goBack.setBounds(171,0,30,30);
        goBack.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\InterfazNFTperfil\\Images\\flechaizquierda.png"));
        mainPanel.add(goBack);

        JTextField buscador = new JTextField("Buscar");
        buscador.setBounds(240,40,400,30);
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
        mainPanel.add(buscador);

        JButton buscarButton=new JButton();
        buscarButton.setBounds(640,40,30,30);
        buscarButton.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\InterfazNFTperfil\\Images\\lupa.png"));
        mainPanel.add(buscarButton);

        JPanel nftPanel = new JPanel();
        nftPanel.setBounds(240,90,430,550);
        nftPanel.setLayout(null);
        nftPanel.setBackground(new Color(196,196,196));
        mainPanel.add(nftPanel);

        JLabel nftImage = new JLabel(/*new ImageIcon()*/);
        nftImage.setBounds(90,70,250,250);
        nftImage.setBackground(Color.WHITE);
        nftImage.setOpaque(true);
        nftPanel.add(nftImage);

        JLabel NombreNFT = createLabel("Nombre del NFT",350,30);
        nftPanel.add(NombreNFT);

        JLabel valorNft = createLabel("Valor actual",380,20);
        nftPanel.add(valorNft);

        JLabel creadorNft = createLabel("Creador",410,20);
        nftPanel.add(creadorNft);

        JLabel propietarioNft = createLabel("Propietario",440,20);
        nftPanel.add(propietarioNft);

        setVisible(true);
    }
    private JLabel createLabel(String texto, int posy, int tamaño){
        JLabel label = new JLabel(texto);
        label.setBounds(90,posy,400,60);
        label.setFont(new Font("Arial", Font.PLAIN, tamaño));
        return label;
    }
}
