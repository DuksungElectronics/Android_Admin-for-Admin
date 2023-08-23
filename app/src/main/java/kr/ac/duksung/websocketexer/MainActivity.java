package kr.ac.duksung.websocketexer;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {


    Intent intent2;
    ImageView imageView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        //textView = (TextView) findViewById(R.id.text);
        intent2 = new Intent(this.getApplicationContext(), MyService.class);

        // startService(intent2);
        startForegroundService(intent2);

        imageView = (ImageView) findViewById(R.id.imageView);




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);

            }
        });

    }

}