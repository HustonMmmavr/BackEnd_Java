package lastunion.application.Views;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PasswordView {
    private String oldPassword;
    private String newPassword;

    @JsonCreator
    public PasswordView(@JsonProperty("oldPassword") String oldPassword,
                        @JsonProperty("newPassword") String newPassword){
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public boolean isFilled(){
        return newPassword != null && oldPassword != null;
    }
}
