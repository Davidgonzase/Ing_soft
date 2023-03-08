import java.sql.*;

class Server{
    public Server(){
        
    }
    private void setconnection(String url,String user, String password){
        try {
            Connection con=DriverManager.getConnection(url,user,password);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}