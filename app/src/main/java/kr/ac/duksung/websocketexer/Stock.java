package kr.ac.duksung.websocketexer;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stock {
    private List<Stock> items = null;

    @SerializedName("market_id")
    @Expose
    private int market_id;

    @SerializedName("item_id")
    @Expose
    private int item_id;

    @SerializedName("item_name")
    @Expose
    private String item_name;

    @SerializedName("item_price")
    @Expose
    private String item_price;
    @SerializedName("item_stock")
    @Expose
    private String item_stock;

    public int getMarket_id() {
        return market_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getItem_name() { return item_name; }

    public String getItem_price() {
        return item_price;
    }

    public String getItem_stock() {
        return item_stock;
    }
}
