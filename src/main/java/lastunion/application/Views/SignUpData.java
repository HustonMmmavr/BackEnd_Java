package lastunion.application.Views;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpData {
    private final String userName;
    private final String userPassword;
    private final String userEmail;

    @JsonCreator
    SignUpData(@JsonProperty("name") String userName, @JsonProperty("pass") String userPassword,
               @JsonProperty("email") String userEmail){
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public final String getUserName(){
        return userName;
    }
    public final String getUserPassword(){ return userPassword; }
    public final String getUserEmail() {return userEmail; }

}
