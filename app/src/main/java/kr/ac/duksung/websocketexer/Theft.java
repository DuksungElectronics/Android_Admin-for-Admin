package kr.ac.duksung.websocketexer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Theft {
    private List<Theft>  theft_times = null;



    @SerializedName("market_id")
    @Expose
    private int market_id;



    @SerializedName("theft_time")
    @Expose
    private String theft_time;

    public String getTheft_time() {
        return theft_time;
    }

    public int getMarket_id() {
        return market_id;
    }

}
