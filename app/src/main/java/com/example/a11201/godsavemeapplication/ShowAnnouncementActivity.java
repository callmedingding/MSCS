package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.ssc.Announcement.AnnouncementDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowAnnouncementActivity extends Activity {

    ListView list;
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_announcement);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        list = (ListView) findViewById(R.id.listviews);
        list = (ListView) findViewById(R.id.listviews);
        //list.setDivider(null);

        // 调用CourseDao查询方法进行查询
        AnnouncementDao dao = new AnnouncementDao(this);
        // 获取列表数据
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        listItem = dao.selectAllData();//查看所有公告

        // 生成适配器的Item和动态数组对应的元素

        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// 数据源
                R.layout.list_item,// ListItem的XML实现
                // 动态数组与ImageItem对应的子项
                new String[] { "发布者：" },
                // ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[] { R.id.text });

        // 添加并且显示
        list.setAdapter(listItemAdapter);

        // 添加点击
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int postion, long longs) {
                // 获得选中项的HashMap对象
                @SuppressWarnings("unchecked")
                Map<String, String> map = (Map<String, String>) list
                        .getItemAtPosition(postion);
                String publisher_id = map.get("publisher_id");
                String comments = map.get("comments");

                Intent intent = new Intent(ShowAnnouncementActivity.this,AnnouncementDetailActivity.class);
                //传递数据
//                final SerializableMap myMap=new SerializableMap();
//                myMap.setMap(map);
//                Bundle bundle=new Bundle();
//                bundle.putSerializable("map", myMap);
//                intent.putExtras(bundle);

				/* 通过Bundle对象存储需要传递的数据 */
                Bundle bundle = new Bundle();
				/*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
                bundle.putString("publisher_id", publisher_id);
                bundle.putString("comments", comments);
                bundle.putString("from", "查看公告");
				/*把bundle对象assign给Intent*/
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        backBtn = (Button) findViewById(R.id.fanhui);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

    }

    /**
     * @help 返回上一个界面
     */
    @Override
    public void onBackPressed() {
        finish();
    }
}
