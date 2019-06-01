package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ssc.infohandle.DeleteCourse;
import com.example.ssc.infohandle.DeleteStu;
import com.example.ssc.infohandle.InsertCourse;
import com.example.ssc.infohandle.InsertStu;
import com.example.ssc.infohandle.SelectCourse;
import com.example.ssc.infohandle.SelectStu;
import com.example.ssc.infohandle.UpdateCourse;
import com.example.ssc.infohandle.UpdateStu;

public class InfoHandleActivity extends Activity {
	Bundle bundle_from=new Bundle();
	Bundle bundle_to=new Bundle();
	String user_id="";
	Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_handle);
		bundle_from = getIntent().getExtras();
		user_id = bundle_from.getString("user_id");
		bundle_to.putString("from", "信息维护");
		bundle_to.putString("user_id", user_id);
	}
	
	/*
	 * 插入学生按钮监听
	 */
	public void menuload1(View v) {
//		Intent intent = new Intent();
		intent.setClass(this, InsertStu.class);
		startActivity(intent);
	}

	/*
	 * 删除学生按钮监听
	 */
	public void menuload2(View v) {
//		Intent intent = new Intent();
		intent.setClass(this, DeleteStu.class);
		startActivity(intent);
	}

	/*
	 * 修改学生按钮监听
	 */
	public void menuload3(View v) {
		/*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/;
		intent.putExtras(bundle_to);
		intent.setClass(this, UpdateStu.class);//可改全部信息
		startActivity(intent);
	}

	/*
	 * 查找学生按钮监听
	 */
	public void menuload4(View v) {
//		Intent intent = new Intent();
		intent.setClass(this, SelectStu.class);
		startActivity(intent);
	}

	/*
	 * 插入课程按钮监听
	 */
	public void menuload5(View v) {
//		Intent intent = new Intent();
		intent.setClass(this, InsertCourse.class);
		startActivity(intent);
	}

	/*
	 * 删除课程按钮监听
	 */
	public void menuload6(View v) {
//		Intent intent = new Intent();
		intent.setClass(this, DeleteCourse.class);
		startActivity(intent);
	}

	/*
	 * 修改课程按钮监听
	 */
	public void menuload7(View v) {
//		Intent intent = new Intent();
		intent.setClass(this, UpdateCourse.class);
		startActivity(intent);
	}

	/*
	 * 查找课程按钮监听
	 */
	public void menuload8(View v) {
//		Intent intent = new Intent();
		intent.setClass(this, SelectCourse.class);
		startActivity(intent);
	}

	/**
	 * @help 返回上一个界面
	 */
	@Override
	public void onBackPressed() {
		finish();
	}
}
