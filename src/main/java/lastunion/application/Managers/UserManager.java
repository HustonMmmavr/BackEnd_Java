package lastunion.application.Managers;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lastunion.application.DAO.UserDAO;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Locale;

public class UserManager {
    @NotNull
    private UserDAO userDAO;

    public UserManager() {
    }
}
