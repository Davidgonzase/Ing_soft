import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        JPanel catalogPanel = new JPanel();
        catalogPanel.setBounds(170,0,720,720);
        catalogPanel.setLayout(null);
        add(catalogPanel);

        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setSize(600,595);
        resultadosPanel.setLayout(null);
        resultadosPanel.setBackground(new Color(196,196,196));

        JScrollPane scrollPane = new JScrollPane(resultadosPanel);
        scrollPane.setBounds(50,0,600,595);
        catalogPanel.add(scrollPane);

        JButton añadir = createButton(100,"Añadir");
        catalogPanel.add(añadir);

        JButton eliminar = createButton(420,"Eliminar");
        catalogPanel.add(eliminar);

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
