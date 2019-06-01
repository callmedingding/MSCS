package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.db.MyDBHelper;
import com.example.ssc.student.Stu;
import com.example.ssc.student.StuDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InsertStu extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText stuinsertET1;
	private EditText stuinsertET2;
	private EditText stuinsertET3;
	private EditText stuinsertET4;
	private EditText stuinsertET5;
	private EditText stuinsertET6;
	private EditText stuinsertET7;
	private EditText stuinsertET8;
	private EditText stuinsertET9;
	private EditText stuinsertET10;
	private EditText stuinsertET11;

	private Spinner spinner1;
	private Spinner spinner2;
	private Spinner spinner3;
	private Spinner spinner4;
	private Spinner spinner5;
	boolean isFirst1=true,isFirst2=true,isFirst3=true,isFirst4=true,isFirst5=true;

	Button choose;
	Button stuinsertBtn;
	List<Map<String,String>> typelist=new ArrayList<>();
	SimpleAdapter adapter;
	AlertDialog.Builder builder;
	View getlistview;
	String set="";

	Intent intent=new Intent();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_stu);
		initview();

	}

	public void initview(){
	/*
		 * 初始化EditText
		 */
		stuinsertET1 = (EditText) findViewById(R.id.stuinsertET1);
		stuinsertET2 = (EditText) findViewById(R.id.stuinsertET2);
		stuinsertET3 = (EditText) findViewById(R.id.stuinsertET3);
		stuinsertET4 = (EditText) findViewById(R.id.stuinsertET4);
		stuinsertET5 = (EditText) findViewById(R.id.stuinsertET5);
		stuinsertET6 = (EditText) findViewById(R.id.stuinsertET6);
		stuinsertET7 = (EditText) findViewById(R.id.stuinsertET7);
		stuinsertET8 = (EditText) findViewById(R.id.stuinsertET8);
		stuinsertET9 = (EditText) findViewById(R.id.stuinsertET9);
		stuinsertET10 = (EditText) findViewById(R.id.stuinsertET10);
		stuinsertET11 = (EditText) findViewById(R.id.stuinsertET11);
		//账号类型
		spinner1= (Spinner) findViewById(R.id.stuinsertSP1);
		spinner1=initSpinner(spinner1,"select * from tb_UserType");
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {//默认执行了
				if (isFirst1) {
					view.setVisibility(View.INVISIBLE);
				} else {
                /*adapterView是指当前的listview；
                 *view是当前listview中的item的view的布局,就是可用这个view获取里面控件id后操作控件
                 * i是当前item在listview中适配器的位置
                 * l是当前item在listview里第几行的位置
                 */
					//获得选中项中的HashMap对象
					HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
//				String Text=map.get("id");
					//赋值
//				Toast.makeText(this,Text,Toast.LENGTH_SHORT).show();
					stuinsertET4.setText(map.get("id"));
				}
				isFirst1=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//性别
		spinner2= (Spinner) findViewById(R.id.stuinsertSP2);
		spinner2=initSpinner(spinner2,"select * from tb_Sex");
		spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirst2) {
					view.setVisibility(View.INVISIBLE);
				} else {
                /*adapterView是指当前的listview；
                 *view是当前listview中的item的view的布局,就是可用这个view获取里面控件id后操作控件
                 * i是当前item在listview中适配器的位置
                 * l是当前item在listview里第几行的位置
                 */
					//获得选中项中的HashMap对象
					HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
//				String Text=map.get("id");
					//赋值
//				Toast.makeText(this,Text,Toast.LENGTH_SHORT).show();
					stuinsertET5.setText(map.get("id"));
				}
				isFirst2=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//班级
		spinner3= (Spinner) findViewById(R.id.stuinsertSP3);
		spinner3=initSpinner(spinner3,"select * from tb_class");
		spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirst3) {
					view.setVisibility(View.INVISIBLE);
				} else {
                /*adapterView是指当前的listview；
                 *view是当前listview中的item的view的布局,就是可用这个view获取里面控件id后操作控件
                 * i是当前item在listview中适配器的位置
                 * l是当前item在listview里第几行的位置
                 */
					//获得选中项中的HashMap对象
					HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
//				String Text=map.get("id");
					//赋值
//				Toast.makeText(this,Text,Toast.LENGTH_SHORT).show();
					stuinsertET8.setText(map.get("id"));
				}
				isFirst3=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//学院
		spinner4= (Spinner) findViewById(R.id.stuinsertSP4);
		spinner4=initSpinner(spinner4,"select * from tb_college");
		spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirst4) {
					view.setVisibility(View.INVISIBLE);
				} else {
                /*adapterView是指当前的listview；
                 *view是当前listview中的item的view的布局,就是可用这个view获取里面控件id后操作控件
                 * i是当前item在listview中适配器的位置
                 * l是当前item在listview里第几行的位置
                 */
					//获得选中项中的HashMap对象
					HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
//				String Text=map.get("id");
					//赋值
//				Toast.makeText(this,Text,Toast.LENGTH_SHORT).show();
					stuinsertET9.setText(map.get("id"));
				}
				isFirst4=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//状态
		spinner5= (Spinner) findViewById(R.id.stuinsertSP5);
		spinner5=initSpinner(spinner5,"select * from tb_UserStatus");
		spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (isFirst5) {
					view.setVisibility(View.INVISIBLE);
				} else {
                /*adapterView是指当前的listview；
                 *view是当前listview中的item的view的布局,就是可用这个view获取里面控件id后操作控件
                 * i是当前item在listview中适配器的位置
                 * l是当前item在listview里第几行的位置
                 */
					//获得选中项中的HashMap对象
					HashMap<String, String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
//				String Text=map.get("id");
					//赋值
//				Toast.makeText(this,Text,Toast.LENGTH_SHORT).show();
					stuinsertET10.setText(map.get("id"));
				}
				isFirst5=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//可选类型
		choose= (Button) findViewById(R.id.course_type_set);
		initMutilAlertDialog();
		choose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CreateDialog();
			}
		});

		stuinsertBtn= (Button) findViewById(R.id.studeletebtn);
