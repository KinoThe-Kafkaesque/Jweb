package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    private static String login = "root";
    private static String password = "";
    private static String url = "jdbc:mysql://localhost/jweb";

//    private static String login = "bfb6cc87ff3c77";
//    private static String password = "bfb6cc87ff3c77";
//    private static String url = "jdbc:mysql://bfb6cc87ff3c77:8dea0359@eu-cdbr-west-02.cleardb.net/heroku_f85d4df7538d63f?reconnect=true";
    private Connection connection = null;
    private static Connexion instane;

    private Connexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver introvable");
        } catch (SQLException e) {
            System.out.println("Connexion errror");
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Connexion getInstane() {
        if (instane == null) {
            instane = new Connexion();
        }
        return instane;
    }

}
