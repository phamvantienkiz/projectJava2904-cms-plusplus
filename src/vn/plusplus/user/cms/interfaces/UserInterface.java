package vn.plusplus.user.cms.interfaces;

import vn.plusplus.user.cms.model.User;

import java.util.List;

public interface UserInterface {
    boolean checkUserByUserName(String userName);
    List<User> readAllUserFromDB();
    void saveUserToDB(User user);

    User findUserByUserNameAndPassword(String userName, String passWord);

    void sendEmailToResetPass(String email);

}
