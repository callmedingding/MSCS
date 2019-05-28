package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.teacher.Teacher;
import com.example.ssc.teacher.TeacherDao;


public class InsertTea extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText teainsertET1;
	private EditText teainsertET2;
	private EditText teainsertET3;
	private EditText teainsertET4;
	private EditText teainsertET5;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_tea);
		/*
		 * 初始化EditText
		 */
		teainsertET1 = (EditText) findViewById(R.id.teainsertET1);
		teainsertET2 = (EditText) findViewById(R.id.teainsertET2);
		teainsertET3 = (EditText) findViewById(R.id.teainsertET3);
		teainsertET4 = (EditText) findViewById(R.id.teainsertET4);
		teainsertET5 = (EditText) findViewById(R.id.teainsertET5);
	}

	/*
	 * 提交按钮监听
	 */
	public void teaInsertLoad(View v) {
		/*
		 * 获取每个EditText中的值
		 */
		String str_id = teainsertET1.getText().toString().trim();
		Integer id = 0;
		if (!str_id.equals("")) {
			id = Integer.parseInt(str_id);
		}
		String name = teainsertET2.getText().toString().trim();
		String sex = teainsertET3.getText().toString().trim();
		String phonenumber = teainsertET4.getText().toString().trim();
		String xueyuan = teainsertET5.getText().toString().trim();
		// 封装成老师对象
		Teacher tea = new Teacher(id, name, sex, xueyuan, phonenumber);
		
		// 调用TeacherDao插入方法进行插入
		TeacherDao dao = new TeacherDao(this);
		dao.insert(tea);

		/*
		 * 弹出对话框提示插入成功
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("成功插入老师信息！");
		builder.setPositiveButton("确定", null);
		builder.create();
		builder.show();
	}

}