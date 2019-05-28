package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ssc.db.MyDBHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CreateDBActivity extends Activity implements View.OnClickListener{
    private static final String DB_NAME = "db_mscs.sql";
    private MyDBHelper mydbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_db);
        mydbHelper=new MyDBHelper(this);
        db=mydbHelper.getWritableDatabase();//创建数据库文件，但实际还没有数据表
        Button btnCreateDB=(Button)findViewById(R.id.create_db);
        btnCreateDB.setOnClickListener(this);
    }

    private String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_db:
                try {
                    InputStream in= getAssets().open( DB_NAME);
                    String sqlUpdate = null;
                    try {
                        sqlUpdate = readTextFromSDcard(in);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String[] s = sqlUpdate.split(";");
                    for (int i = 0; i < s.length; i++) {
                        if (!TextUtils.isEmpty(s[i])) {
                            db.execSQL(s[i]);
                        }
                    }
                    in.close();
                }  catch (IOException e) {
                }
                Log.v("message:","click create db btn.");
                break;


        }

    }
}
