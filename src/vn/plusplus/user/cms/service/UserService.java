package vn.plusplus.user.cms.service;


import vn.plusplus.user.cms.interfaces.UserInterface;
import vn.plusplus.user.cms.model.User;


import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class UserService implements UserInterface {

    private SendEmailService emailService;

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();

    private static final String DATA_FOR_RANDOM_STRING = CHAR_UPPER;
    private static SecureRandom random = new SecureRandom();


    private SendEmailService getEmailService(){
        if(emailService == null){
            emailService = new SendEmailService();
        }
        return emailService;
    }

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
            while ((line = bufferedReader.readLine()) != null) {
                //userName=VALUE###fullName=VALUE###email=VALUE###phone=VALUE###score=VALUE###password=VALUE
                String[] fileLine = line.split("###");
                //cat username tu file
                for (String st : fileLine) {
                    String[] items = st.split("=");
                    if (items[0].equals("userName")) {
                        usernames = items[1];
                    } else if (items[0].equals("fullName")) {
                        fullnames = items[1];
                    } else if (items[0].equals("email")) {
                        emails = items[1];
                    } else if (items[0].equals("phone")) {
                        phones = items[1];
                    } else if (items[0].equals("score")) {
                        scores = Integer.parseInt(items[1]);
                    } else if (items[0].equals("password")) {
                        passwords = items[1];
                    }
                } //Đã Fix
                //chuyen cac thanh phan vao trong liss<User>
                users.add(new User(usernames, fullnames, emails, phones, scores, passwords));
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
        List<User> users = readAllUserFromDB();
        for(User user1 : users){
            if(user1.getUserName().equals(user.getUserName())){
                users.remove(user1);
            }
        }

        File file = new File("data/user.txt");
        String filePath = file.getAbsolutePath();
        FileWriter fileWriter = new FileWriter(filePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for(User us : users) {
            bufferedWriter.write(us.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }

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
        for (User user : users) {
            if (userName.equals(user.getUserName()) && passWord.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {

        List<User> users = readAllUserFromDB();
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }

        return null;
    }

    @Override
    public String sendTokenToEmail(String email) {
        String token = generateRandomString(6);
        String content = "Token to reset password for you: " + token;
        emailService = getEmailService();
        try {
            emailService.sendEmail("Reset password email", email, content);
        } catch (Exception e){
            System.out.println("Send email reset pass failed");
        }
        return token;

    }

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);

        }

        return sb.toString();

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

    public boolean checkToken(String tokenCorrect, String tokenInput) {

        boolean checkToken = false;
        if(tokenCorrect.equals(tokenInput)){
            checkToken = true;
        }
        return checkToken;
    }
}