//		stuinsertBtn.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				stuInsertLoad(v);
//			}
//		});

	}
	public void initMutilAlertDialog(){
		MyDBHelper myDBHelper=new MyDBHelper(this);
		SQLiteDatabase db=myDBHelper.getWritableDatabase();
		Cursor cursor=db.rawQuery("select * from tb_courseType",null);
		if(cursor.moveToFirst()){
			for (int i=0;i<cursor.getCount();i++){
				Map<String,String> map=new HashMap<>();
				map.put("course_type_name",cursor.getString(cursor.getColumnIndex("course_type_name")));
				map.put("course_type_id",cursor.getString(cursor.getColumnIndex("course_type_id")));
				typelist.add(map);
				cursor.moveToNext();
			}
		}
		boolean[] isCheck=new boolean[typelist.size()];
		//初始化
		for (int i = 0; i < isCheck.length; i++) {
			isCheck[i] = false;
		}
	}

	public void CreateDialog() {
		// 动态加载一个listview的布局文件进来
		LayoutInflater inflater = LayoutInflater.from(this);
		getlistview = inflater.inflate(R.layout.alertdialog_listview, null);

		// 给ListView绑定内容
		ListView listview = (ListView) getlistview.findViewById(R.id.X_listview);
		adapter = new InsertStu.SetSimpleAdapter(this,typelist , R.layout.checkbox_list, new String[] { "course_type_name" },
				new int[] { R.id.X_text });
		// 给listview加入适配器
		listview.setAdapter(adapter);
		listview.setItemsCanFocus(false);
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listview.setOnItemClickListener(new InsertStu.ItemOnClick());

		builder = new AlertDialog.Builder(this);
		builder.setTitle("请选择可选课程类型");
		builder.setIcon(R.drawable.ic_launcher);
		//设置加载的listview
		builder.setView(getlistview);
		builder.setPositiveButton("确定", new InsertStu.DialogOnClick());
		builder.setNegativeButton("取消", new InsertStu.DialogOnClick());
		builder.create().show();
	}

	class ItemOnClick implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
			CheckBox cBox = (CheckBox) view.findViewById(R.id.X_checkbox);
			HashMap<String,String> map=(HashMap<String,String>)arg0.getItemAtPosition(position);
			String Text=map.get("course_type_id");
			if (cBox.isChecked()) {
				cBox.setChecked(false);
				set=set.replace(Text+",","");
				Log.i("TAG", set);
			} else {
				set=set+Text+",";
				cBox.setChecked(true);
				Log.i("TAG", set);
			}

