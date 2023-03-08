import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loginmanager implements ActionListener{
   @Override
   public void actionPerformed(ActionEvent e){
      if(e.getActionCommand()==Buttons.LOGIN.toString()){
         Display.getinstance().Register();
      }
   }
}