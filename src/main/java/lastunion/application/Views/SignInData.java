package lastunion.application.Views;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class SignInData {
    private final String userName;
    private final String userPassword;

    @JsonCreator
    SignInData(@JsonProperty("userName") String userName,
               @JsonProperty("userPassword") String userPassword){
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public final String getUserName(){
        return userName;
    }
    public final String getUserPassword(){ return userPassword;}
}
