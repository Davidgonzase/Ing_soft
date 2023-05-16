package UserApp;

public class Messages {
    private String message;
    private boolean escrito;

    public Messages(String mensaje){
        message=mensaje;
        escrito=false;
    }

    public String getMessage() {
        return message;
    }

    public void setEscrito(boolean escrito) {
        this.escrito = escrito;
    }

    public boolean getEscrito() {
        return escrito;
    }
}
