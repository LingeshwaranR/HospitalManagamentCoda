package global.coda.hopsitalmanagement.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *@author VC
 */
public class MysqlConnection {
    private final String connectionUrl = "jdbc:mysql://localhost:3306/hospital_db?useUnicode=true&characterEncoding=UTF-8";
    private Connection connection;

    /**
     * handles the DB Connection.
     * @throws SQLException handles the SQL exception.
     */
    public MysqlConnection() throws SQLException {

        connection =  DriverManager.getConnection(connectionUrl, "root", "root");

    }

    /**
     * Get Connection.
     * @return gives the connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * CLose the Connection.
     * @throws SQLException handles the Sql Exception.
     */
    public void closeConnection() throws SQLException {
        connection.close();
    }
}
