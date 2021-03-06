package com.example.a32150.phpmysql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.*;

public class MainActivity extends AppCompatActivity {

    OkHttpClient client;
    EditText userid, temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userid = findViewById(R.id.userid);
        temperature = findViewById(R.id.temperature);
    }
    
    public void BtnClick(View v) {
       /// Log.d("Test", "GET HTTP");
        client = new OkHttpClient();
        String pararm= "userid="+userid.getText().toString()+"&temperature="+temperature.getText().toString();
        final Request request = new Request.Builder().url("http://192.168.58.3:8080/code/android_api/get_api.php?"+pararm).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Result", "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //Log.d("Result", "onResponse");
                String json = response.body().string();
                Log.d("Test", json);//抓取伺服器端網頁回應字串
            }
        });

    }
}
