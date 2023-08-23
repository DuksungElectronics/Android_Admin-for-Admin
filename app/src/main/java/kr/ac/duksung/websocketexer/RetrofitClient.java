package kr.ac.duksung.websocketexer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String BASE_URL;

    RetrofitClient(String BASE_URL){
       this.BASE_URL = BASE_URL;

    }

    //private static String BASE_URL="http://125.128.219.177:8080/";

    public static RetrofitInterface getApiService(){
        return getInstance().create(RetrofitInterface.class);
    }

    private static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

}
