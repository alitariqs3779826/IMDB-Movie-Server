package app.controller;

import app.dao.AccountDAO;
import app.model.Account;
import org.mindrot.jbcrypt.BCrypt;





public class UserController {



    // Authenticate the user by hashing the inputted password using the stored salt,
    // then comparing the generated hashed password to the stored hashed password
    public static boolean authenticate(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Account user = AccountDAO.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        //M3
        String status = AccountDAO.getStatusByUserName(username);
        /**
         * What is the "salt"? You can read more in here:
         * https://www.baeldung.com/java-password-hashing
         */
        String hashedPassword = BCrypt.hashpw(password, AccountDAO.SALT);
        boolean pwd = hashedPassword.equals(user.getPassword());
        boolean accountStatus = !status.equals("pending");
        return (pwd && accountStatus);
    }




    public static void setPassword(String username, String oldPassword, String newPassword) {
        if (authenticate(username, oldPassword)) {
            String newSalt = BCrypt.gensalt();
            String newHashedPassword = BCrypt.hashpw(newSalt, newPassword);

            // Update the user salt and password
        }
    }

}
