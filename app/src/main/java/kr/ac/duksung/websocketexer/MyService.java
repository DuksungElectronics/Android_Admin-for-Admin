package kr.ac.duksung.websocketexer;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


public class MyService extends Service {
    //private OkHttpClient mWebSocketClient;
    private Handler mHandler;
    private Runnable mRunnable;


    private TextView mTextView;
    // Channel에 대한 id 생성 : Channel을 구부하기 위한 ID 이다.
    private static final String CHANNEL_ID = "duksungelec";
    // Channel을 생성 및 전달해 줄 수 있는 Manager 생성
    private NotificationManager mNotificationManager;

    private static final int NOTIFICATION_ID = 0;

    static ArrayList<String> stringArrayList = new ArrayList<>();

    private static final int NOTIFICATION_ID2 = 1;

    private static final String CHANNEL_ID2 = "ForegroundServiceChannel";

    public MyService() {
    }

    //Data를 Fragment로 보내는 method
    private void sendDataToFragment(String data) {
        Intent intent = new Intent("my-event");
        intent.putExtra("message", data);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        Log.d("확인","전달 시작");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        Notification notification = buildNotification();
        //서비스를 Foreground 상태로 설정하고 알림 표시
        startForeground(NOTIFICATION_ID2, notification);
    }

/*
    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }*/


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            //예외 발생 예상 코드
            sendDataToFragment("hi");
        } catch (Exception e) {
            //예외 발생시 처리할 내용4
            Log.d("확인:","전달 실패");
        }

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // 기기(device)의 SDK 버전 확인 ( SDK 26 버전 이상인지 - VERSION_CODES.O = 26)
        if(android.os.Build.VERSION.SDK_INT
                >= android.os.Build.VERSION_CODES.O){
            //Channel 정의 생성자( construct 이용 )
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,"Test Notification",mNotificationManager.IMPORTANCE_HIGH);
            //Channel에 대한 기본 설정
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Mascot");
            // Manager을 이용하여 Channel 생성
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        Log.d("확인","onStartCommand()");

        // 백그라운드 스레드에서 WebSocket 연결
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("ws://125.128.219.177:8080/text")
                .build();



        WebSocket webSocket = client.newWebSocket(request, new WebSocketListener() {

            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                Log.d("확인", "Received message: 연결성공");

            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {

                buildNotification();



                //String data = "Hello from Service";

                Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                PendingIntent notificationPendingIntent = PendingIntent.getActivity(getApplicationContext(), NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                // WebSocket으로부터 텍스트 메시지를 받았을 때 호출됩니다.
                super.onMessage(webSocket, text);

                NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setContentTitle("도난의심")
                        .setContentText("삐용삐용")
                        .setSmallIcon(R.drawable.admin)
                        .setContentIntent(notificationPendingIntent) //추가된 부분
                        .setAutoCancel(true);
                //notification을 탭 했을경우 notification을 없앤다.//추가된 부분

                //notification을 탭 했을경우 notification을 없앤다.//notification을 탭 했을경우 notification을 없앤다.
                //notification을 탭 했을경우 notification을 없앤다

                mNotificationManager.notify(NOTIFICATION_ID, notifyBuilder.build());

                Intent intent = new Intent("my-event");
                //intent.putExtra("message", "전달할 데이터");
                // LocalBroadcastManager를 사용하여 Broadcast 전송
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                stringArrayList.add(text);


                try{
                    intent.putStringArrayListExtra("message",stringArrayList);
                    Log.d("확인","전달 성공");
                }
                catch(Exception e){
                    Log.d("확인","전달 실패");
                }




            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {

                Log.d("확인", "Received message: 연결끊김");

            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                System.out.println("연결실패");
                Log.e(TAG, "확인" + t.getMessage(), t);

            }
        });

       // return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("확인", "onDestroy()");
        stopForeground(true);
        super.onDestroy();
        //mHandler.removeCallbacks(mRunnable);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private Notification buildNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText("Service is running...")
                .setSmallIcon(R.drawable.admin);

        return builder.build();
    }



}