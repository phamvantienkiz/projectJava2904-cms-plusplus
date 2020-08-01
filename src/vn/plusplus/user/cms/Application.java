package vn.plusplus.user.cms;

import vn.plusplus.user.cms.model.User;
import vn.plusplus.user.cms.service.UserService;

import java.util.List;
import java.util.Scanner;

public class Application {

    public static boolean login(String userName, String passWord)
    {
        UserService service = new UserService();
        List<User> users = service.readAllUserFromDB();
        for(int i=0;i<users.size();i++)
        {
            if(users.get(i).getUserName().equals(userName) && users.get(i).getPassword().equals(passWord))
            {
                    return true;
            }

        }
        return false;
    }

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

            int option = scanner.nextInt();scanner.nextLine();
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
                        userRe.setUserName(userNameRegister);//new
                        System.out.println("Nhap vao fullName:");
                        String fullName =scanner.nextLine();
                        userRe.setFullName(fullName);//new
                        System.out.println("Nhập mật khẩu: ");//new
                        String passWord = scanner.nextLine();//new
                        userRe.setPassword(passWord);//new
                        System.out.println("Nhập Email: ");//new
                        String email = scanner.nextLine();//new
                        userRe.setEmail(email);//new
                        System.out.println("Nhập phone: ");//new
                        String phone = scanner.nextLine();//new
                        userRe.setPhone(phone);//new
                        service.saveUserToDB(userRe);
                    }
                    break;
                case 2:

                    System.out.println(" Nhập UserName: ");
                    String userName =scanner.nextLine();

                    System.out.println(" Nhập Password: ");
                    String passWord = scanner.nextLine();
                    if(login(userName,passWord) == true)
                    {
                        System.out.println(" Đăng nhập thành công!");
                    }
                    else
                    {
                        System.out.println(" Sai tên tài khoản hoặc mật khẩu!");
                    }
                    break;
                case 3:
                    System.out.println("Nhập Email đã đăng kí: ");
                    String email = scanner.nextLine();
                    User findUserEmai = service.getUserByEmail(email);
                    if (findUserEmai != null){
                        String tokenCorrect = service.sendTokenToEmail(email);
                        System.out.println("Kiểm tra Email và nhập mã đổi mật khẩu: ");
                        System.out.println("Nhập mã: ");
                        String token = scanner.nextLine();
                        if (service.checkToken(tokenCorrect, token) == true) {
                            System.out.println("Mời nhập mật khẩu mới: ");
                            String passwordNew = scanner.nextLine();
                            System.out.println("Nhập lại mật khẩu ở trên: ");
                            String passwordNew2 = scanner.nextLine();
                            if (passwordNew.equals(passwordNew2)){
                                System.out.println("Đổi mật khẩu thành công !");
                                findUserEmai.setPassword(passwordNew);
                                service.saveUserToDB(findUserEmai);
                                break;
                            } else {
                                System.out.println("Mật khẩu không trùng nhau vui lòng nhập lại");
                            }
                        } else {
                            System.out.println("Mã không đúng kiểm tra và nhập lại");
                        }
                    } else {
                        System.out.println("Không tìm thấy Email vui lòng nhập lại: ");
                    }
                    break;
                case 4:
                    System.out.println("1.Tìm kiếm user theo tên\n "+
                            "2.Tìm kiếm user theo xếp hạng");
                    int choose = scanner.nextInt();scanner.nextLine();
                    switch (choose){
                        case 1:
                            System.out.println("Mời bạn nhập tên user:");
                            String nameUser =scanner.nextLine();
                            User finduserbyname =service.findUserByUserName(nameUser);
                            if (finduserbyname == null){
                                System.out.println("Tên user không tồn tại mời bạn nhập lại");
                            }
                            else {
                                System.out.println("Tên: "+finduserbyname.getUserName());
                                System.out.println("Tên đây đủ: "+finduserbyname.getFullName());
                                System.out.println("SĐT: "+finduserbyname.getPhone());
                                System.out.println("Điểm: "+finduserbyname.getScore());
                                System.out.println("Email: "+finduserbyname.getEmail());
                            }
                            break;
                        case 2:
                            List<User> users = service.findAllUserOderByScoreDesc();
                            System.out.println("Mời bạng nhập xếp hạng của user:");
                            int rank = scanner.nextInt();
                            if (rank > users.size()){
                                System.out.println("Xếp hạng của user cần tìm không tồn tại mời bạn nhập lại");
                            }
                            else {
                                User userbyrank = users.get(rank-1);
                                System.out.println("Tên: "+userbyrank.getUserName());
                                System.out.println("Tên đầy đủ: "+userbyrank.getFullName());
                                System.out.println("SĐT: "+userbyrank.getPhone());
                                System.out.println("Điểm: "+userbyrank.getScore());
                                System.out.println("Email: "+userbyrank.getEmail());
                            }
                            break;
                    }
                    break;

                case 5:
                    ketThuc = false;


            }
        }

    }

}
