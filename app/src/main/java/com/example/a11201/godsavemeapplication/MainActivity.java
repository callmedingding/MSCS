package com.example.a11201.godsavemeapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ssc.infohandle.UpdateStu;

@SuppressLint("ShowToast")
public class MainActivity extends Activity implements OnClickListener {
    private TextView title;
    private Button show_personal_info, show_announcement, my_course, select_course,info_handle_btn;
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		/*
		 * 用户显示
		 */
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        title = (TextView) findViewById(R.id.home_title);
        title.setText("欢迎"+name+"进入系统^_^");

        show_personal_info = (Button) findViewById(R.id.show_personal_info);
        show_announcement = (Button) findViewById(R.id.show_announcement);
        my_course = (Button) findViewById(R.id.my_course);
        select_course = (Button) findViewById(R.id.select_course);
        info_handle_btn = (Button) findViewById(R.id.info_handle_btn);

        show_personal_info.setOnClickListener(this);
        show_announcement.setOnClickListener(this);
        my_course.setOnClickListener(this);
        select_course.setOnClickListener(this);
        info_handle_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            Intent intent=new Intent() ;
            Bundle bundle = getIntent().getExtras();
            String user_id = bundle.getString("user_id");
            switch (v.getId()) {
                //查看課程，此部分调到我的课程中，此部分改为个人信息
                case R.id.show_personal_info:
                    intent.setClass(this, UpdateStu.class);//修改个人信息
                    bundle.putString("from", "首页");
                    startActivity(intent);
                    break;
                //查看老師，改为我的公告！
                case R.id.show_announcement:
                    intent.setClass(this, ShowAnnouncementActivity.class);
                    bundle.putString("from", "首页");
                    break;
                //我的課程，学生和老师
                case R.id.my_course:
                    if(user_id.substring(0,2)!="mng"){
                        intent.setClass(this, MyCourseActivity.class);
                        bundle.putString("from", "首页");
                    }else{
                        Toast.makeText(this, "你又没有课要上，干嘛点!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                //立即選課，仅限学生使用
                case R.id.select_course:
                    if(user_id.substring(0,2)=="stu"){
                        intent.setClass(this, SelectCourseActivity.class);
                        bundle.putString("from", "首页");
                    }else{
                        Toast.makeText(this, "非学生不能选课!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                //信息维护，仅限管理员使用
                case R.id.info_handle_btn:
                    if(user_id.substring(0,2)=="mng"){
                        intent.setClass(this, InfoHandleActivity.class);
                    }else{
                        Toast.makeText(this, "非管理员不能进入!", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            startActivity(intent);
        } catch (Exception e) {
            Log.v("EXCEPTION:",e.getMessage().toString());
            Toast.makeText(this, "玩下捉迷藏吧O(∩_∩)O~", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 10000) {// 如果两次按键时间间隔大于800毫秒，则不退出
                Toast.makeText(MainActivity.this, "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                firstTime = secondTime;// 更新firstTime
                return true;
            } else {
                System.exit(0);// 否则退出程序
            }
        }
        return super.onKeyUp(keyCode, event);
    }


}