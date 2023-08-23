package kr.ac.duksung.websocketexer;



import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PageThreeFragment extends Fragment {
    ListView listView;
    ArrayList userList;
    ArrayAdapter adapter;
    DatabaseReference mDatabase;


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);





        }



        @Override
        public void onDestroy() {
            super.onDestroy();
            // BroadcastReceiver를 해제합니다.

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_page_three, container, false);

        listView = rootView.findViewById(R.id.listView);

        userList = new ArrayList<>();





        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                userList.clear(); // 기존 리스트 초기화

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    User newUser = childSnapshot.getValue(User.class);
                    Log.d("Firebase","data받기 시작");
                    if (newUser != null) {
                        String userNumber = newUser.getUserNumber();
                        Log.d("Firebase",userNumber);
                        String timestamp = newUser.getTimestamp();
                        String userInfo = "사용자: " + "0" + userNumber + "\n출입시간: " + timestamp;
                        userList.add(userInfo);

                    }
                    else{
                        Log.d("확인","null");
                    }
                }

                adapter.notifyDataSetChanged(); // 리스트뷰 갱신
            }

            // db에서 데이터를 읽어올때 예외 발생하면 처리
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Firebase", "Failed to retrieve users: " + databaseError.getMessage());
            }
        };

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, userList);

        listView.setAdapter(adapter);

        mDatabase.addValueEventListener(valueEventListener);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstaceState){
        super.onViewCreated(view,savedInstaceState);


    }




}
