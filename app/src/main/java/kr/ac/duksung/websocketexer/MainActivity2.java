package kr.ac.duksung.websocketexer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;


import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {
    Intent intent;
    ArrayAdapter<String> adapter;
    private TabLayout tabLayout;
    //framgent가 배치되는 곳 viewPager
    private ViewPager viewPager;
    //PageOneFragment pageOneFragment;
    //service와 주고받기 위함.
    private MyBroadcastReceiver mBroadcastReceiver;
    // for aloarm Channel에 대한 id 생성 : Channel을 구부하기 위한 ID 이다.
    private static final String CHANNEL_ID = "duksungelec";
    // Channel을 생성 및 전달해 줄 수 있는 Manager 생성
    private NotificationManager mNotificationManager;
    private static final int NOTIFICATION_ID = 0;

    private ImageView realTimeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("확인", "Main onCreate");



        mBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("com.example.action.UPDATE_TEXT_VIEW");
        registerReceiver(mBroadcastReceiver, intentFilter);

        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main2);
        ViewPager viewPager = findViewById(R.id.viewPager);

        TabLayout tab = findViewById(R.id.tabLayout);
        tab.setupWithViewPager(viewPager);




        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.commitNow();


        TestPagerAdapter adapter = new TestPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        //둘은 붙어있어야한다. 실시간 CCTV 로 이동
        realTimeBtn = (ImageView) findViewById(R.id.realTimeBtn);
        realTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RealTimeActivity.class);
                startActivity(intent);
            }
        });




    }

    private class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            ArrayList<String> message = intent.getStringArrayListExtra("message");


            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent notificationPendingIntent = PendingIntent.getActivity(getApplicationContext(), NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

            NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                    .setContentTitle("도난의심")
                    .setContentText("삐용삐용")
                    .setSmallIcon(R.drawable.ic_android_black_24dp);
            //notification을 탭 했을경우 notification을 없앤다

            //mNotificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        }
    }





}
