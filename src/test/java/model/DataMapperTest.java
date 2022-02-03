package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//test
class DataMapperTest {
    DataMapper dataMapper = new DataMapper();
    @BeforeEach
    void setUp() {
        System.out.println("TESTINNNNGGGG");
        Connection con = null;
        try {
            con = DBconnector.connection();
            String createTable = "CREATE TABLE IF NOT EXISTS `startcode_test`.`usertable` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `fname` VARCHAR(45) NULL,\n" +
                    "  `lname` VARCHAR(45) NULL,\n" +
                    "  `pw` VARCHAR(45) NULL,\n" +
                    "  `phone` VARCHAR(45) NULL,\n" +
                    "  `address` VARCHAR(45) NULL,\n" +
                    "  PRIMARY KEY (`id`));";
            con.prepareStatement(createTable).executeUpdate();
        String SQL = "INSERT INTO startcode_test.usertable (fname, lname, pw, phone, address) VALUES (?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, "Hans");
        ps.setString(2, "Hansen");
        ps.setString(3, "Hemmelig123");
        ps.setString(4, "40404040");
        ps.setString(5,"Rolighedsvej 3");
        ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createUser() {
    }

    @Test
    void getAllUsers() {
        List<User> users = dataMapper.getAllUsers();
        User actual = users.get(0);
        User expected = new User("Hans", "Hansen", "Hemmelig123", "40404040", "Rolighedsvej 3");
        assertEquals(expected, actual);
    }
}