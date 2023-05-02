import javax.swing.*;
import java.awt.*;

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

        JLabel foto=new JLabel(/*new ImageIcon()*/);
        foto.setBounds(250,30,250,250);
        foto.setBackground(Color.WHITE);
        foto.setOpaque(true);
        mainPanel.add(foto);

        JLabel username = new JLabel("Username");
        username.setBounds(515,30,170,60);
        username.setFont(new Font("Arial", Font.PLAIN, 30));
        mainPanel.add(username);

        JButton catalogo = createButton(100, "Catalogo");
        mainPanel.add(catalogo);

        JButton Contactar = createButton(170, "Contactar");
        mainPanel.add(Contactar);

        JLabel descripcion=new JLabel();
        descripcion.setBounds(180,340,520,250);
        descripcion.setBackground(Color.WHITE);
        descripcion.setOpaque(true);
        mainPanel.add(descripcion);

        setVisible(true);
    }
    public JButton createButton(int posy, String texto){
        JButton jButton= new JButton(texto);
        jButton.setBounds(501,posy,170,60);
        jButton.setBackground(Color.RED);
        jButton.setForeground(Color.CYAN);
        return jButton;
    }
}
