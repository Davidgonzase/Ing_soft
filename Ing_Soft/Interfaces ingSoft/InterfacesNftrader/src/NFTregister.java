import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buscador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        setSize(720,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new BarraBotones();
        add(mainPanel);

        JButton goBack = new JButton();
        goBack.setBounds(171,0,30,30);
        goBack.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\InterfacesNftrader\\Images\\flechaizquierda.png"));
        mainPanel.add(goBack);

        JTextField nombrenft=generateTextField(150,"Nombre del NFT");
        mainPanel.add(nombrenft);

        JLabel valorLabel = new JLabel("Valor del NFT:");
        valorLabel.setBounds(230,250,100,30);
        mainPanel.add(valorLabel);

        //LO DE ABAJO ES PARA Q SOLO SE PUEDAN PONER NUMEROS EN EL TFIELD
        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        JFormattedTextField valorNFT = new JFormattedTextField(formatter);
        valorNFT.setBounds(310,250,360,30);
        mainPanel.add(valorNFT);

        JTextField creadorNFT=generateTextField(350,"Creador del NFT");
        mainPanel.add(creadorNFT);

        JTextField propietarioNFT=generateTextField(450,"Propietario del NFT");
        mainPanel.add(propietarioNFT);

        JButton registrarButton = new JButton("Registrar");
        registrarButton.setBounds(400,570,80,30);
        mainPanel.add(registrarButton);

        setVisible(true);
    }
    private JTextField generateTextField(int y, String message){
        JTextField textField = new JTextField(message);
        textField.setBounds(220,y,450,30);
        ponerTextFieldBonito(textField,message);
        return textField;
    }
    //FUNCION PARA GENERAR LOS LABEL QUE MARCA ERRORES
    private JLabel generateErrorLabel(int y, String message){
        JLabel label = new JLabel(message);
        label.setBounds(220,y,600,30);
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
