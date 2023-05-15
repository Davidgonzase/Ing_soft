package UserApp;

public class Nft {
    private String nombre;
    private int id;
    private String precio;
    private String description;
    private byte[] image;
    public Nft(String nombre,String id, String description,String precio,byte[] image){
        this.id=Integer.parseInt(id);
        this.nombre=nombre;
        this.precio=precio;
        this.image= image;
        this.description=description;
    }
    public String get_nombre(){
        return nombre;
    }
    public String get_price(){
        return precio;
    }
    public String get_description(){
        return description;
    }
    public int get_id(){
        return id;
    }
    public byte[] get_Image(){
        return image;
    }
}

