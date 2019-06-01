package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.ssc.course.CourseDao;
import com.example.ssc.db.MyDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectCourseActivity extends Activity {
	ListView list;
	Button backBtn;
	Spinner courseTypeSpinner;
	boolean isFirst=true;
	String course_type="",my_id="";
	Bundle bundle_from=new Bundle(),bundle_to = new Bundle();
	SimpleAdapter listItemAdapter;
	ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_course);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		bundle_from=getIntent().getExtras();
		my_id=bundle_from.getString("user_id");
		list = (ListView) findViewById(R.id.listviews);

		// 调用CourseDao查询方法进行查询
		CourseDao dao = new CourseDao(this);
		// 获取列表数据
		listItem = dao.getAllData();//初始为全部

		// 生成适配器的Item和动态数组对应的元素

		listItemAdapter = new SimpleAdapter(this, listItem,// 数据源
				R.layout.list_item,// ListItem的XML实现
				// 动态数组与ImageItem对应的子项
				new String[] { "course_id" ,"course_name"},
				// ImageItem的XML文件里面的一个ImageView,两个TextView ID
				new int[] { R.id.publisher,R.id.text });//不要在意细节，注意位置

		// 添加并且显示
		list.setAdapter(listItemAdapter);

		// 添加点击课程信息
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

				Intent intent = new Intent(SelectCourseActivity.this,
						CourseDetailActivity.class);
				/* 通过Bundle对象存储需要传递的数据 */

				/*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/
				bundle_to.putString("course_id", course_id);
				bundle_to.putString("course_name", course_name);
				bundle_to.putString("my_id",my_id);
				bundle_to.putString("from", "立即选课");
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

		courseTypeSpinner=(Spinner)findViewById(R.id.courseTypeSP);
		courseTypeSpinner=initSpinner(courseTypeSpinner,"select * from tb_courseType");
		courseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//默认执行了
				if (isFirst) {
					view.setVisibility(View.INVISIBLE);
				} else {
                /*adapterView是指当前的listview；
                 *view是当前listview中的item的view的布局,就是可用这个view获取里面控件id后操作控件
                 * i是当前item在listview中适配器的位置
                 * l是当前item在listview里第几行的位置
                 */
					//获得选中项中的HashMap对象
					HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
					course_type=map.get("id");
					System.out.println(course_type);
					//刷新页面

				}
				isFirst=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
	}


	//初始化下拉框


	public Spinner initSpinner(Spinner spinner, String sql){

		MyDBHelper myDBHelper=new MyDBHelper(this);
		SQLiteDatabase db=myDBHelper.getWritableDatabase();
		Cursor cursor=db.rawQuery(sql,null);
		List<Map<String,String>> list=new ArrayList<>();
		if(cursor.moveToFirst()){
			for (int i=0;i<cursor.getCount();i++){
				Map<String,String> map=new HashMap<>();
				map.put("name",cursor.getString(1));//对应值
				map.put("id",cursor.getString(0));//主键
				list.add(map);
				cursor.moveToNext();
			}
		}
		SimpleAdapter simpleAdapter=new SimpleAdapter(
				this,
				list,
				R.layout.spinner_list,
				new String[]{"name"},
				new int[]{R.id.name});
		spinner.setAdapter(simpleAdapter);

		return spinner;
	}

	/**
	 * @help 返回上一个界面
	 */
	@Override
	public void onBackPressed() {
		finish();
	}
}
