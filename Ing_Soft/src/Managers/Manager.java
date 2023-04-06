package Managers;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import Display.Display;

public abstract class Manager implements ActionListener{
    public Display display;
    public JPanel panel= new JPanel(); 
    public Manager(Display display){
        this.display=display;
        panel.setBackground(new Color(85,86,83));
    }
    public abstract void initialize();
}
