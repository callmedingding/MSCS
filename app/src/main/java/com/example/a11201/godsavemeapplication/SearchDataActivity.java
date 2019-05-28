package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
//很重要，参透
import com.example.ssc.db.MyDBHelper;

public class SearchDataActivity extends Activity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_data);
        tv=(TextView)findViewById(R.id.testTextView);

        MyDBHelper myDBHelper=new MyDBHelper(this);
        SQLiteDatabase db=myDBHelper.getWritableDatabase();
        Cursor cursor=db.query("tb_Person",new String[]{"user_id","name"},"user_id='stu12301'",null,null,null,null);//能出正确结果
        String user_id="";
        String name="";
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            user_id=cursor.getString(0)+"\n";
            name=cursor.getString(1)+"\n";
        }
        cursor.close();
        db.close();
        tv.setText(user_id+name);
    }
}
