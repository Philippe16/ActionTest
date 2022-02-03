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

//    public static void main(String[] args) {
//        DataMapper dataMapper = new DataMapper();
//        dataMapper.createUser(new User("Frederik","Hansen","Pass123","343434", "Home 3"));
//        List<User> users = dataMapper.getAllUsers();
//        for (User user : users) {
//            System.out.println(user);
//        }
//    }
}
