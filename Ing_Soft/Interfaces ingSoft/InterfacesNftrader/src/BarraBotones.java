import javax.swing.*;
import java.awt.*;

//PARA IMPLEMENTAR ESTO EN CUALQUIER INTERFAZ SIMPLEMENTE AÑADIR mainPanel.add(new BarraBotones());

public class BarraBotones extends JPanel {
    public BarraBotones(){
        setBounds(0,0,720,720);
        setLayout(null);
        setBackground(new Color(192,192,192));

        JLabel decoracion = new JLabel();
        decoracion.setBounds(0,0,170,60);
        decoracion.setBackground(Color.red);
        decoracion.setOpaque(true);
        add(decoracion);

        JButton perfilButton= createButton(100,"Perfil");
        add(perfilButton);

        JButton catalogoButton= createButton(170,"Mi catálogo");
        add(catalogoButton);

        JButton chatsButton= createButton(240,"Chats");
        add(chatsButton);

        JButton busquedaButton= createButton(310,"Busqueda");
        add(busquedaButton);

        JButton cerrarButton= createButton(610,"Cerrar sesión");
        add(cerrarButton);

        JLabel backdecoracion = new JLabel();
        backdecoracion.setBounds(0,0,170,720);
        backdecoracion.setBackground(Color.white);
        backdecoracion.setOpaque(true);
        add(backdecoracion);

        setVisible(true);
    }
    public JButton createButton(int posy, String texto){
        JButton jButton= new JButton(texto);
        jButton.setBounds(0,posy,170,60);
        return jButton;
    }
}
