package vn.plusplus.user.cms;

import vn.plusplus.user.cms.service.UserService;

public class Application {

    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.findUserByUserName("kiemnx");
    }

}
