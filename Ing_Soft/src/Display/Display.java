package Display;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.*;

public class Display{
    private static Display instance;
    private JFrame frame;
    private Managerslist currentManager;

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
    public void clean(JComponent panel){
        if(frame.getContentPane()!=null){
            frame.getContentPane().setVisible(false);
            SwingUtilities.updateComponentTreeUI(frame);
            frame.setContentPane(panel);
        }else{
            frame.setContentPane(panel);
        }
    }

    public void reload(){
        SwingUtilities.updateComponentTreeUI(frame);
    }
    
    public void chgFrame(Dimension dimension,Boolean setresize){
        if(dimension!=null){
            frame.setSize(dimension);
        }
        if(setresize!=null){
            frame.setResizable(setresize);
        }
    }
    public void setManager(Managerslist type){
        currentManager=type;
        Managerslist.managerselect(type,this);
    }
    public Container getCP(){
        return frame.getContentPane();
    }
    public Managerslist gManagerslist(){
        return currentManager;
    }
    public void dispose(){
        frame.setVisible(false);
        this.dispose();
    }
}
