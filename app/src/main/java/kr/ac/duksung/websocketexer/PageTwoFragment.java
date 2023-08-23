package kr.ac.duksung.websocketexer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageTwoFragment extends Fragment {
    Call<List<Theft>> call;


    ListView listView;
   // private BroadcastReceiver receiver;
    public static ArrayList<String> serviceList = new ArrayList<>();
    RetrofitClient retrofitClient = new RetrofitClient("http://125.128.219.177:8080/");
    ArrayList<String> theftTimes;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d("확인","onCreate 작동");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("확인","onCreateView 작동");
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_page_two, container, false);

        listView = (ListView) rootView.findViewById(R.id.listView);

        request();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(),TheftActivity.class);
                intent.putExtra("theft_time",theftTimes.get(i) );
                startActivity(intent);


                Toast.makeText(getContext(), theftTimes.get(i), Toast.LENGTH_SHORT).show();
            }
        });


         BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("확인","전달성공");


                // 데이터를 추출하여 처리
                String data = intent.getStringExtra("message");
                // 데이터를 사용하여 UI 업데이트 등의 작업 수행
                serviceList.add(data);

            }
        };

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstaceState){
        super.onViewCreated(view,savedInstaceState);
        Log.d("확인","onViewCreated 작동");

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("확인","onResume 작동");
        request();

        // BroadcastReceiver를 등록합니다.
        IntentFilter filter = new IntentFilter("my-event");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(receiver, filter);

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("확인","onPause 작동");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, serviceList);
        listView.setAdapter(adapter);
        // BroadcastReceiver를 해제합니다.
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(receiver);

    }

    //service에서 websocket이 on Message() 하였을때 작동하는 리스너
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        //WebScoket에서 Message가 전달되면, ?!
        @Override
        public void onReceive(Context context, Intent intent) {
            request();
            if (intent.getAction().equals("my-event")) {
                // Service로부터 전달된 데이터를 수신합니다.
                ArrayList<String> serviceList = intent.getStringArrayListExtra("message");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, serviceList);
                listView.setAdapter(adapter);

                Log.d("확인","전달성공");
                //showMessage(message);
            }

        }

    };

    @Override
    public void onStop() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, serviceList);
        listView.setAdapter(adapter);
        super.onStop();
    }

    public void request(){

        call=retrofitClient.getApiService().getTheft();
        theftTimes = new ArrayList<String>();

        call.enqueue(new Callback<List<Theft>>() {
            @Override
            public void onResponse(Call<List<Theft>> call, Response<List<Theft>> response) {
                List<Theft> result = response.body();
                for(int i=0; i<result.size();i++){
                    int size = result.size();

                    theftTimes.add(result.get(i).getTheft_time());

                }

                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, theftTimes);
                listView.setAdapter(adapter2);
            }


            @Override
            public void onFailure(Call<List<Theft>> call, Throwable t) {
                Log.d("확인", "retrofit실패");

            }

        });

    }
}
