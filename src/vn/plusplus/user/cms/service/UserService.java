package vn.plusplus.user.cms.service;

import vn.plusplus.user.cms.interfaces.UserInterface;
import vn.plusplus.user.cms.model.User;

import java.util.List;

public class UserService implements UserInterface {

    @Override
    public List<User> readAllUserFromDB() {
        return null;
    }

    @Override
    public void saveUserToDB(User user) {

    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String passWord) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public void sendTokenToEmail(String email) {

    }

    @Override
    public User findUserByUserName(String userName) {
        return null;
    }

    @Override
    public List<User> findAllUserOderByScoreDesc() {
        return null;
    }
}
