/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author thomas
 */
public class User {
    private int id;
    private String fname;
    private String lname;
    private String pw;
    private String phone;
    private String address;

    public User(int id, String fname, String lname, String pw, String phone, String address) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.pw = pw;
        this.phone = phone;
        this.address = address;
    }

    public User(String fname, String lname, String pw, String phone, String address) {
        this.fname = fname;
        this.lname = lname;
        this.pw = pw;
        this.phone = phone;
        this.address = address;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", fname=" + fname + ", lname=" + lname + ", pw=" + pw + ", phone=" + phone + ", address=" + address + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(fname, user.fname) && Objects.equals(lname, user.lname) && Objects.equals(pw, user.pw) && Objects.equals(phone, user.phone) && Objects.equals(address, user.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fname, lname, pw, phone, address);
    }
}
