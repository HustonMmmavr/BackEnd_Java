package lastunion.application.Controllers;

import lastunion.application.Managers.UserManager;
import lastunion.application.Models.SignUpModel;
import lastunion.application.Views.ResponseCode;
import lastunion.application.Views.SignUpData;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.Locale;


@RestController
public class SignUpController {
    @NotNull
    private final MessageSource messageSource;
    @NotNull
    private final UserManager userManager;

    public SignUpController(@NotNull UserManager userManager,@NotNull MessageSource messageSource) {
        this.messageSource = messageSource;
        this.userManager = userManager;
    }

    @RequestMapping(path="/api/user/signup", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseCode> getMessage(@RequestBody SignUpData body, HttpSession httpSession) {
        @SuppressWarnings("LocalVariableNamingConvention")
        final SignUpModel signUpUser = new SignUpModel(body.getUserName(), body.getUserPassword(), body.getUserEmail());
        final UserManager.ResponseCode responseCode = userManager.signUpUser(signUpUser);

        //noinspection EnumSwitchStatementWhichMissesCases
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
    }}
