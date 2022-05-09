package com.example.mydogapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.example.mydogapp.model.DogBreed;

import java.util.List;

@Dao
public interface DogsDao {

    @Transaction
    @Query("SELECT * FROM DogBreed")
    LiveData<List<DogBreed>> getAllDogs();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(DogBreed... dogs);

    @Update
    void update(DogBreed dog);

    @Delete
    void delete(DogBreed dog);
}
