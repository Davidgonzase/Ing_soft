package Display;
import javax.swing.JPanel;

import Managers.Buscadormanager;
import Managers.Catalogomanager;
import Managers.Chatsmanager;
import Managers.Dualsmanager;
import Managers.Loginmanager;
import Managers.Manager;
import Managers.Perfilmanager;
import Managers.Registermanager;
import UserApp.App;

public enum Managerslist{
    LOGIN(0),REGISTER(1),DUAL(2),PERFIL(3),CATALOGO(4),CHATS(5),BUSQUEDA(6);
    private int number;
    private Managerslist(int number){
        this.number=number;
    }
    private static Managerslist currentManager=null;
    private static Managerslist lastManager=null;
    private static Manager[] managers=new Manager[7];
    private static JPanel[] panels=new JPanel[3];
    private static JPanel[] sJPanels=new JPanel[7];
    public static void managerselect(Managerslist type,Display display){
        currentManager=type;
        if(type.number<3){
            Manager manager=null;
            if(managers[type.number]==null){
                manager=managerfactory(type.number,display);
                manager.initialize();
                managers[type.number]=manager;
                panels[type.number]=manager.getJPanel();
            }else{
                manager=managers[type.number];
                display.clean(panels[type.number]);
                display.getCP().setVisible(true);
            }
        }else{
            if(managers[DUAL.number]!=null){
                Dualsmanager manager=null;
                manager=(Dualsmanager)managers[DUAL.number];
                Manager submanager=managerfactory(type.number, display);
                submanager.initialize();
                manager.clean(submanager.getJPanel());
            }
        }
    }
    private static Manager managerfactory(int id,Display display){
        switch(id){
            case 0:
                return new Loginmanager(display);
            case 1:
                return new Registermanager(display);
            case 2:
                return new Dualsmanager(display);
            case 3:
                return new Perfilmanager(display,App.get_user());
            case 4:
                return new Catalogomanager(display);
            case 5:
                return new Chatsmanager(display);
            case 6:
                return new Buscadormanager(display);
            default:
                return null;
        }
    }
}