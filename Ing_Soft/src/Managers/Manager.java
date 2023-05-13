package Managers;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import Display.Display;


public abstract class Manager implements ActionListener{
   public Display display;
   protected JPanel panel= new JPanel(); 
   public Manager(Display display){
      this.display=display;
      try {
         panel.setBackground(new Color(132,179,203));
          for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
         } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }
         panel.setLayout(null);
   }
   public abstract void initialize();
   public JPanel getJPanel(){
      return panel;
   }
    
}
