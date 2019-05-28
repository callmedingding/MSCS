package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ssc.Service.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestLoginActivity extends Activity {
    String id = null;
    String key = null;
    EditText ed_user, ed_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_login);
        ed_user = (EditText) findViewById(R.id.ed_test_user);
        ed_key = (EditText) findViewById(R.id.ed_test_psw);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }


        });
    }

    static Retrofit aliMapRetrofit = new Retrofit.Builder()
            .baseUrl("http://10.8.214.4:80/Android/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    static Gson GSON;
    static {
        GSON = new GsonBuilder().setPrettyPrinting().create();
    }
    private void login() {
        id = ed_user.getText().toString().trim();
        key = ed_key.getText().toString().trim();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Service service = aliMapRetrofit.create(Service.class);
                try {
                    ResponseBody body = service.getResult(id, key).execute().body();
                    System.out.println(GSON.toJson(body));
                    Log.i("TESTLogin:",GSON.toJson(body).toString());
                } catch (IOException e) {
                    System.out.print("TESTLogin:\n");
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
