package UserApp;

import javax.swing.ImageIcon;

public class User {
    private String username;
    private int id;
    private String email;
    private String description;
    private ImageIcon image;
    public User(String userId,String email, String username,String description,byte[] image){
        id=Integer.parseInt(userId);
        this.email=email;
        this.username=username;
        this.image= new ImageIcon(image);
        this.description=description;
    }
    public String get_username(){
        return username;
    }
    public String get_email(){
        return email;
    }
    public String get_description(){
        return description;
    }
    public int get_id(){
        return id;
    }
    public ImageIcon get_Image(){
        return image;
    }
    
}
