package com.example.nhocs.demonavigation.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Statistic_Rating {

    public int getStatistic() {
        return statistic;
    }
    @SerializedName("statistic")
    @Expose
    private int statistic;
}
