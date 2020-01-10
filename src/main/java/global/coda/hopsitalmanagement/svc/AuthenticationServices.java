package global.coda.hopsitalmanagement.svc;

import global.coda.hopsitalmanagement.dao.impl.UserDbDaoImpl;
import global.coda.hopsitalmanagement.patientdetails.model.User;

import java.sql.SQLException;

/**
 * The type Authentication services.
 *
 * @author VC
 */
public class AuthenticationServices {
    private User user;

    /**
     * Provides The Login.
     *
     * @param email    is the email ID.
     * @param password is the password,
     * @return the login
     */
    public User login(String email, String password) {


        try {
            UserDbDaoImpl userDb = new UserDbDaoImpl();
            user = userDb.getUser(email);

            if (!(password.equals(user.getPassword()))) {
                user.setPassword(null);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}


