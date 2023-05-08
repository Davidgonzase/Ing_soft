import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NFTregister extends JFrame {
    public NFTregister(){
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

        JPanel NFTregisterPanel = new JPanel();
        NFTregisterPanel.setSize(720,720);
        NFTregisterPanel.setLayout(null);
        add(NFTregisterPanel);

        JButton goBack = new JButton();
        goBack.setBounds(0,0,30,30);
        goBack.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\InterfacesNftrader\\Images\\flechaizquierda.png"));
        NFTregisterPanel.add(goBack);

        JTextField nombrenft=generateTextField(150,"Nombre del NFT");
        NFTregisterPanel.add(nombrenft);

        JLabel valorLabel = new JLabel("Valor del NFT:");
        valorLabel.setBounds(129,250,100,30);
        NFTregisterPanel.add(valorLabel);

        //LO DE ABAJO ES PARA Q SOLO SE PUEDAN PONER NUMEROS EN EL TFIELD
        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        JFormattedTextField valorNFT = new JFormattedTextField(formatter);
        valorNFT.setBounds(209,250,360,30);
        NFTregisterPanel.add(valorNFT);

        JTextField creadorNFT=generateTextField(350,"Creador del NFT");
        NFTregisterPanel.add(creadorNFT);

        JTextField propietarioNFT=generateTextField(450,"Propietario del NFT");
        NFTregisterPanel.add(propietarioNFT);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBounds(299,570,80,30);
        NFTregisterPanel.add(registrarButton);

        setVisible(true);
    }
    private JTextField generateTextField(int y, String message){
        JTextField textField = new JTextField(message);
        textField.setBounds(119,y,450,30);
        ponerTextFieldBonito(textField,message);
        return textField;
    }
    //FUNCION PARA GENERAR LOS LABEL QUE MARCA ERRORES
    private JLabel generateErrorLabel(int y, String message){
        JLabel label = new JLabel(message);
        label.setBounds(121,y,600,30);
        label.setForeground(new Color(197,40,45));
        return label;
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
}
