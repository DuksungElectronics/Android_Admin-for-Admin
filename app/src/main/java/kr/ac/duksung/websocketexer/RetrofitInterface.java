package kr.ac.duksung.websocketexer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {


    @GET("stock")
    Call<List<Stock>> getStock();

    //(괄호안에 들어가는 것은 BASE_URL+)
    @GET("theft_time")
    Call<List<Theft>> getTheft();

}
