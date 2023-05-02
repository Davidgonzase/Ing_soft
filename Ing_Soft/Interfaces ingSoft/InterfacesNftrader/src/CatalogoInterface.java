import javax.swing.*;
import java.awt.*;

public class CatalogoInterface extends JFrame {
    public CatalogoInterface(){
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

        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setSize(535,595);
        resultadosPanel.setLayout(null);
        resultadosPanel.setBackground(new Color(196,196,196));

        JScrollPane scrollPane = new JScrollPane(resultadosPanel);
        scrollPane.setBounds(171,0,535,595);
        mainPanel.add(scrollPane);

        JButton añadir = createButton(180,"Añadir");
        mainPanel.add(añadir);

        JButton eliminar = createButton(500,"Eliminar");
        mainPanel.add(eliminar);

        setVisible(true);
    }
    public JButton createButton(int posx, String texto){
        JButton jButton= new JButton(texto);
        jButton.setBounds(posx,600,170,60);
        jButton.setFont(new Font("Arial", Font.PLAIN, 30));
        jButton.setBackground(Color.RED);
        jButton.setForeground(Color.white);
        return jButton;
    }
}
