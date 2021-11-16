package util;

import model.UserPassURL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectingToMyDatabase {

    private static ConnectingToMyDatabase dbIsntance;
    private Connection con;


    private ConnectingToMyDatabase() {
    }

    public static ConnectingToMyDatabase getInstance(){
        if(dbIsntance==null){
            dbIsntance= new ConnectingToMyDatabase();
        }
        return dbIsntance;
    }

    public Connection getConnection(){
        if(con==null){
            try {
                con = DriverManager.getConnection(UserPassURL.URL1.getValue(), UserPassURL.USER1.getValue(), UserPassURL.PASSWORD1.getValue());
            } catch (SQLException ex) {
                Logger.getLogger(ConnectingToMyDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return con;
    }
}
