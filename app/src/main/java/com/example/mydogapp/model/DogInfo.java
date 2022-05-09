package com.example.mydogapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

public class DogInfo {
    @Embedded DogBreed dog;
    @Relation(
            parentColumn = "id",
            entityColumn = "heightId"
    )
    public Height height;
    @Relation(
            parentColumn = "id",
            entityColumn = "weightId"
    )
    public Weight weight;
}
