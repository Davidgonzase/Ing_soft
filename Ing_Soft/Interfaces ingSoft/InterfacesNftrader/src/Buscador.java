import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Buscador extends JFrame {
    public Buscador(){
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

        JTextField buscador = new JTextField("Buscar");
        buscador.setBounds(240,40,400,30);
        buscador.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (buscador.getText().equals("Buscar")) {
                    buscador.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (buscador.getText().isEmpty()) {
                    buscador.setText("Buscar");
                }
            }
        });
        mainPanel.add(buscador);

        JButton buscarButton=new JButton();
        buscarButton.setBounds(640,40,30,30);
        buscarButton.setIcon(new ImageIcon("C:\\Users\\Carlos\\Desktop\\IGENIERÍA DE SOFTWARE\\InterfacesNftrader\\Images\\lupa.png"));
        mainPanel.add(buscarButton);

        JPanel resultadosPanel = new JPanel();
        resultadosPanel.setSize(535,595);
        resultadosPanel.setLayout(null);
        resultadosPanel.setBackground(new Color(196,196,196));

        JScrollPane scrollPane = new JScrollPane(resultadosPanel);
        scrollPane.setBounds(171,90,535,595);
        mainPanel.add(scrollPane);

        setVisible(true);
    }
}
