package vn.plusplus.user.cms.service;


import vn.plusplus.user.cms.interfaces.UserInterface;
import vn.plusplus.user.cms.model.User;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
            String line = "";
            String usernames = "";
            String fullnames = "";
            String emails = "";
            String phones = "";
            int scores = 0;
            String passwords = "";
            while ((line = bufferedReader.readLine()) != null){
                //userName=VALUE###fullName=VALUE###email=VALUE###phone=VALUE###score=VALUE###password=VALUE
                String[] fileLine = line.split("###");
                //cat username tu file
                for(String st : fileLine){
                    String[] items = st.split("=");
                    if(items[0].equals("userName")){
                        usernames = items[1];
                    } else if (items[0].equals("fullName")){
                        fullnames = items[1];
                    } else if (items[0].equals("email")){
                        emails = items[1];
                    } else if (items[0].equals("phone")){
                        phones = items[1];
                    }else if (items[0].equals("score")){
                        scores = Integer.parseInt(items[1]);
                    } else if (items[0].equals("password")){
                        passwords = items[1];
                    }
                } //Đã Fix
                //chuyen cac thanh phan vao trong liss<User>
                users.add(new User(usernames, fullnames, emails, phones, scores, passwords ));
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
//        user = new User();

        File file = new File("data/user.txt");
        String filePath = file.getAbsolutePath();
        FileWriter fileWriter = new FileWriter(filePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        bufferedWriter.newLine();
        bufferedWriter.write(user.toString());
        bufferedWriter.flush();

        bufferedWriter.close();
        fileWriter.close();

        /*
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        //userName=VALUE###fullName=VALUE###email=VALUE###....
        try {
            fileOutputStream = new FileOutputStream("user.txt");
            try {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        fileInputStream = new FileInputStream("user.txt");
        objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            User user2 = (User) objectInputStream.readObject();
            System.out.println(user2.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            fileInputStream.close();
            fileOutputStream.close();
            objectInputStream.close();
            objectOutputStream.close();
        }*/
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String passWord) {
        List<User> users = readAllUserFromDB();
        for (User user : users){
            if (userName.equals(user.getUserName()) && passWord.equals(user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        List<User> users = readAllUserFromDB();
        for(User user : users)
        {
            if(email.equals(user.getEmail()))
            {
                return user;
            }
        }

        return null;
    }

    @Override
    public void sendTokenToEmail(String email) {

    }

    @Override
    public User findUserByUserName(String userName) {
        List<User> users = readAllUserFromDB();
        User findUserbyUsername = null;
        for (User user : users) {
            if (userName.equals(user.getUserName())) {
                findUserbyUsername = user;
                break;
            }
        }
        return findUserbyUsername;
        //Đã Fix
    }

    @Override
    public List<User> findAllUserOderByScoreDesc() {
        List<User> users = readAllUserFromDB();
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getScore() < o2.getScore()) {
                    return 1;
                } else {
                    if (o1.getScore() == o2.getScore()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }

            }
        });
        return users;

    }
}
