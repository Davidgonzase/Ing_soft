import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NFTregister extends JFrame {
    public void pasarImagen_a_byte(File file){
        try {
            // Cargar la imagen desde la ruta
            BufferedImage image = ImageIO.read(file);

            // Crear un stream de salida de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // Escribir la imagen en el stream de salida de bytes en formato PNG
            ImageIO.write(image, "png", baos);

            // Obtener los bytes del stream de salida de bytes
            byte[] bytes = baos.toByteArray();
            System.out.print("BBBBBBBBBBBBBBBBBBBBBBBBb");
            // AQUI PONER SENTENCIA PARA MANDAR EL ARR DE BYTES A LA BASE DE DATOS
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public File arraybyte_a_file(byte[] bytes){
        File output = null;
        try {
            // Crear un stream de entrada de bytes desde el array
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

            // Leer la imagen desde el stream de entrada de bytes
            BufferedImage image = ImageIO.read(bais);

            // Guardar la imagen en un archivo
            output = new File("imagen.png");
            ImageIO.write(image, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }
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

        JTextField nombrenft=generateTextField(50,"Nombre del NFT");
        NFTregisterPanel.add(nombrenft);

        JLabel valorLabel = new JLabel("Valor del NFT:");
        valorLabel.setBounds(129,150,100,30);
        NFTregisterPanel.add(valorLabel);

        //LO DE ABAJO ES PARA Q SOLO SE PUEDAN PONER NUMEROS EN EL TFIELD
        NumberFormatter formatter = new NumberFormatter();
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        JFormattedTextField valorNFT = new JFormattedTextField(formatter);
        valorNFT.setBounds(209,150,360,30);
        NFTregisterPanel.add(valorNFT);

        JTextField creadorNFT=generateTextField(250,"Creador del NFT");
        NFTregisterPanel.add(creadorNFT);

        JTextField propietarioNFT=generateTextField(350,"Propietario del NFT");
        NFTregisterPanel.add(propietarioNFT);

        JLabel label= new JLabel("Imagen: ");
        label.setOpaque(true);
        label.setBackground(Color.white);
        label.setBounds(119,450,400,30);
        NFTregisterPanel.add(label);

        final JFileChooser fc = new JFileChooser();

        JButton button = new JButton();
        button.setBounds(519,450,50,30);
        button.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\InterfacesNftrader\\Images\\carpeta.png"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button) {
                    int returnVal = fc.showOpenDialog(NFTregister.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        pasarImagen_a_byte(file);
                        label.setText("Imagen: "+file.getName());
                    } else {
                        System.out.println("Open command cancelled by user.");
                    }
                }
            }
        });
        NFTregisterPanel.add(button);

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
