package kr.ac.duksung.websocketexer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PageOneFragment extends Fragment {
    RetrofitClient retrofitClient = new RetrofitClient("http://125.128.219.177:8080/");

    Call<List<Stock>> call;
    private RecyclerView recyclerView;
    private ArrayList<RecyclerViewItem> mList;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    ArrayList<String> itemnames ;
    ArrayList<String> itemprices;
    ArrayList<String> itemstocks;
    FloatingActionButton refreshbtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_page_one, container, false);
        refreshbtn = (FloatingActionButton) rootView.findViewById(R.id.refreshbtn);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstaceState){
        super.onViewCreated(view,savedInstaceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mList = new ArrayList<>();
        RecyclerViewItem item = new RecyclerViewItem();
        request();
        refreshbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
                //request();
            }
        });


    }

    public void request(){
        itemnames = new ArrayList<String>();
        itemprices = new ArrayList<String>();
        itemstocks = new ArrayList<String>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        call=retrofitClient.getApiService().getStock();

        call.enqueue(new Callback<List<Stock>>() {
            @Override
            public void onResponse(Call<List<Stock>> call, Response<List<Stock>> response) {
                List<Stock> result = response.body();
                for(int i=0; i<result.size();i++){
                    itemnames.add(result.get(i).getItem_name());
                    itemprices.add(result.get(i).getItem_price());
                    itemstocks.add(result.get(i).getItem_stock());
                    addItem(itemnames.get(i),itemprices.get(i),itemstocks.get(i));
                }
                mRecyclerViewAdapter = new RecyclerViewAdapter(mList);
                recyclerView.setAdapter(mRecyclerViewAdapter);
            }
            @Override
            public void onFailure(Call<List<Stock>> call, Throwable t) {
            }
        });

    }

    public void addItem(String item_name, String item_price, String item_stock){
        RecyclerViewItem item = new RecyclerViewItem();
        item.setItemName(item_name);
        item.setItemPrice(item_price);
        item.setItemStock(item_stock);
        mList.add(item);
    }

    public void clear(){
        mList.clear();
        request();

    }


}

