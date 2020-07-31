package vn.plusplus.user.cms.interfaces;

import vn.plusplus.user.cms.model.User;

import java.io.IOException;
import java.util.List;

public interface UserInterface {

    List<User> readAllUserFromDB();

    // Register feature
//    User findUserByUserName(String userName);
    void saveUserToDB(User user) throws IOException;

    // Login feature
    User findUserByUserNameAndPassword(String userName, String passWord);

    // Reset password
    User getUserByEmail(String email);
    String sendTokenToEmail(String email);

    // Search
    User findUserByUserName(String userName);
    List<User> findAllUserOderByScoreDesc();

}
