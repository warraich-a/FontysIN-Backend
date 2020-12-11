package service.controller;

import service.PersistenceController;
import service.model.*;
import service.repository.DatabaseException;
import service.repository.JDBCProfileRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import io.jsonwebtoken.*;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
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

        String email = decoded.getIssuer();
        User u = getUserByEmail(email);

        return u;
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
    private static String SECRET_KEY = "oeRaYY";

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
}
