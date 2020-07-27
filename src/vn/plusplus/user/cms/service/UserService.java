package vn.plusplus.user.cms.service;


import vn.plusplus.user.cms.interfaces.UserInterface;
import vn.plusplus.user.cms.model.User;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserInterface {


    @Override
    public List<User> readAllUserFromDB() {
        List<User> users = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        File file = new File("data/user.txt");
        String filePath = file.getAbsolutePath();
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            int i = 0;
            String line = "";
            String usernames = "";
            String fullnames = "";
            String emails = "";
            String phones = "";
            String scorestr = "";
            int scores;
            String passwords = "";
            while ((line = bufferedReader.readLine()) != null){
                //userName=VALUE###fullName=VALUE###email=VALUE###phone=VALUE###score=VALUE###password=VALUE
                String[] fileLine = line.split("###");
                //cat username tu file
                String[] fileuname = fileLine[0].split("=");
                usernames = fileuname[1];
                //cat fullname tu file
                String[] filefullname = fileLine[1].split("=");
                fullnames = filefullname[1];
                //cat email tu file
                String[] filemail = fileLine[2].split("=");
                emails = filemail[1];
                //cat phone tu file
                String[] filephone = fileLine[3].split("=");
                phones = filephone[1];
                //cat score tu file
                String[] filescores = fileLine[4].split("=");
                scorestr = filescores[1];
                scores = Integer.parseInt(scorestr);
                //cat password tu file
                String[] filepass = fileLine[5].split("=");
                passwords = filepass[1];
                //chuyen cac thanh phan vao trong liss<User>
                users.add(new User(usernames, fullnames, emails, phones, scores, passwords ));
                i++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return users;
        //return null;
    }

    @Override
    public void saveUserToDB(User user) throws IOException {

            FileReader fileReader = null;
            BufferedReader bufferedReader = null;

            try {
                fileReader = new FileReader("user.txt");
                bufferedReader = new BufferedReader(fileReader,10);
                String line = "";
                while ((bufferedReader.readLine()) !=null)
                {
                    line = bufferedReader.readLine();
                    System.out.println(line);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            finally {
                fileReader.close();
                bufferedReader.close();
            }
        }


    @Override
    public User findUserByUserNameAndPassword(String userName, String passWord) {
        List<User> users = readAllUserFromDB();
        for (int i =0;i<users.size();i++){
            if (userName.equals(users.get(i).getUserName()) && passWord.equals(users.get(i).getPassword())){
                return users.get(i);
            }
        }

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
        User findUserbyUsername = null;
        for (int i = 0; i < readAllUserFromDB().size(); i++) {
            if (userName.equals(readAllUserFromDB().get(i).getUserName())) {
                findUserbyUsername = readAllUserFromDB().get(i);
            }else {
                findUserbyUsername = null;
            }
        }
        return findUserbyUsername;
        //return null;
    }

    @Override
    public List<User> findAllUserOderByScoreDesc() {
        return null;
    }
}
