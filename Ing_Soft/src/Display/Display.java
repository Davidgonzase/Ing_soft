package Display;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.*;
import Managers.Manager;

public class Display{
    private static Display instance;
    private JFrame frame;
    private Managerslist type=null;
    private Manager manager=null;
    private Display(){
        frame=new JFrame("NFTrader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        setManager(Managerslist.LOGIN);
    }
    public static Display getinstance(){
        if(instance==null){
            instance=new Display();
        }
        return instance;
    }
    public void clean(JPanel panel){
        if(frame.getContentPane()!=null){
            frame.getContentPane().removeAll();
            frame.setContentPane(panel);
            SwingUtilities.updateComponentTreeUI(frame);
            frame.pack();
        }else{
            frame.setContentPane(panel);
            frame.pack();
        }
    }
    public void chgFrame(Dimension dimension,Boolean setresize){
        if(dimension!=null){
            frame.setSize(dimension);
        }
        if(setresize!=null){
            frame.setResizable(setresize);
        }
    }
    public Manager getManager(){
        return manager;
    }
    public void setManager(Managerslist type){
        this.type=type;
        manager=Managerslist.managerselect(type,this);
    }
    public Container getCP(){
        return frame.getContentPane();
    }
}
