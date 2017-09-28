package lastunion.application.Managers;

import lastunion.application.DAO.UserDAO;
import lastunion.application.Models.SignInModel;
import lastunion.application.Models.SignUpModel;

import lastunion.application.Models.UserModel;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
        INCORRECT_REG_DATA,
        DATABASE_ERROR
    };

    // Work with password
    ////////////////////////////////////////////////////////////////////////
    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public String makePasswordHash(final String password) {
        return passwordEncoder().encode(password);
    }

    public boolean checkPassword(final String password, final String passwordHash){
        return passwordEncoder().matches(password, passwordHash);
    }
    //////////////////////////////////////////////////////////////////////////

    public ResponseCode signInUser(@NotNull SignInModel signInUserData) {

        // Check sigInModel for empty fields
        if (signInUserData.isFilledData())
            return ResponseCode.INCORRECT_AUTH_DATA;

        // Check user storaged in database
        try {
            UserModel savedUser = userDAO.getUserByName(signInUserData.getUserName());

            // wrong password
            if (!checkPassword(savedUser.getUserPasswordHash(), signInUserData.getUserPassword()))
                return ResponseCode.INCORRECT_PASSWORD;
        }
        // no user, storaged in database
        catch(EmptyResultDataAccessException ex) {
            return ResponseCode.INCORRECT_LOGIN;
        }
        // error in work with db
        catch(DataAccessException ex){
            return ResponseCode.DATABASE_ERROR;
        }
        return ResponseCode.OK;
    }

    public ResponseCode signUpUser(SignUpModel signUpUserData) {

        // Check signUpModel for empty fields
        if (!signUpUserData.isFilledData())  {
            return ResponseCode.INCORRECT_REG_DATA;
        }

        // Creating UserModel to stoarage
        UserModel newUser = new UserModel(signUpUserData);
        newUser.setUserPasswordHash(makePasswordHash(signUpUserData.getUserPassword()));

        // trying to save user
        try {
            userDAO.saveUser(newUser);
        }
        // user with this login exist
        catch(DuplicateKeyException dupEx) {
            return ResponseCode.LOGIN_IS_BUSY;
        }
        // error in work with db
        catch(DataAccessException daEx) {
            return ResponseCode.DATABASE_ERROR;
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
