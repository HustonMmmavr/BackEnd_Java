package lastunion.application.Controllers;

import lastunion.application.Managers.UserManager;
import lastunion.application.Models.SignInModel;
import lastunion.application.Views.ResponseCode;
import lastunion.application.Views.SignInData;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Locale;

@CrossOrigin(origins = "")
@RestController
public class SignInController {
    @NotNull
    private final MessageSource messageSource;
    @NotNull
    private final UserManager userManager;

    public SignInController(@NotNull UserManager userManager, @NotNull MessageSource messageSource) {
        this.messageSource = messageSource;
        this.userManager = userManager;
    }

    @RequestMapping(path="/api/user/signin", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE
                    )
    public ResponseEntity<ResponseCode> getMessage(@RequestBody SignInData body, HttpSession httpSession) {
        @SuppressWarnings("LocalVariableNamingConvention")
        final SignInModel signinUser = new SignInModel(body.getUserName(), body.getUserPassword());
        final UserManager.ResponseCode responseCode = userManager.signInUser(signinUser);

        switch (responseCode) {

            case INCORRECT_AUTH_DATA: {
                return new ResponseEntity<>(new ResponseCode(false,
                        messageSource.getMessage("msgs.bad_request", null, Locale.ENGLISH)),
                        HttpStatus.BAD_REQUEST);
            }

            case INCORRECT_LOGIN:
            case INCORRECT_PASSWORD: {
                return new ResponseEntity<>(new ResponseCode(false,
                        messageSource.getMessage("msgs.forbidden", null, Locale.ENGLISH)),
                        HttpStatus.FORBIDDEN);
            }

            case OK: {
                httpSession.setAttribute("userLogin", body.getUserName());
                return new ResponseEntity<>(new ResponseCode(true,
                        messageSource.getMessage("msgs.ok", null, Locale.ENGLISH)),
                        HttpStatus.OK);
            }

            default: {
                return new ResponseEntity<>(new ResponseCode(false,
                        messageSource.getMessage("msgs.internal_server_error", null, Locale.ENGLISH)),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}