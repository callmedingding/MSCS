package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.course.Course;
import com.example.ssc.course.CourseDao;


public class UpdateCourse extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText courseupdateET1;
	private EditText courseupdateET2;
	private EditText courseupdateET3;
	private EditText courseupdateET4;
	private EditText courseupdateET5;
	private EditText courseupdateET6;
	private EditText courseupdateET7;
	private EditText courseupdateET8;
	private EditText courseupdateET9;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_course);
		/*
		 * 初始化EditText
		 */
		courseupdateET1 = (EditText) findViewById(R.id.courseupdateET1);
		courseupdateET2 = (EditText) findViewById(R.id.courseupdateET2);
		courseupdateET3 = (EditText) findViewById(R.id.courseupdateET3);
		courseupdateET4 = (EditText) findViewById(R.id.courseupdateET4);
		courseupdateET5 = (EditText) findViewById(R.id.courseupdateET5);
		courseupdateET6 = (EditText) findViewById(R.id.courseupdateET6);
		courseupdateET7 = (EditText) findViewById(R.id.courseupdateET7);
		courseupdateET8 = (EditText) findViewById(R.id.courseupdateET8);
		courseupdateET9 = (EditText) findViewById(R.id.courseupdateET9);
	}

	/*
	 * 更新按钮监听
	 */
	public void courseUpdateLoad(View v) {
		/*
		 * 获取每个EditText中的值
		 */
		String str_id = courseupdateET1.getText().toString().trim();
		String course_id = "";
		if (!str_id.equals("")) {
			course_id = str_id;
		}
		String course_name = courseupdateET2.getText().toString().trim();
		Integer course_type_id = Integer.parseInt(courseupdateET3.getText().toString().trim());
		Integer course_status_id = Integer.parseInt(courseupdateET4.getText().toString().trim());
		Integer class_hour = Integer.parseInt(courseupdateET5.getText().toString().trim());
		Integer credit = Integer.parseInt(courseupdateET6.getText().toString().trim());
		Integer maxnum = Integer.parseInt(courseupdateET7.getText().toString().trim());
		String course_time_id = courseupdateET8.getText().toString().trim();
		String classroom_id = courseupdateET9.getText().toString().trim();
		String time_and_classroom=course_time_id+"-"+classroom_id;
		// 封装成课程对象
		Course course = new Course(course_id, course_name, course_type_id, course_status_id,
				class_hour, credit,maxnum,course_time_id,classroom_id,time_and_classroom);

		// 调用CourseDao更新方法进行更新
		CourseDao dao = new CourseDao(this);
		dao.update(course);

		/*
		 * 弹出对话框提示更新成功
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("成功更新ID号为：" + course_id + "的课程信息！");
		builder.setPositiveButton("确定", null);
		builder.create();
		builder.show();
	}
}