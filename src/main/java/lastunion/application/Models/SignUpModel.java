package lastunion.application.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SignUpModel {
    private String userName;
    private String userPassword;
    private String userEmail;

    @JsonCreator
    public SignUpModel(@JsonProperty("userName") String userName,
                       @JsonProperty("userPassword") String userPassword,
                       @JsonProperty("userEmail") String userEmail){
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public String getUserName(){
        return userName;
    }
    public String getUserPassword(){return userPassword;}
    public String getUserEmail() {return userEmail;}

    public boolean isFilledData() {
        return userName != null && userPassword != null && userEmail != null;
    }
}
