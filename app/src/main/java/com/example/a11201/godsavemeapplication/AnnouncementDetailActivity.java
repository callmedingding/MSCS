package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ssc.Announcement.Announcement;
import com.example.ssc.Announcement.AnnouncementDao;
import com.example.ssc.student.Stu;
import com.example.ssc.student.StuDao;

public class AnnouncementDetailActivity extends Activity {

    private TextView publisher_id, Announcement_detail;
    private Button handleBtn;
    String from; //记录从哪个页面跳转过来
//    Course course;
    Bundle bundle_from=new Bundle(),bundle_to=new Bundle();
    Announcement ann;
    Intent intent=new Intent();
    String my_id="",publish_id = "",comments="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_detail);

        handleBtn= (Button) findViewById(R.id.handle);
        handleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除公告
                deleteAnn();
            }
        });

		/*
		 * 标题显示
		 */
        // Bundle bundle_from = getIntent().getExtras();
        // SerializableMap serializableMap = (SerializableMap)
        // bundle_from.get("map");
        // System.out.println("哈哈哈"+serializableMap.getMap());

		/*获取Intent中的Bundle对象*/
        bundle_from = this.getIntent().getExtras();
        /*获取Bundle中的数据，注意类型和key*/
        String str_id = bundle_from.getString("publisher_id");
        System.out.println(str_id);

        if (!str_id.equals("")) {
            publish_id = str_id;
        }
//        String publish_id = bundle_from.getString("publish_id");
        comments = bundle_from.getString("comments");
        from = bundle_from.getString("from");
        System.out.println("AnnouncementDetailActivity:\tfrom\t" + from);
        StuDao dao=new StuDao(this);
        Stu stu=dao.select(publish_id);
        ann=new Announcement(publish_id,comments);
        publisher_id = (TextView) findViewById(R.id.title);
        Announcement_detail = (TextView) findViewById(R.id.announcement_detail);///!!!!
        publisher_id.setText("" + stu.getName());//查名字
        Announcement_detail.setText("" +comments);
    }

    void deleteAnn(){
        AnnouncementDao dao=new AnnouncementDao(this);
        //判断是否能删除
        //1、为管理员
        //2、是自己发布的
        //记得判空
        my_id=bundle_from.getString("my_id");
        if(my_id.substring(0,3).equals("mng")||my_id.equals(publish_id)){
            dao.delete(ann);

        }
        //跳转到showAnnouncement
        bundle_to.putString("from", "公告详情");
        intent.setClass(this,ShowAnnouncementActivity.class);
        intent.putExtras(bundle_to);
        /*
		 * 弹出对话框提示插入成功
		 */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("成功删除改公告！");
        builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface arg0, int arg1){
                arg0.dismiss();
                startActivity(intent);
            }
        });
        builder.create();
        builder.show();
    }

}
