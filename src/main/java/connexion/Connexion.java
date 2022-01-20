package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {


//    private static String login = "root";
//    private static String password = "";
//    private static String url = "jdbc:mysql://localhost/jweb";

    private static String login = "z2nzomr90aw4rj4g";
    private static String password = "ur239rkvjwa1qvmt";
    private static String url = "jdbc:mysql://esilxl0nthgloe1y.chr7pe7iynqr.eu-west-1.rds.amazonaws.com/ux4qc2qtc8ije7s6";
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
