package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.course.CourseDao;


public class SelectCourse extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText courseselectET;
	/*
	 * 定义TextView
	 */
	private TextView courseselectTV;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_course_two);
		/*
		 * 初始化EditText
		 */
		courseselectET = (EditText) findViewById(R.id.courseselectET);
		// 初始化TextView
		courseselectTV = (TextView) findViewById(R.id.courseselectTV3);
	}

	/*
	 * 查询按钮监听
	 */
	public void courseSelectLoad(View v) {
		/*
		 * 获取EditText中的值
		 */
		String str_id = courseselectET.getText().toString().trim();
		String course_id = "";
		if (!str_id.equals("")) {
			course_id = str_id;
		}
		// 调用CourseDao查询方法进行查询
		CourseDao dao = new CourseDao(this);
		String str = dao.select(course_id);
		
		System.out.println("查到了："+ str);

		if (str == null) {
			/*
			 * 弹出对话框提示查询失败
			 */
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage("您查询的课程信息不存在！");
			builder.setPositiveButton("确定", null);
			builder.create();
			builder.show();
		} else {
			// 显示出查询结果,不显示最后一列
			courseselectTV.setText(str);
		}
	}
}