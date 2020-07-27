package vn.plusplus.user.cms;

import vn.plusplus.user.cms.model.User;
import vn.plusplus.user.cms.service.UserService;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws Exception{
        boolean ketThuc = true;
        while (ketThuc){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nhap lua chon su dung:");
            System.out.println("1. Dang ky\n" +
                    "2. Dang nhap\n" +
                    "3. Reset password\n" +
                    "4. Tim kiem\n" +
                    "5. Ket thuc");

            int option = scanner.nextInt();
            scanner.nextLine();
            UserService service = new UserService();
            switch (option){
                case 1:
                    System.out.println("Nhap username dang ky: ");
                    String userNameRegister = scanner.nextLine();
                    User userRe = service.findUserByUserName(userNameRegister);
                    if(userRe != null){
                        System.out.println("Ten dang nhap da ton tai, moi ban chon ten khac");
                    } else {
                        userRe = new User();
                        System.out.println("Nhap vao fullName:");
                        String fullName =scanner.nextLine();
                        service.saveUserToDB(userRe);
                    }
                    break;
                case 2:

                case 5:
                    ketThuc = false;

            }
        }

    }

}
