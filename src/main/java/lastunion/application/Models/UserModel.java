package lastunion.application.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class UserModel {
    private Integer userId;
    private String userName;
    private String userEmail;
    private String userPasswordHash;
    private Integer userHighScore;

    @JsonCreator
    public UserModel(@JsonProperty() String name){

    }

    public UserModel(String name, String passwordHash){
        userName = name;
        userPasswordHash = passwordHash;
    }

    public UserModel(){

    }

    /*
    Getters and Setters
     */

    public Integer getUserId(){
        return userId;
    }

    public String getName(){
        return userName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserPasswordHash(){
        return userPasswordHash;
    }

    Integer getUserHighScore(){
        return userHighScore;
    }

    void setUserId(Integer userId) {
        this.userId = userId;
    }

    void setUserLogin(String userName){
        this.userName = userName;
    }

    void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    void setUserPasswordHash(String userPasswordHash){
        this.userPasswordHash = userPasswordHash;
    }

    void setUserHighScore(Integer userHighScore){
        this.userHighScore = userHighScore;
    }

}