//            if (position == 0 && (cBox.isChecked())) {
//                //如果是选中 全选  就把所有的都选上 然后更新
//                for (int i = 0; i < bl.length; i++) {
//                    bl[i] = true;
//                }
//                adapter.notifyDataSetChanged();
//            } else if (position == 0 && (!cBox.isChecked())) {
//                //如果是取消全选 就把所有的都取消 然后更新
//                for (int i = 0; i < bl.length; i++) {
//                    bl[i] = false;
//                }
//                adapter.notifyDataSetChanged();
//            }
//            if (position != 0 && (!cBox.isChecked())) {
//                // 如果把其它的选项取消   把全选取消
//                bl[0] = false;
//                bl[position]=false;
//                adapter.notifyDataSetChanged();
//            } else if (position != 0 && (cBox.isChecked())) {
//                //如果选择其它的选项，看是否全部选择
//                //先把该选项选中 设置为true
//                bl[position]=true;
//                int a = 0;
//                for (int i = 1; i < bl.length; i++) {
//                    if (bl[i] == false) {
//                        //如果有一个没选中  就不是全选 直接跳出循环
//                        break;
//                    } else {
//                        //计算有多少个选中的
//                        a++;
//                        if (a == bl.length - 1) {
//                            //如果选项都选中，就把全选 选中，然后更新
//                            bl[0] = true;
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
//                }
//            }
		}

	}

	class DialogOnClick implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
				case Dialog.BUTTON_POSITIVE:
					//确定按钮的事件
					Log.i("set:",set);
					stuinsertET11.setText(set);
					break;
				case Dialog.BUTTON_NEGATIVE:
					//取消按钮的事件
					break;
				default:
					break;
			}
		}
	}

	//重写simpleadapterd的getview方法
	class SetSimpleAdapter extends SimpleAdapter {
		public SetSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from,
								int[] to) {
			super(context, data, resource, from, to);
		}
	}

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

	/*
	 * 提交按钮监听
	 */
	public void stuInsertLoad(View v) {
		/*
		 * 获取每个EditText中的值
		 */
		String str_id = stuinsertET1.getText().toString().trim();
		String user_id = "";
		if (!str_id.equals("")) {
			user_id = str_id;
		}

		String name = stuinsertET2.getText().toString().trim();
		String user_key = stuinsertET3.getText().toString().trim();
		String type_id = stuinsertET4.getText().toString().trim();
		String sex_id = stuinsertET5.getText().toString().trim();
		String tel = stuinsertET6.getText().toString().trim();
		String email = stuinsertET7.getText().toString().trim();
		String class_id = stuinsertET8.getText().toString().trim();
		String college_id = stuinsertET9.getText().toString().trim();
		String status_id = stuinsertET10.getText().toString().trim();
		String course_type_set = stuinsertET11.getText().toString().trim();

		// 封装成学生对象
		Stu stu = new Stu(user_id, name, user_key, type_id, sex_id, tel, email,class_id,college_id,status_id,course_type_set);
		
		// 调用StuDao插入方法进行插入
		StuDao dao = new StuDao(this);
		dao.insert(stu);
		intent.setClass(this, InsertStu.class);
		/*
		 * 弹出对话框提示插入成功
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("成功插入学生信息！");
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