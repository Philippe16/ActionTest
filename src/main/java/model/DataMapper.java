/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thomas
 */
public class DataMapper {

    public void createUser(User u) {

        try {
            Connection con = DBconnector.connection();

            String SQL = "INSERT INTO usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, u.getFname());
            ps.setString(2, u.getLname());
            ps.setString(3, u.getPw());
            ps.setString(4, u.getAddress());
            ps.setString(5, u.getPhone());
            ps.executeUpdate();
            ResultSet ids = ps.getGeneratedKeys();
            ids.next();
            int id = ids.getInt(1);
            u.setId(id);

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> persons = new ArrayList();
        try {
            Connection con = DBconnector.connection();
            String SQL = "SELECT id, fname, lname, pw, phone, address FROM usertable";
            PreparedStatement ps = con.prepareStatement(SQL);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                String pw = rs.getString("pw");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int id = (int) rs.getInt("id");
                persons.add(new User(id, fname, lname, pw, phone, address));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return persons;
    }

    public ArrayList<String> getAllUsernames() {
        ArrayList<String> usernames = new ArrayList<>();

        try(Connection connection = DBconnector.connection()){
            String sql = "SELECT fname, lname FROM usertable";

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                do {
                    String fname = rs.getString("fname");
                    String lname = rs.getString("lname");

                    usernames.add(fname + " " + lname);
                } while (rs.next());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return usernames;
    }

    public String getSpecificUserDetails(String fname, String lname){
        String userDetails = "";

        try(Connection connection = DBconnector.connection()){
            String sql =
                    "SELECT * FROM usertable WHERE fname=? AND lname=?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, fname);
                ps.setString(2, lname);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int id = (int) rs.getInt("id");
                    String pw = rs.getString("pw");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");

                    userDetails = new User(id, fname, lname, pw, phone, address).toString();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return userDetails;
    }

    public boolean editPw(int userId, String newPw) {
        boolean success = false;

        DBconnector.setConnection(null);

        try(Connection connection = DBconnector.connection()){
            String sql = "UPDATE usertable SET pw = ? WHERE id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, newPw);
                ps.setInt(2, userId);

                ps.executeUpdate();

                success = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return success;
    }


//    public static void main(String[] args) {
//        DataMapper dataMapper = new DataMapper();
//        dataMapper.createUser(new User("Frederik","Hansen","Pass123","343434", "Home 3"));
//        List<User> users = dataMapper.getAllUsers();
//        for (User user : users) {
//            System.out.println(user);
//        }
//    }
}
