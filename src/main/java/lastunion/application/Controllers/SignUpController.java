package lastunion.application.Controllers;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lastunion.application.Managers.AccountManager;

import javax.validation.constraints.NotNull;
@RestController
public class SignUpController {

    @NotNull
    private final MessageSource messageSource;
    @NotNull
    private final AccountManager accountManager;

    public SignUpController(@NotNull AccountManager accountManager, @NotNull MessageSource messageSource) {
        this.messageSource = messageSource;
        this.accountManager = accountManager;
    }
}
