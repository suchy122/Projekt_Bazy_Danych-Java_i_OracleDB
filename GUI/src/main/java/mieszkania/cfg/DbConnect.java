package mieszkania.cfg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
    protected Connection connection;

    public Connection getConnection() {
        final String connectionString="jdbc:oracle:thin:mieszkania/qwerty123@//localhost:1521/xepdb1";
        try{
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
