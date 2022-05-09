package com.example.mycontact;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact")
    LiveData<List<Contact>> getAllContacts();
    @Insert
    void insertAll(Contact... contacts);
    @Update
    void update(Contact contact);
    @Delete
    void deleteAll(List<Contact> contacts);
}
