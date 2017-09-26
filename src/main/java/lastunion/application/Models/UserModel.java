package lastunion.application.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class UserModel {
    private Integer userId;
    @NotNull
    private String userLogin;
    @NotNull
    private String userEmail;
    @NotNull
    private String userPasswordHash;
    private Integer userHighScore;

    @JsonCreator
    UserModel(@JsonProperty() String name){

    }


}
