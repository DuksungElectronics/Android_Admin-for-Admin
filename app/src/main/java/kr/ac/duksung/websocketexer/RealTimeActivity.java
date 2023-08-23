package kr.ac.duksung.websocketexer;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class RealTimeActivity extends AppCompatActivity {

    private Button accessBtn;
    private EditText ipAddrText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time);

        ipAddrText = (EditText) findViewById(R.id.editIPaddr);
        accessBtn = (Button) findViewById(R.id.accessBtn);

        accessBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ipAddr = ipAddrText.getText().toString();
                if (ipAddr.length() == 0) {
                    Toast.makeText(getApplicationContext(), "ip 주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Pattern pattern = Patterns.IP_ADDRESS;
                if(!pattern.matcher(ipAddr).matches()) {
                    Toast.makeText(getApplicationContext(), "ip 주소 형식에 맞게 입력해주세요.(xx.xx.xx.xx)", Toast.LENGTH_SHORT).show();
                    return;
                }
                ipAddr = "http://"+ipAddr+":5000/stream";
                Intent intent = new Intent(getApplicationContext(), CameraActivity.class);
                intent.putExtra("streamUrl", ipAddr);
                startActivity(intent);
            };
        });
    };
}