package lastunion.application.DAO;

import lastunion.application.Models.UserModel;
//import org.springframework.boot.autoconfigure.jdbc;

public class UserDAO {

    //JdbcTemplate jdbcTemplate;
    private static final String dbName = "";
    private static final String tableName = "";

    public UserModel getUserById(Integer id)  {
        //UserModel user = new UserModel();
        return null;
    }

    public UserModel getUserByName(String name)  {
        //UserModel user = new UserModel();
        return null;
    }

    public void modifyUser(UserModel user, UserModel changedUser) {
        String query = "UPDA";
        executeQuery(query);
    }

    public void saveUser(UserModel user)  {
        String query = "INSERT INTO ";
    }

    public void deleteUser(UserModel user){

    }

//    private void InsertData()
//
    private void executeQuery(String query)  {

    }

}
