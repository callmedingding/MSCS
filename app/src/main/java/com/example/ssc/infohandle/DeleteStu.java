package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.student.StuDao;


public class DeleteStu extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText studeleteET;
	Intent intent=new Intent();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete_stu);
		/*
		 * 初始化EditText
		 */
		studeleteET = (EditText) findViewById(R.id.studeleteET);

	}

	/*
	 * 删除按钮监听
	 */
	public void stuDeleteLoad(View v) {
		/*
		 * 获取EditText中的值
		 */
		String str_id = studeleteET.getText().toString().trim();
		String user_id = "";
		if (!str_id.equals("")) {
			user_id = str_id;
		}
		// 调用StuDao删除方法进行删除
		StuDao dao = new StuDao(this);
		dao.delete(user_id);
		intent.setClass(this, DeleteStu.class);
		/*
		 * 弹出对话框提示删除成功
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("成功删除ID号为：" + user_id + "的学生信息！");
		builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface arg0, int arg1){
				arg0.dismiss();
				startActivity(intent);
			}
		});
		builder.create();
		builder.show();
	}
}