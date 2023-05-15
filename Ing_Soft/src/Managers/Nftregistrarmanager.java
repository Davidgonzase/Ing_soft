package Managers;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import Display.Display;
import UserApp.App;

public class Nftregistrarmanager extends Manager{
    private JButton button;
    private JLabel label;
    private JFileChooser fc=null;
    private byte[] bytes=null;
    private JTextField nombrenft;
    public Nftregistrarmanager(Display display) {
        super(display);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            int returnVal = fc.showOpenDialog(Display.getinstance().getCP());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                pasarImagen_a_byte(file);
                label.setText("Imagen: "+file.getName());
            }
        }else if(e.getActionCommand()=="Registrar"){
            if(bytes!=null){
                System.out.println(App.newnft(nombrenft.getText(),"Nula", String.valueOf(App.get_user().get_id()),bytes));
            }
        }
    }

    @Override
    public void initialize() {
        panel.setSize(720,720);
        panel.setLayout(null);

        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/flechaizquierda.png")));
        panel.add(goBack);

        nombrenft=generateTextField(50,"Nombre del NFT");
        panel.add(nombrenft);

        JLabel valorLabel = new JLabel("Valor del NFT:");
        valorLabel.setBounds(129,150,100,30);
        panel.add(valorLabel);

        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        JFormattedTextField valorNFT = new JFormattedTextField(formatter);
        valorNFT.setBounds(209,150,360,30);
        panel.add(valorNFT);

        JTextField creadorNFT=generateTextField(250,"Creador del NFT");
        panel.add(creadorNFT);

        JTextField propietarioNFT=generateTextField(350,"Propietario del NFT");
        panel.add(propietarioNFT);

        label= new JLabel("Imagen: ");
        label.setOpaque(true);
        label.setBackground(Color.white);
        label.setBounds(119,450,400,30);
        panel.add(label);

        fc = new JFileChooser();

        button = new JButton();
        button.setBounds(519,450,50,30);
        button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/carpeta.png")));
        button.addActionListener(this);
        panel.add(button);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBounds(299,570,80,30);
        registrarButton.addActionListener(this);
        panel.add(registrarButton);
    }
    private JTextField generateTextField(int y, String message){
        JTextField textField = new JTextField(message);
        textField.setBounds(119,y,450,30);
        ponerTextFieldBonito(textField,message);
        return textField;
    }
    private void ponerTextFieldBonito(JTextField textField, String string){
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(string)) {
                    textField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setText(string);
                }
            }
        });
    }

    public void pasarImagen_a_byte(File file){
        try {
            BufferedImage img=ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "png", baos);
            baos.flush();
            bytes = baos.toByteArray();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
