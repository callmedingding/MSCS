package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.course.CourseDao;


public class DeleteCourse extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText coursedeleteET;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_course);
		/*
		 * 初始化EditText
		 */
		coursedeleteET = (EditText) findViewById(R.id.coursedeleteET);

	}

	/*
	 * 删除按钮监听
	 */
	public void courseDeleteLoad(View v) {
		/*
		 * 获取EditText中的值
		 */
		String str_id = coursedeleteET.getText().toString().trim();
		String course_id ="";
		if (!str_id.equals("")) {
			course_id =str_id;
		}
		// 调用courseDao删除方法进行删除
		CourseDao dao = new CourseDao(this);
		dao.delete(course_id);

		/*
		 * 弹出对话框提示删除成功
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("成功删除ID号为：" + course_id + "的课程信息！");
		builder.setPositiveButton("确定", null);
		builder.create();
		builder.show();
	}
}