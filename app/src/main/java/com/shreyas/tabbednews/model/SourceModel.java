package com.shreyas.tabbednews.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "newsSource")
public class SourceModel {

    @PrimaryKey(autoGenerate = false)
    private int autoId = 999;

    @SerializedName("status")
    private String status;

    @SerializedName("sources")
    private List<SourceItemModel> sources;

    public SourceModel(String status, List<SourceItemModel> sources) {
        this.status = status;
        this.sources = sources;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SourceItemModel> getSources() {
        return sources;
    }

    public void setSources(List<SourceItemModel> sources) {
        this.sources = sources;
    }

    @Override
    public String toString() {
        return "SourceModel{" +
                "autoId=" + autoId +
                ", status='" + status + '\'' +
                ", sources=" + sources +
                '}';
    }
}
