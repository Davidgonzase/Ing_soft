package UserApp;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class User {
    private String username;
    private int id;
    private String email;
    private String description;
    private Image image;
    public User(String userId,String email, String username,String description,ByteArrayInputStream image){
        id=Integer.parseInt(userId);
        this.email=email;
        this.username=username;
        try {
            this.image= ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public Image get_Image(){
        return image;
    }
    
}
