package lastunion.application.Controllers;


import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lastunion.application.Managers.AccountManager;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Locale;

@CrossOrigin(origins = "")
@RestController
public class SignInController {
    @NotNull
    private final MessageSource messageSource;
    @NotNull
    private final AccountManager accountManager;

    public SignInController(@NotNull AccountManager accountManager, @NotNull MessageSource messageSource) {
        this.messageSource = messageSource;
        this.accountManager = accountManager;
    }


    // TODO Response on route

}