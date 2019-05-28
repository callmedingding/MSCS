package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.student.Stu;
import com.example.ssc.student.StuDao;


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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_stu);
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
		String type_id_str = stuinsertET4.getText().toString().trim();
		String sex_id_str = stuinsertET5.getText().toString().trim();
		String tel_str = stuinsertET6.getText().toString().trim();
		String email = stuinsertET7.getText().toString().trim();
		String class_id_str = stuinsertET8.getText().toString().trim();
		String college_id_str = stuinsertET9.getText().toString().trim();
		String status_id_str = stuinsertET10.getText().toString().trim();
		String course_type_set = stuinsertET11.getText().toString().trim();

		Integer type_id=Integer.parseInt(type_id_str);
		Integer sex_id=Integer.parseInt(sex_id_str);
		Integer tel=Integer.parseInt(tel_str);
		Integer class_id=Integer.parseInt(class_id_str);
		Integer college_id=Integer.parseInt(college_id_str);
		Integer status_id=Integer.parseInt(status_id_str);

		// 封装成学生对象
		Stu stu = new Stu(user_id, name, user_key, type_id, sex_id, tel, email,class_id,college_id,status_id,course_type_set);
		
		// 调用StuDao插入方法进行插入
		StuDao dao = new StuDao(this);
		dao.insert(stu);

		/*
		 * 弹出对话框提示插入成功
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("成功插入学生信息！");
		builder.setPositiveButton("确定", null);
		builder.create();
		builder.show();
	}

}