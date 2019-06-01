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

public class UpdateStu extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText stuupdateET1;
	private EditText stuupdateET2;
	private EditText stuupdateET3;
	private EditText stuupdateET4;
	private EditText stuupdateET5;
	private EditText stuupdateET6;
	private EditText stuupdateET7;
	private EditText stuupdateET8;
	private EditText stuupdateET9;
	private EditText stuupdateET10;
	private EditText stuupdateET11;

	private Spinner spinner1;
	private Spinner spinner2;
	private Spinner spinner3;
	private Spinner spinner4;
	private Spinner spinner5;
	boolean isFirst1=true,isFirst2=true,isFirst3=true,isFirst4=true,isFirst5=true;
	Button choose;
	List<Map<String,String>> typelist=new ArrayList<>();
	SimpleAdapter adapter;
	AlertDialog.Builder builder;
	View getlistview;
	String set="";

	private Button btn_update;
	private Stu stu=null;
	private StuDao dao=null;
	String name ="",user_key = "",email="",course_type_set="",
			type_id="",sex_id="",class_id="", college_id="",status_id="",tel="";
	String str_id_ed="";
	//从**来
    String from="";
	//个人信息修改，故获得的是自己的id号
    String user_id ="",user_id_1="";
	Intent intent=new Intent();
	Bundle bundle_from =new Bundle();
	Bundle bundle_to=new Bundle();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_stu);
		initview();
		btn_update=(Button)findViewById(R.id.stuupdatebtn);
		btn_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 stuUpdateLoad(v);//修改
            }
        });

		bundle_from=getIntent().getExtras();
		//从首页或维护页面来
		from=bundle_from.getString("from");
		//个人信息修改，故获得的是自己的id号
		user_id = bundle_from.getString("user_id");
		user_id_1=bundle_from.getString("user_id");
        //任何人可调用studao.update
        if(from.equals("首页")){//若为自己则自动填充个人信息
			System.out.println("from首页");
            getStu(user_id);//从首页来，修改个人信息，传送个人id获得个人全部信息显示在ed上

        }else if(from.equals("信息维护")){
			System.out.println("from信息维护");
			//管理员从维护信息来，可修改任何人信息，id就设为空，留管理员自己统一输入
            user_id="";

        }
	}

	public void initview(){
		/*
		 * 初始化EditText
		 */
		stuupdateET1 = (EditText) findViewById(R.id.stuupdateET1);
		stuupdateET2 = (EditText) findViewById(R.id.stuupdateET2);
		stuupdateET3 = (EditText) findViewById(R.id.stuupdateET3);
		stuupdateET4 = (EditText) findViewById(R.id.stuupdateET4);
		stuupdateET5 = (EditText) findViewById(R.id.stuupdateET5);
		stuupdateET6 = (EditText) findViewById(R.id.stuupdateET6);
		stuupdateET7 = (EditText) findViewById(R.id.stuupdateET7);
		stuupdateET8 = (EditText) findViewById(R.id.stuupdateET8);
		stuupdateET9 = (EditText) findViewById(R.id.stuupdateET9);
		stuupdateET10 = (EditText) findViewById(R.id.stuupdateET10);
		stuupdateET11 = (EditText) findViewById(R.id.stuupdateET11);


		//账号类型
		spinner1= (Spinner) findViewById(R.id.stuupdateSP1);
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
					stuupdateET4.setText(map.get("id"));
				}
				isFirst1=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//性别
		spinner2= (Spinner) findViewById(R.id.stuupdateSP2);
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
					stuupdateET5.setText(map.get("id"));
				}
				isFirst2=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//班级
		spinner3= (Spinner) findViewById(R.id.stuupdateSP3);
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
					stuupdateET8.setText(map.get("id"));
				}
				isFirst3=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//学院
		spinner4= (Spinner) findViewById(R.id.stuupdateSP4);
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
					stuupdateET9.setText(map.get("id"));
				}
				isFirst4=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//状态
		spinner5= (Spinner) findViewById(R.id.stuupdateSP5);
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
					stuupdateET10.setText(map.get("id"));
				}
				isFirst5=false;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) { }
		});
		//可选类型
		choose= (Button) findViewById(R.id.stuupdate_course_type_set);
		initMutilAlertDialog();
		choose.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CreateDialog();
			}
		});


	}

	//初始化弹框列表
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

	//点击按钮后生成列表
	public void CreateDialog() {
		// 动态加载一个listview的布局文件进来
		LayoutInflater inflater = LayoutInflater.from(this);
		getlistview = inflater.inflate(R.layout.alertdialog_listview, null);

		// 给ListView绑定内容
		ListView listview = (ListView) getlistview.findViewById(R.id.X_listview);
		adapter = new UpdateStu.SetSimpleAdapter(this,typelist , R.layout.checkbox_list, new String[] { "course_type_name" },
				new int[] { R.id.X_text });
		// 给listview加入适配器
		listview.setAdapter(adapter);
		listview.setItemsCanFocus(false);
		listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		listview.setOnItemClickListener(new UpdateStu.ItemOnClick());

		builder = new AlertDialog.Builder(this);
		builder.setTitle("请选择可选课程类型");
		builder.setIcon(R.drawable.ic_launcher);
		//设置加载的listview
		builder.setView(getlistview);
		builder.setPositiveButton("确定", new UpdateStu.DialogOnClick());
		builder.setNegativeButton("取消", new UpdateStu.DialogOnClick());
		builder.create().show();
	}

	class ItemOnClick implements AdapterView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
			CheckBox cBox = (CheckBox) view.findViewById(R.id.X_checkbox);
			HashMap<String, String> map = (HashMap<String, String>) arg0.getItemAtPosition(position);
			String Text = map.get("course_type_id");
			if (cBox.isChecked()) {
				cBox.setChecked(false);
				set = set.replace(Text + ",", "");
				Log.i("TAG", set);
			} else {
				set = set + Text + ",";
				cBox.setChecked(true);
				Log.i("TAG", set);
			}
		}
	}

	class DialogOnClick implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
				case Dialog.BUTTON_POSITIVE:
					//确定按钮的事件
					Log.i("set:",set);
					stuupdateET11.setText(set);
					break;
				case Dialog.BUTTON_NEGATIVE:
					//取消按钮的事件
					break;
				default:
					break;
			}
		}
	}

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

    //获取原来的信息
    public void getStu(String user_id) {
		if (user_id.equals("")) {

		} else {
			//从修改个人而来，自动输入
			MyDBHelper myhelper = new MyDBHelper(this.getBaseContext());
			SQLiteDatabase db = myhelper.getReadableDatabase();
			Cursor cursor = db.rawQuery("select * from tb_person where user_id=?", new String[]{user_id + ""});
			if (cursor.moveToFirst()) {
				stuupdateET1.setText(user_id + "");//管理员修改自己的
//				System.out.println(user_id);
				stuupdateET2.setText(cursor.getString(cursor.getColumnIndex("name")) + "");
				//密码
				stuupdateET3.setText(cursor.getString(cursor.getColumnIndex("user_key")) + "");

				{//账号类型
					Cursor cursor_1 = db.rawQuery("select * from tb_UserType where type_id=?",
							new String[]{
									cursor.getString(cursor.getColumnIndex("type_id"))
							}
					);
					if (cursor_1.moveToFirst()) {
						stuupdateET4.setText(cursor_1.getString(cursor_1.getColumnIndex("type")) + "");
					}
				}

				{//性别
					Cursor cursor_2 = db.rawQuery("select * from tb_Sex where sex_id=?",
							new String[]{
									cursor.getString(cursor.getColumnIndex("sex_id"))
							}
					);
					if (cursor_2.moveToFirst()){
						stuupdateET5.setText(cursor_2.getString(cursor_2.getColumnIndex("sex")) + "");
					}
				}
				//电话
				stuupdateET6.setText(cursor.getString((cursor.getColumnIndex("tel"))) + "");
				//电子邮箱
				stuupdateET7.setText(cursor.getString(cursor.getColumnIndex("email")) + "");

				System.out.println(cursor.getString(cursor.getColumnIndex("class_id")));
				if(cursor.getString(cursor.getColumnIndex("class_id"))!=null)
				{//班级，可为空
					Cursor cursor_6 = db.rawQuery("select * from tb_class where class_id=?",
							new String[]{
									cursor.getString(cursor.getColumnIndex("class_id"))
							}
					);
					if(cursor_6.moveToFirst()){
						stuupdateET8.setText(cursor_6.getString(cursor_6.getColumnIndex("class_name")) + "");
					}
				}
				if(cursor.getString(cursor.getColumnIndex("college_id"))!=null)
				{//学院，可为空
					Cursor cursor_3 = db.rawQuery("select * from tb_college where college_id=?",
							new String[]{
									cursor.getString(cursor.getColumnIndex("college_id"))
							}
					);
					if(cursor_3.moveToFirst()){
					stuupdateET9.setText(cursor_3.getString(cursor_3.getColumnIndex("college_name")) + "");
					}
				}

				{//账号状态
					Cursor cursor_4 = db.rawQuery("select * from tb_UserStatus where status_id=?",
							new String[]{
									cursor.getString(cursor.getColumnIndex("status_id"))
							}
					);
					if(cursor_4.moveToFirst()){
						stuupdateET10.setText(cursor_4.getString(cursor_4.getColumnIndex("status")) + "");
					}
				}

				//可为空
				if(cursor.moveToFirst()){
					String str1=cursor.getString(cursor.getColumnIndex("course_type_set"));
					System.out.println(str1);
					//可选课程类型，可为空
					if(str1!=null) {
						String[] course_type = str1.split(",");
						String courseType_str = "";
						for (int i = 0; i < course_type.length; i++) {
							Cursor cursor_5 = db.rawQuery("select * from tb_courseType where course_type_id=?",
									new String[]{course_type[i] + ""});
							cursor_5.moveToFirst();
							String str = cursor_5.getString(cursor_5.getColumnIndex("course_type_name"));
							courseType_str = courseType_str + str + ",";
						}
						System.out.println(courseType_str);
						stuupdateET11.setText(courseType_str);
					}
				}
			}
		}
	}

	/*
	 * 更新按钮监听
	 */
	public void stuUpdateLoad(View v) {
		/*
		 * 获取每个EditText中的值
		 */
		str_id_ed = stuupdateET1.getText().toString().trim();//要修改的人的id，有可能是自己的
		name = stuupdateET2.getText().toString().trim();
		user_key = stuupdateET3.getText().toString().trim();
		type_id=stuupdateET4.getText().toString().trim();
		sex_id=stuupdateET5.getText().toString().trim();//
		tel=stuupdateET6.getText().toString().trim();
		email = stuupdateET7.getText().toString().trim();
		class_id=stuupdateET8.getText().toString().trim();
		college_id=stuupdateET9.getText().toString().trim();
		status_id=stuupdateET10.getText().toString().trim();
		course_type_set = stuupdateET11.getText().toString().trim();


		//管理员
		System.out.println(user_id_1.substring(0,3));
		if(!user_id_1.substring(0,3).equals("mng")){
			//非管理员只能改部分
			//很多是炮灰，在具体update语句中没有进行更新,user_id不可被更改
			stu=new Stu(user_id_1, name, user_key, type_id, sex_id, tel,email,class_id,college_id,status_id,course_type_set);
			dao = new StuDao(this);
			dao.updatePart(stu);
			errorMsg(this,"提示","由于不是管理员，故只对账户密码，电话和邮箱进行了修改与更新，若有疑问请找管理员。");
			bundle_to = getIntent().getExtras();
			bundle_to.putString("user_id", user_id);
			bundle_to.putString("from", "首页");
			intent.putExtras(bundle_to);
			intent.setClass(this,UpdateStu.class);
			startActivity(intent);
		}else{
            //管理员修改私人信息和修改所有用户信息
			// 封装成用户对象
			stu = new Stu(str_id_ed, name, user_key, type_id, sex_id, tel,email,class_id,college_id,status_id,course_type_set);
			// 调用StuDao更新方法进行更新
			dao = new StuDao(this);
			dao.update(stu);
			errorMsg(this,"提示","修改成功");
			bundle_to = getIntent().getExtras();
			bundle_to.putString("user_id", user_id);
			bundle_to.putString("from", "信息维护");
			intent.putExtras(bundle_to);
			intent.setClass(this,UpdateStu.class);
		}

	}
	public void errorMsg(Context context, String title, String message){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
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