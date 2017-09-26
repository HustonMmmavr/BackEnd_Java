package lastunion.application.Managers;

import lastunion.application.DAO.UserDAO;
import lastunion.application.Models.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class UserManager {
    @NotNull
    private UserDAO userDAO;

    public enum ResponseCode {
        OK,
        LOGIN_IS_BUSY,
        INCORRECT_LOGIN,
        INCORRECT_PASSWORD,
        INCORRECT_SESSION,
        INCORRECT_AUTH_DATA,
        INCORREXT_REG_DATA,
        DATABASE_ERROR
    };

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Public method to create hash password
    public String makePasswordHash(final String password) {
        return passwordEncoder().encode(password);
    }

    public boolean checkPassword(final String password, final String passwordHash){
        return passwordEncoder().matches(password, passwordHash);
    }

    public ResponseCode signinUser(UserModel user) {
        try {
            userDAO.getUserByName(user.getName());
        }
        catch(Exception e) //TODO
        {

        }
        return ResponseCode.OK;
    }

    public ResponseCode signupUser(UserModel user) {
        try {
            userDAO.getUserByName(user.getName());

        }
        catch(Exception e) //TODO
        {

        }
        return ResponseCode.OK;
    }

    public ResponseCode changeUserEmail(UserModel user, String newEmail){

        return ResponseCode.OK;
    }

    public ResponseCode changeUserName(){
        return ResponseCode.OK;
    }

    public ResponseCode signUpUser(){
        return ResponseCode.OK;
    }

    public ResponseCode getUserByName(String userName, UserModel user){
        return ResponseCode.OK;
    }

    public ResponseCode getUserById(Integer userId, UserModel user){
        return ResponseCode.OK;
    }
}
