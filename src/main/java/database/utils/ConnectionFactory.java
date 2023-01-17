package database.utils;

import java.sql.*;

public class ConnectionFactory {
    private static Connection connection;

    public static synchronized Connection createConnection() throws SQLException{
        if(connection == null){
            connection = DriverManager.getConnection("jdbc:sqlite:grupoDeAmigos.db");
        }
        return connection;
    }

    public static PreparedStatement createPreparedStatemment(String sql) throws SQLException {
        return createConnection().prepareStatement(sql);
    }

    public static Statement createStatement() throws SQLException {
        return createConnection().createStatement();
    }

}
