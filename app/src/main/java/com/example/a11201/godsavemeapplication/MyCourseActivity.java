package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.ssc.course.MyCourseDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
//有问题
public class MyCourseActivity extends Activity {
	ListView list;
	Button backBtn;
	Bundle bundle_from=new Bundle(),bundle_to=new Bundle();
	ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	String my_id="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_course);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		list = (ListView) findViewById(R.id.listviews);
		//list.setDivider(null);

		// 调用MyCourseDao查询方法进行查询
		MyCourseDao dao = new MyCourseDao(this);
		// 获取列表数据
		//需要个人id
		bundle_from = getIntent().getExtras();
		my_id = bundle_from.getString("user_id");
		listItem = dao.getAllData(my_id);

		// 生成适配器的Item和动态数组对应的元素

		SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,// 数据源
				R.layout.list_item,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "course_name" },
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.text });

		// 添加并且显示
		list.setAdapter(listItemAdapter);

		// 添加点击
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int postion, long longs) {
				// 获得选中项的HashMap对象
				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>) list
						.getItemAtPosition(postion);
				String course_id = map.get("course_id");
				String course_name = map.get("course_name");
				
				Intent intent = new Intent(MyCourseActivity.this,
						CourseDetailActivity.class);
				/* 通过Bundle对象存储需要传递的数据 */  

				/*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/  
				bundle_to.putString("course_id", course_id);//课程id
				bundle_to.putString("course_name", course_name);//似乎没用
				bundle_to.putString("my_id",my_id);
				bundle_to.putString("from", "我的课程");
				/*把bundle对象assign给Intent*/  
				intent.putExtras(bundle_to);
				startActivity(intent);
			}
		});
		
		backBtn = (Button) findViewById(R.id.fanhui);
		backBtn.setOnClickListener(new OnClickListener() {
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
