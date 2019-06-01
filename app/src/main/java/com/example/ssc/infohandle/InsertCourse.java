package com.example.ssc.infohandle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.course.Course;
import com.example.ssc.course.CourseDao;
import com.example.ssc.course.MyCourse;
import com.example.ssc.course.MyCourseDao;


public class InsertCourse extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText courseinsertET1;
	private EditText courseinsertET2;
	private EditText courseinsertET3;
	private EditText courseinsertET4;
	private EditText courseinsertET5;
	private EditText courseinsertET6;
	private EditText courseinsertET7;
	private EditText courseinsertET8;
	private EditText courseinsertET9;
	private EditText courseinsertET10;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insert_course);
		/*
		 * 初始化EditText
		 */
		courseinsertET1 = (EditText) findViewById(R.id.courseinsertET1);
		courseinsertET2 = (EditText) findViewById(R.id.courseinsertET2);
		courseinsertET3 = (EditText) findViewById(R.id.courseinsertET3);
		courseinsertET4 = (EditText) findViewById(R.id.courseinsertET4);
		courseinsertET5 = (EditText) findViewById(R.id.courseinsertET5);
		courseinsertET6 = (EditText) findViewById(R.id.courseinsertET6);
		courseinsertET7 = (EditText) findViewById(R.id.courseinsertET7);
		courseinsertET8 = (EditText) findViewById(R.id.courseinsertET8);
		courseinsertET9 = (EditText) findViewById(R.id.courseinsertET9);
		courseinsertET10 = (EditText) findViewById(R.id.courseinsertET10);
	}

	/*
	 * 提交按钮监听
	 */
	public void courseInsertLoad(View v) {
		/*
		 * 获取每个EditText中的值
		 */
		String str_id = courseinsertET1.getText().toString().trim();
		String course_id = "";
		if (!str_id.equals("")) {
			course_id = str_id;
		}
		String course_name = courseinsertET2.getText().toString().trim();
		Integer course_type_id = Integer.parseInt(courseinsertET3.getText().toString().trim());
		Integer course_status_id = Integer.parseInt(courseinsertET4.getText().toString().trim());
		Integer class_hour = Integer.parseInt(courseinsertET5.getText().toString().trim());
		Integer credit = Integer.parseInt(courseinsertET6.getText().toString().trim());
		Integer maxnum = Integer.parseInt(courseinsertET7.getText().toString().trim());
		String course_time_id = courseinsertET8.getText().toString().trim();
		String classroom_id = courseinsertET9.getText().toString().trim();
		String time_and_classroom=course_time_id+"-"+classroom_id;
		String tea_id = courseinsertET10.getText().toString().trim();

		// 封装成课程对象
		Course course = new Course(course_id, course_name, course_type_id, course_status_id,
				class_hour, credit,maxnum,course_time_id,classroom_id,time_and_classroom);
		MyCourse mycourse=new MyCourse(tea_id,course_id);
		// 调用courseDao插入方法进行插入
		CourseDao dao = new CourseDao(this);
		MyCourseDao mycoursedao=new MyCourseDao(this);
		dao.insert(course);
		mycoursedao.insert(mycourse);
		/*
		 * 弹出对话框提示插入成功
		 */
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setTitle("提示");
//		builder.setMessage("成功插入课程信息！");
//		builder.setPositiveButton("确定", null);
//		builder.create();
//		builder.show();
	}

}