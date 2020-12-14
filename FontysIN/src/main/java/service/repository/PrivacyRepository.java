package service.repository;

import service.model.*;
import service.model.dto.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBCPrivacyRepository  extends JDBCRepository{

    public boolean createPrivacy(Privacy p) throws DatabaseException{
        Connection connection = this.getDatabaseConnection();
        boolean exist = false;
        String sql = "INSERT INTO privacy(`userId`,`educationSetting`, `experienceSetting`, `skillSetting`,`hideFromSearch`) VALUES (?,?,?,?,?)";
        try {
            if(!exist){
                PreparedStatement statement = connection.prepareStatement(sql);

                statement.setInt(1, p.getUserId());
                statement.setString(2,p.getEducationSetting().toString());
                statement.setString(3,p.getExperienceSetting().toString());
                statement.setString(4,p.getSkillSetting().toString());
                Boolean hideFromSearch = p.getHideFromSearch();
                int search;
                if(hideFromSearch){
                    search = 1;
                }else{
                    search = 0;
                }
                statement.setInt(5, search);
                statement.executeUpdate();
                connection.commit();
                connection.close();
                return true;
            } else  {
                connection.close();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Privacy> getPrivacyList() throws DatabaseException {
        List<Privacy> privacyList = new ArrayList<>();

        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM privacy";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String educationSetting = resultSet.getString("educationSetting");
                String experienceSetting = resultSet.getString("experienceSetting");
                String skillSetting = resultSet.getString("skillSetting");
                int hideFromSearch = resultSet.getInt("hideFromSearch");
                Privacy.Setting edu = Privacy.Setting.EVERYONE;
                Boolean search = false;
                if (educationSetting.equals("EVERYONE"))
                {
                    edu = Privacy.Setting.EVERYONE;
                }
                else if (educationSetting.equals("CONNECTIONS"))
                {
                    edu = Privacy.Setting.CONNECTIONS;
                }
                else  if (educationSetting.equals("ONLYME"))
                {
                    edu = Privacy.Setting.ONLYME;
                }
                Privacy.Setting exp = Privacy.Setting.EVERYONE;
                if (experienceSetting.equals("EVERYONE"))
                {
                    exp = Privacy.Setting.EVERYONE;
                }
                else if (experienceSetting.equals("CONNECTIONS"))
                {
                    exp = Privacy.Setting.CONNECTIONS;
                }
                else  if (experienceSetting.equals("ONLYME"))
                {
                    exp = Privacy.Setting.ONLYME;
                }
                Privacy.Setting ski = Privacy.Setting.EVERYONE;
                if (skillSetting.equals( "EVERYONE"))
                {
                    ski = Privacy.Setting.EVERYONE;
                }
                else if (skillSetting.equals("CONNECTIONS"))
                {
                    ski = Privacy.Setting.CONNECTIONS;
                }
                else  if (skillSetting.equals("ONLYME"))
                {
                    ski = Privacy.Setting.ONLYME;
                }
                if(hideFromSearch == 1){// So 1 is true, yes hide me from search
                    search = true; // Yes hide me
                }else{
                    search = false; // no dont hide me
                }

                Privacy a = new Privacy(id, userId, edu, exp, ski,search);
                privacyList.add(a);
            }
            connection.setAutoCommit(false);
            connection.close();

        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read comments from the database.",throwable);
        }
        return privacyList;
    }

    public Privacy getPrivacyByUser(User user) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "SELECT * FROM privacy WHERE userId = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()){
                connection.close();
                throw new DatabaseException("Privacy with id " + user.getId() + " cannot be found");
            } else {
                int id = resultSet.getInt("id");
                int userId = resultSet.getInt("userId");
                String educationSetting = resultSet.getString("educationSetting");
                String experienceSetting = resultSet.getString("experienceSetting");
                String skillSetting = resultSet.getString("skillSetting");
                int hideFromSearch = resultSet.getInt("hideFromSearch");
                Privacy.Setting edu = Privacy.Setting.EVERYONE;
                Boolean search = false;
                if (educationSetting.equals("EVERYONE"))
                {
                    edu = Privacy.Setting.EVERYONE;
                }
                else if (educationSetting.equals("CONNECTIONS"))
                {
                    edu = Privacy.Setting.CONNECTIONS;
                }
                else  if (educationSetting.equals("ONLYME"))
                {
                    edu = Privacy.Setting.ONLYME;
                }
                Privacy.Setting exp = Privacy.Setting.EVERYONE;
                if (experienceSetting.equals("EVERYONE"))
                {
                    exp = Privacy.Setting.EVERYONE;
                }
                else if (experienceSetting.equals("CONNECTIONS"))
                {
                    exp = Privacy.Setting.CONNECTIONS;
                }
                else  if (experienceSetting.equals("ONLYME"))
                {
                    exp = Privacy.Setting.ONLYME;
                }
                Privacy.Setting ski = Privacy.Setting.EVERYONE;
                if (skillSetting.equals( "EVERYONE"))
                {
                    ski = Privacy.Setting.EVERYONE;
                }
                else if (skillSetting.equals("CONNECTIONS"))
                {
                    ski = Privacy.Setting.CONNECTIONS;
                }
                else  if (skillSetting.equals("ONLYME"))
                {
                    ski = Privacy.Setting.ONLYME;
                }
                if(hideFromSearch == 1){ // So 1 is true, yes hide me from search
                    search = true; // Yes hide me
                }else{
                    search = false; // No do not hide me
                }

                Privacy a = new Privacy(id, userId, edu, exp, ski,search);
                connection.close();
                return a;
            }
        } catch (SQLException throwable) {
            throw new DatabaseException("Cannot read products from the database.",throwable);
        }
    }
    public boolean updatePrivacy(Privacy a) throws DatabaseException {
        Connection connection = this.getDatabaseConnection();
        String sql = "UPDATE `privacy` SET `educationSetting`=?,`experienceSetting`=?,`skillSetting`=?,`hideFromSearch`=? WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            Boolean hideFromSearch = a.getHideFromSearch();
            int search;
            if(hideFromSearch){
                search = 1;
            }else{
                search = 0;
            }
            statement.setInt(5, a.getId());
            statement.setString(1, String.valueOf(a.getEducationSetting()));
            statement.setString(2, String.valueOf(a.getExperienceSetting()));
            statement.setString(3, String.valueOf(a.getSkillSetting()));
            statement.setInt(4, search);
            statement.executeUpdate();
            connection.commit();
            connection.close();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
