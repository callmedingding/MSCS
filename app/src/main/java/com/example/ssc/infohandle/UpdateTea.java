package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.teacher.Teacher;
import com.example.ssc.teacher.TeacherDao;


public class UpdateTea extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText teaupdateET1;
	private EditText teaupdateET2;
	private EditText teaupdateET3;
	private EditText teaupdateET4;
	private EditText teaupdateET5;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_tea);
		/*
		 * 初始化EditText
		 */
		teaupdateET1 = (EditText) findViewById(R.id.teaupdateET1);
		teaupdateET2 = (EditText) findViewById(R.id.teaupdateET2);
		teaupdateET3 = (EditText) findViewById(R.id.teaupdateET3);
		teaupdateET4 = (EditText) findViewById(R.id.teaupdateET4);
		teaupdateET5 = (EditText) findViewById(R.id.teaupdateET5);
	}

	/*
	 * 更新按钮监听
	 */
	public void teaUpdateLoad(View v) {
		/*
		 * 获取每个EditText中的值
		 */
		String str_id = teaupdateET1.getText().toString().trim();
		Integer id = 0;
		if (!str_id.equals("")) {
			id = Integer.parseInt(str_id);
		}
		String name = teaupdateET2.getText().toString().trim();
		String sex = teaupdateET3.getText().toString().trim();
		String phonenumber = teaupdateET4.getText().toString().trim();
		String xueyuan = teaupdateET5.getText().toString().trim();
		// 封装成老师对象
		Teacher tea = new Teacher(id, name, sex, xueyuan, phonenumber);

		// 调用TeacherDao更新方法进行更新
		TeacherDao dao = new TeacherDao(this);
		dao.update(tea);

		/*
		 * 弹出对话框提示更新成功
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("成功更新ID号为：" + id + "的老师信息！");
		builder.setPositiveButton("确定", null);
		builder.create();
		builder.show();
	}
}