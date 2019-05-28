package com.example.a11201.godsavemeapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.ssc.course.Course;

public class AnnouncementDetailActivity extends AppCompatActivity {

    private TextView publisher_id, Announcement_detail;
    private Button handleBtn;
    String from; //记录从哪个页面跳转过来
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_detail);

		/*
		 * 标题显示
		 */
        // Bundle bundle = getIntent().getExtras();
        // SerializableMap serializableMap = (SerializableMap)
        // bundle.get("map");
        // System.out.println("哈哈哈"+serializableMap.getMap());

		/*获取Intent中的Bundle对象*/
        Bundle bundle = this.getIntent().getExtras();
        /*获取Bundle中的数据，注意类型和key*/
        String str_id = bundle.getString("publish_id");
        String publish_id = "";
        if (!str_id.equals("")) {
            publish_id = str_id;
        }
        String comments = bundle.getString("comments");
        from = bundle.getString("from");
        System.out.println("AnnouncementDetailActivity:\tfrom\t" + from);

        publisher_id = (TextView) findViewById(R.id.title);
        Announcement_detail = (TextView) findViewById(R.id.announcement_detail);///!!!!
        publisher_id.setText("(发布者:)" + comments);

    }
}
