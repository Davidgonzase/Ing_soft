import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class perfilUser extends JFrame {
    public perfilUser(){
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

        JPanel userProfilePanel = new JPanel();
        userProfilePanel.setSize(720,720);
        userProfilePanel.setLayout(null);
        add(userProfilePanel);

        JLabel foto=new JLabel(/*new ImageIcon()*/);
        foto.setBounds(120,30,250,250);
        foto.setBackground(Color.WHITE);
        foto.setOpaque(true);
        userProfilePanel.add(foto);

        JLabel username = new JLabel("Username");
        username.setBounds(415,30,170,60);
        username.setFont(new Font("Arial", Font.PLAIN, 30));
        userProfilePanel.add(username);

        JButton Contactar = createButton(120, "Modificar");
        userProfilePanel.add(Contactar);

        JTextArea descripcion=new JTextArea("Hola soy Herculano y me la agarras con la mano");
        descripcion.setBounds(80,340,520,250);
        descripcion.setBackground(Color.WHITE);
        descripcion.setOpaque(true);
        userProfilePanel.add(descripcion);

        setVisible(true);
    }
    public JButton createButton(int posy, String texto){
        JButton jButton= new JButton(texto);
        jButton.setBounds(400,posy,170,60);
        jButton.setBackground(new Color(58,101,193));
        jButton.setForeground(Color.WHITE);
        return jButton;
    }
}
