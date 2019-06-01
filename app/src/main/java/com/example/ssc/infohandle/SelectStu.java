package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.db.MyDBHelper;
import com.example.ssc.student.Stu;
import com.example.ssc.student.StuDao;


public class SelectStu extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText stuselectET;
	/*
	 * 定义TextView
	 */
	private TextView stuselectTV;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_stu);
		/*
		 * 初始化EditText
		 */
		stuselectET = (EditText) findViewById(R.id.stuselectET);
		// 初始化TextView
		stuselectTV = (TextView) findViewById(R.id.stuselectTV3);
	}

	/*
	 * 查询按钮监听
	 */
	public void stuSelectLoad(View v) {
		/*
		 * 获取EditText中的值
		 */
		String str_id = stuselectET.getText().toString().trim();
		String user_id = "";
		if (!str_id.equals("")) {
			user_id = str_id;
		}
		// 调用StuDao查询方法进行查询
		StuDao dao = new StuDao(this);
		Stu stu = dao.select(user_id);

		if (stu == null) {
			/*
			 * 弹出对话框提示查询失败
			 */
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage("您查询的学生信息不存在！");
			builder.setPositiveButton("确定", null);
			builder.create();
			builder.show();
		} else {
			MyDBHelper myhelper = new MyDBHelper(this.getBaseContext());
			SQLiteDatabase db = myhelper.getReadableDatabase();
//			Cursor cursor = db.rawQuery("select * from tb_person where user_id=?", new String[]{user_id + ""});
//			if (cursor.moveToFirst()) {
			String type_name="",sex="",class_name="",college_name="",status="";
				//账号类型
					Cursor cursor_1 = db.rawQuery("select * from tb_UserType where type_id=?",
							new String[]{stu.getType_id()+""});
					if (cursor_1.moveToFirst()) {
						type_name=cursor_1.getString(cursor_1.getColumnIndex("type")) + "";
					}

				//性别
					Cursor cursor_2 = db.rawQuery("select * from tb_Sex where sex_id=?",
							new String[]{stu.getSex_id()});
					if (cursor_2.moveToFirst()){
						sex=cursor_2.getString(cursor_2.getColumnIndex("sex")) + "";
					}


//
//				System.out.println(cursor.getString(cursor.getColumnIndex("class_id")));
				if(stu.getClass_id()!=null)
				{//班级，可为空
					Cursor cursor_6 = db.rawQuery("select * from tb_class where class_id=?",
							new String[]{stu.getClass_id()}	);
					if(cursor_6.moveToFirst()){
						class_name=cursor_6.getString(cursor_6.getColumnIndex("class_name")) + "";
					}
				}

				if(stu.getCollege_id()!=null)
				{//学院，可为空
					Cursor cursor_3 = db.rawQuery("select * from tb_college where college_id=?",
							new String[]{stu.getCollege_id()});
					if(cursor_3.moveToFirst()){
						college_name=cursor_3.getString(cursor_3.getColumnIndex("college_name")) + "";
					}
				}

				//账号状态
					Cursor cursor_4 = db.rawQuery("select * from tb_UserStatus where status_id=?",
							new String[]{stu.getStatus_id()});
					if(cursor_4.moveToFirst()){
						status=cursor_4.getString(cursor_4.getColumnIndex("status")) + "";
					}

				//可为空
					String str1=stu.getCourse_type_set();
					System.out.println(str1);
					String courseType_str = "";
					//可选课程类型，可为空
					if(str1!=null) {
						String[] course_type = str1.split(",");
						for (int i = 0; i < course_type.length; i++) {
							Cursor cursor_5 = db.rawQuery("select * from tb_courseType where course_type_id=?",
									new String[]{course_type[i] + ""});
							cursor_5.moveToFirst();
							String str = cursor_5.getString(cursor_5.getColumnIndex("course_type_name"));
							courseType_str = courseType_str + str + ",";
						}
						System.out.println(courseType_str);

					}


			// 显示出查询结果
			stuselectTV.setText("账号ID：" + stu.getUser_id() + "\n" + "姓名："
					+ stu.getName() + "\n" + "密码：" + stu.getUser_key() + "\n"
					+ "账号类型：" + type_name + "\n" + "性别："
					+ sex + "\n" + "电话：" + stu.getTel()
					+ "\n" + "电子邮箱：" + stu.getEmail() + "\n" + "班级："
					+ class_name+ "\n" + "学院：" + college_name + "\n"
					+ "账号状态：" + status + "\n" + "可选课程类型：" +courseType_str  );
		}
	}
}