package com.example.mycontact;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String firstName;
    @ColumnInfo
    private String lastName;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String phone;
    @ColumnInfo
    private String avatar;

    public Contact(String firstName, String lastName, String email, String phone, String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
        this.phone = phone;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
