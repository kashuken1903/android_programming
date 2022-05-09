package com.example.mydogapp.model;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


public class Height {
//    @PrimaryKey(autoGenerate = true)
    private int heightId;
    @ColumnInfo(name = "heightImp")
    @SerializedName("imperial")
    private String imperial;
    @ColumnInfo(name = "heightMet")
    @SerializedName("metric")
    private String metric;

    public Height(String imperial, String metric) {
        this.imperial = imperial;
        this.metric = metric;
    }

    public int getHeightId() {
        return heightId;
    }

    public void setHeightId(int heightId) {
        this.heightId = heightId;
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
