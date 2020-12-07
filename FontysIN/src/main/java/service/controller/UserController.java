package service.controller;

import service.PersistenceController;
import service.model.*;
import service.repository.DatabaseException;
import service.repository.JDBCProfileRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import service.model.*;
import service.repository.*;

import java.sql.SQLException;
import java.util.List;
public class UserController {
    JDBCProfileRepository profileRepository = new JDBCProfileRepository();
    PersistenceController controller = new PersistenceController();
    public User getUserByEmail(String email) {

        try{
            User u = null;
            List<User> Users = profileRepository.getUsers();
            for (User p :Users){
                if(p.getEmail().equals(email)){
                    return p;
                }
            }

        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
    public User getUserFromAuth(String auth){
        String encodedCredentials = auth.replaceFirst("Basic ", "");
        String credentials = new
                String(Base64.getDecoder().decode(encodedCredentials.getBytes()));
        //Split username and password tokens in credentials
        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String email = tokenizer.nextToken();
        User u = getUserByEmail(email);
        return u;
    }

    public boolean login(String email, String password){
        User u = getUserByEmail(email);
        if(u.equals(null)){
            return false;
        }
        String encryptedPassword = doHashing(password);
        if(u.getPassword().equals(encryptedPassword)){
            return true;
        }
        return false;
    }

    public boolean isIdAndAuthSame(int id, String auth) {
        String encodedCredentials = auth.replaceFirst("Basic ", "");
        String credentials = new
                String(Base64.getDecoder().decode(encodedCredentials.getBytes()));

        final StringTokenizer tokenizer = new StringTokenizer(credentials, ":");
        final String email = tokenizer.nextToken();

        User user = controller.getUser(id);//studentsRepository.get(stNr);
        if (!user.getEmail().equals(email)) {
            return false;
        } else {
            return true;
        }
    }
    public boolean addUser(User user) {

        try {
            String encryptedPassword = doHashing(user.getPassword());
            user.setPassword(encryptedPassword);
            if(profileRepository.createUser(user)) {

                return true;
            }
            else
            {
                return false;
            }
        } catch (DatabaseException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //To encrypt the password
    public static String doHashing (String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }
}
