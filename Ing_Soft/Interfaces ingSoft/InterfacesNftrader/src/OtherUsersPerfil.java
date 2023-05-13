import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OtherUsersPerfil extends JFrame{
    public OtherUsersPerfil(){
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

        JPanel NFTprofilePanel = new JPanel();
        NFTprofilePanel.setSize(720,720);
        NFTprofilePanel.setLayout(null);
        add(NFTprofilePanel);

        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\InterfacesNftrader\\Images\\flechaizquierda.png"));
        NFTprofilePanel.add(goBack);

        JPanel otheruserPanel = new JPanel();
        otheruserPanel.setBounds(120,90,430,550);
        otheruserPanel.setLayout(null);
        otheruserPanel.setBackground(new Color(196,196,196));
        NFTprofilePanel.add(otheruserPanel);

        JLabel nftImage = new JLabel(/*new ImageIcon()*/);
        nftImage.setBounds(90,70,250,250);
        nftImage.setBackground(Color.WHITE);
        nftImage.setOpaque(true);
        otheruserPanel.add(nftImage);

        JLabel NombreNFT = createLabel("Username",350,30);
        otheruserPanel.add(NombreNFT);

        JTextArea descripción = new JTextArea("Descripción");
        descripción.setBounds(90,400,250,100);
        descripción.setBackground(Color.white);
        descripción.setOpaque(true);
        otheruserPanel.add(descripción);

        JButton catalogoButton = new JButton("Catálogo");
        catalogoButton.setBounds(90,500,90,30);
        otheruserPanel.add(catalogoButton);

        JButton contactarButton = new JButton("Contactar");
        contactarButton.setBounds(250,500,90,30);
        otheruserPanel.add(contactarButton);

        /*JLabel creadorNft = createLabel("Creador",410,20);
        otheruserPanel.add(creadorNft);

        JLabel propietarioNft = createLabel("Propietario",440,20);
        otheruserPanel.add(propietarioNft);*/

        setVisible(true);
    }
    private JLabel createLabel(String texto, int posy, int tamaño){
        JLabel label = new JLabel(texto);
        label.setBounds(90,posy,400,60);
        label.setFont(new Font("Arial", Font.PLAIN, tamaño));
        return label;
    }
}
