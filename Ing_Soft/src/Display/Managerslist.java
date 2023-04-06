package Display;
import Managers.Loginmanager;
import Managers.Manager;
import Managers.Registermanager;

public enum Managerslist{
    LOGIN,REGISTER,PERFIL,SEARCH,NFT    ;
    public static Manager managerselect(Managerslist type,Display display){
        Manager manager;
        switch(type){
            case LOGIN:
                manager=(Loginmanager) new Loginmanager(display);
                manager.initialize();
                return manager;
            case REGISTER:
                manager=(Registermanager) new Registermanager(display);
                manager.initialize();
                return manager;
            default:
                return null;
        }
    }
}
