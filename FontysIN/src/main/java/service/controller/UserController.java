package service.controller;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import service.model.User;
import service.model.UserType;
import service.model.dto.UserDTO;
import service.repository.DatabaseException;
import service.repository.ProfileRepository;
import service.repository.UserRepository;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.net.URISyntaxException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class UserController {
    ProfileRepository profileRepository = new ProfileRepository();
    ProfileController controller = new ProfileController();
    public User getUserByEmail(String email) {

        try{
            User u = null;
            List<User> Users = profileRepository.getUsers();
            for (User p :Users){
                if(p.getEmail().equals(email)){
                    return p;
                }
            }

        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;

    }

    public User getUser(int userId){

//        ProfileRepository profileRepository = new ProfileRepository();


        try {
            User user = profileRepository.getUserById(userId);

            System.out.println("ok");

            return user;
        } catch (DatabaseException | SQLException | URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
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
    public User getUserFromToken(String token) {
        Claims decoded = decodeJWT(token);

        String id = decoded.getId();

        User u = getUser(parseInt(id));

        return u;
    }
    public static String SECRET_KEY = "oeRaYY";

    //Sample method to construct a JWT
    public String createJWT(String id, String issuer, String subject, long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

//        //if it has been specified, let's add the expiration
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
    public  Claims decodeJWT(String jwt) {

        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public boolean addUser(User user) {

//        ProfileRepository pRepository = new ProfileRepository();
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
        } catch (DatabaseException | SQLException | URISyntaxException e) {
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

    /******************RANIM***********************Filter Search One bu One**************************/

    /**
     * Show/print the users with the given type
     * @param type of the user to be shown.
     */
    //show users by user type
    public List<UserDTO> UserFilteredWithType(UserType type){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByType(type);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Show/print the users with the given location id
     * @param id of the user to be shown.
     */
    //show users by user location
    public List<UserDTO> UserFilteredWithLocation(int id){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByLocation(id);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given department id
     * @param id of the user to be shown.
     */
    //show usesr by user department
    public List<UserDTO> UserFilteredWithDepartment(int id){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByDepartment(id);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given start study year
     * @param year of the user to be shown.
     */
    //show users by start study year
    public List<UserDTO> UserFilteredWithStartStudyYear(int year){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByStartStudyYear(year);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given start work year
     * @param year of the user to be shown.
     */
    //show users by start work year
    public List<UserDTO> UserFilteredWithStartWorkYear(int year){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByStartWorkYear(year);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given usertype location and department
     * @param type
     * @param lId
     * @param dId
     * of the user to be shown.
     */
    //show users by loc dep and type
    public List<UserDTO> UserFilterByTypeLocationAndDepartment(UserType type, int lId, int dId){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByUserTypeAndLocationAndDepartment(type, lId, dId);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given usertype start study year location and department
     * @param type
     * @param year
     * @param lId
     * @param dId
     * of the user to be shown.
     */
    //show users by location user type location  department and start study year
    public List<UserDTO> UserFilterByTypeLocationDepartmentAndStartSudyYear(UserType type, int year, int lId, int dId){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByUserTypeAndStartStudyYearAndDepartmentAndLocation(type, year, lId, dId);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given usertype start work year location and department
     * @param type
     * @param year
     * @param lId
     * @param dId
     * of the user to be shown.
     */
    //show users by location user type  location department and start work year
    public List<UserDTO> UserFilterByTypeLocationDepartmentAndStartWorkyearFontysStaff(UserType type, int year, int lId, int dId){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByUserTypeAndStartWorkYearAndDepartmentAndLocationFontysStaff(type, year, lId, dId);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /****************************RANIM*****************************Normal searching*********************/
    /**
     * Show/print the users of FontysIn Web Application
     */
    public List<UserDTO> GetAllUsers(){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersDTO();
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /***********************************Combine filter searching with the input box*************************************/
    /**
     * Show/print the users with the given usertype start work year location and department
     * @param type
     * @param lId
     * @param dId
     * @param chars
     * of the user to be shown.
     */
    //show users by location user type department and start study year
    public List<UserDTO> UserFilteLocationDepartmentTypeAndName(String chars, int lId, int dId, UserType type ){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByUserTypeLocationDeoartmentAndName(chars, lId, dId, type);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Show/print the users with the given usertype start work year location and department
     * @param chars
     * of the user to be shown.
     */
    //show users by location user type department and start study year
    public List<UserDTO> UserFilterByFirstNameChars(String chars){

        UserRepository userRepository = new UserRepository();

        try {
            List<UserDTO> users = userRepository.getUsersByFirstNameChars(chars);
            System.out.println(users);
            return users;
        }
        catch (DatabaseException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }
}
