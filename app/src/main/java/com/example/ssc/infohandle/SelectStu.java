package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a11201.godsavemeapplication.R;
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
			// 显示出查询结果
			stuselectTV.setText("账号ID：" + stu.getUser_id() + "\n" + "姓名："
					+ stu.getName() + "\n" + "密码：" + stu.getUser_key() + "\n"
					+ "账号类型：" + stu.getType_id() + "\n" + "性别："
					+ stu.getSex_id() + "\n" + "电话：" + stu.getTel()
					+ "\n" + "电子邮箱：" + stu.getEmail() + "\n" + "班级："
					+ stu.getClass_id()+ "\n" + "学院：" + stu.getCollege_id() + "\n"
					+ "账号状态：" + stu.getStatus_id() + "\n" + "可选课程类型：" + stu.getCourse_type_set() );
		}
	}
}