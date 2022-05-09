package com.example.mydogapp.model;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class DogBreed implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("life_span")
    private String lifeSpan;
    @SerializedName("origin")
    private String origin;
    @SerializedName("url")
    private String url;
    @SerializedName("bred_for")
    private String bredFor;
    @SerializedName("breed_group")
    private String breedGroup;
    @SerializedName("temperament")
    private String temperament;
    @Embedded
    @SerializedName("height")
    private Height height;
    @Embedded
    @SerializedName("weight")
    private Weight weight;

    private Boolean status;

    public DogBreed(int id, String name, String lifeSpan, String origin, String url, String bredFor, String breedGroup, String temperament, Height height, Weight weight, Boolean status) {
        this.id = id;
        this.name = name;
        this.lifeSpan = lifeSpan;
        this.origin = origin;
        this.url = url;
        this.bredFor = bredFor;
        this.breedGroup = breedGroup;
        this.temperament = temperament;
        this.height = height;
        this.weight = weight;
        this.status = status;
    }

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBreedGroup() {
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }
}
