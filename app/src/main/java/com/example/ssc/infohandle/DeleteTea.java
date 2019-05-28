package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.teacher.TeacherDao;


public class DeleteTea extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText teadeleteET;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_tea);
		/*
		 * 初始化EditText
		 */
		teadeleteET = (EditText) findViewById(R.id.teadeleteET);

	}

	/*
	 * 删除按钮监听
	 */
	public void teaDeleteLoad(View v) {
		/*
		 * 获取EditText中的值
		 */
		String str_id = teadeleteET.getText().toString().trim();
		Integer id = 0;
		if (!str_id.equals("")) {
			id = Integer.parseInt(str_id);
		}
		// 调用teaDao删除方法进行删除
		TeacherDao dao = new TeacherDao(this);
		dao.delete(id);

		/*
		 * 弹出对话框提示删除成功
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("成功删除ID号为：" + id + "的老师信息！");
		builder.setPositiveButton("确定", null);
		builder.create();
		builder.show();
	}
}