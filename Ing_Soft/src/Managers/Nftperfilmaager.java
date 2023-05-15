package Managers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Display.Display;
import Display.Managerslist;
import UserApp.Nft;

public class Nftperfilmaager extends Manager{
    private Nft nft;
    public Nftperfilmaager(Display display,Nft nft) {
        super(display);
        this.nft=nft;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand()=="Atras"){
            Display.getinstance().setManager(Managerslist.getlastManagerslist());
       }
    }

    @Override
    public void initialize() {
        panel.setSize(720,720);
        panel.setLayout(null);
        
        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new ImageIcon("src\\img\\flechaizquierda.png"));
        goBack.setActionCommand("Atras");
        goBack.addActionListener(this);
        panel.add(goBack);

        JPanel nftPanel = new JPanel();
        nftPanel.setBounds(120,90,430,550);
        nftPanel.setLayout(null);
        nftPanel.setBackground(new Color(196,196,196));
        panel.add(nftPanel);

        JLabel nftImage = new JLabel(new ImageIcon(nft.get_Image()));
        nftImage.setBounds(90,70,250,250);
        nftImage.setBackground(Color.WHITE);
        nftImage.setOpaque(true);
        nftPanel.add(nftImage);

        JLabel NombreNFT = createLabel(nft.get_nombre(),350,30);
        nftPanel.add(NombreNFT);

        JLabel valorNft = createLabel(nft.get_price(),380,20);
        nftPanel.add(valorNft);

        JLabel descriptionLabel = createLabel(nft.get_description(),410,20);
        nftPanel.add(descriptionLabel);
    }
    private JLabel createLabel(String texto, int posy, int tamaño){
        JLabel label = new JLabel(texto);
        label.setBounds(90,posy,400,60);
        label.setFont(new Font("Arial", Font.PLAIN, tamaño));
        return label;
    }
}

