package vn.plusplus.user.cms.interfaces;

import vn.plusplus.user.cms.model.User;

import java.util.List;

public interface UserInterface {

    List<User> readAllUserFromDB();

    // Register feature
//    boolean checkUserByUserName(String userName);
    void saveUserToDB(User user);

    // Login feature
    User findUserByUserNameAndPassword(String userName, String passWord);

    // Reset password
        User getUserByEmail(String email);
    void sendTokenToEmail(String email);

    // Search
    User findUserByUserName(String userName);
    List<User> findAllUserOderByScoreDesc();

}
