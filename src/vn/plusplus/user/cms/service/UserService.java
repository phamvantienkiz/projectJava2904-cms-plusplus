package vn.plusplus.user.cms.service;


import vn.plusplus.user.cms.interfaces.UserInterface;
import vn.plusplus.user.cms.model.User;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserInterface {


    @Override
    public List<User> readAllUserFromDB() {
        List<User> users = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader("/data/user.txt");
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
                String[] fileLine = line.split("[###]");
                //cat username tu file
                String[] fileuname = fileLine[0].split("[=]");
                usernames = fileuname[1];
                //cat full name tu file
                String[] filefullname = fileLine[1].split("[=]");
                fullnames = filefullname[1];
                //cat email tu file
                String[] filemail = fileLine[2].split("[=]");
                emails = filemail[1];
                //cat phone tu file
                String[] filephone = fileLine[3].split("[=]");
                phones = filephone[1];
                //cat score tu file
                String[] filescores = fileLine[4].split("[=]");
                scorestr = filescores[1];
                scores = Integer.parseInt(scorestr);
                //cat password tu file
                String[] filepass = fileLine[5].split("[=]");
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
    public void saveUserToDB(User user) {

    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String passWord) {
        readAllUserFromDB();
        for (int i =0;i<readAllUserFromDB().size();i++){
            if (userName.equals(readAllUserFromDB().get(i).getUserName()) && passWord.equals(readAllUserFromDB().get(i).getPassword())){
                return readAllUserFromDB().get(i);
            }
            else{
                return null;
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
        /*User findUserbyUsername = null;
        for (int i = 0; i < user.size(); i++) {
            if (users.get(i).getUsername().equals(userName)) {
                findUserbyUsername = users.get(i);
            }else {
                findUserbyUsername = null;
            }
        }
        return findUserbyUsername;*/
        return null;
    }

    @Override
    public List<User> findAllUserOderByScoreDesc() {
        return null;
    }
}
