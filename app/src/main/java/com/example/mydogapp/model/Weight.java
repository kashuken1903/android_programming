package com.example.mydogapp.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

public class Weight {
//    @PrimaryKey(autoGenerate = true)
    private int weightId;
    @ColumnInfo(name = "weightImp")
    @SerializedName("imperial")
    private String imperial;
    @ColumnInfo(name = "weightMet")
    @SerializedName("metric")
    private String metric;

    public Weight(String imperial, String metric) {
        this.imperial = imperial;
        this.metric = metric;
    }

    public int getWeightId() {
        return weightId;
    }

    public void setWeightId(int weightId) {
        this.weightId = weightId;
    }

    public String getImperial() {
        return imperial;
    }

    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}
