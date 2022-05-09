package com.example.mydogapp.viewmodel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mydogapp.model.DogBreed;

@Database(entities = {DogBreed.class}, version = 1,  exportSchema = false)
public abstract class AppDatabase  extends RoomDatabase {
    public abstract DogsDao dogsDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "my_dog")
                    .build();
        }
        return instance;
    }
}
