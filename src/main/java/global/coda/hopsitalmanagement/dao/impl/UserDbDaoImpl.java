package global.coda.hopsitalmanagement.dao.impl;

import global.coda.hopsitalmanagement.dao.UserDbDao;
import global.coda.hopsitalmanagement.dbconnection.MysqlConnection;
import global.coda.hopsitalmanagement.patientdetails.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User db dao.
 */
public class UserDbDaoImpl implements UserDbDao {
    @Override
    public User getUser(String email) throws SQLException {
        User user = new User();
        MysqlConnection connection = new MysqlConnection();

        Connection sqlConnection = connection.getConnection();
        PreparedStatement statement = sqlConnection.prepareStatement("select * from t_user where email=?  and is_active=?");
        statement.setString(1, email);
        statement.setInt(2, 1);

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            user.setUserId(resultSet.getInt(1));
            user.setUsername(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setRoleId(resultSet.getInt(5));
        }
        connection.closeConnection();

        return user;
    }
}
