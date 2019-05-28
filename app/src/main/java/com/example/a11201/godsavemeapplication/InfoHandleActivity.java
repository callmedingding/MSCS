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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.info_handle);
	}
	
	/*
	 * 插入学生按钮监听
	 */
	public void menuload1(View v) {
		Intent intent = new Intent();
		intent.setClass(this, InsertStu.class);
		startActivity(intent);
	}

	/*
	 * 删除学生按钮监听
	 */
	public void menuload2(View v) {
		Intent intent = new Intent();
		intent.setClass(this, DeleteStu.class);
		startActivity(intent);
	}

	/*
	 * 修改学生按钮监听
	 */
	public void menuload3(View v) {
		Bundle bundle = new Bundle();
				/*字符、字符串、布尔、字节数组、浮点数等等，都可以传*/;
		bundle.putString("from", "信息维护");
		Intent intent = new Intent();
		intent.setClass(this, UpdateStu.class);//可改全部信息
		startActivity(intent);
	}

	/*
	 * 查找学生按钮监听
	 */
	public void menuload4(View v) {
		Intent intent = new Intent();
		intent.setClass(this, SelectStu.class);
		startActivity(intent);
	}

	/*
	 * 插入课程按钮监听
	 */
	public void menuload5(View v) {
		Intent intent = new Intent();
		intent.setClass(this, InsertCourse.class);
		startActivity(intent);
	}

	/*
	 * 删除课程按钮监听
	 */
	public void menuload6(View v) {
		Intent intent = new Intent();
		intent.setClass(this, DeleteCourse.class);
		startActivity(intent);
	}

	/*
	 * 修改课程按钮监听
	 */
	public void menuload7(View v) {
		Intent intent = new Intent();
		intent.setClass(this, UpdateCourse.class);
		startActivity(intent);
	}

	/*
	 * 查找课程按钮监听
	 */
	public void menuload8(View v) {
		Intent intent = new Intent();
		intent.setClass(this, SelectCourse.class);
		startActivity(intent);
	}
	
	/*
	 * 插入老师按钮监听
	 */
//	public void menuload9(View v) {
//		Intent intent = new Intent();
//		intent.setClass(this, InsertTea.class);
//		startActivity(intent);
//	}

	/*
	 * 删除老师按钮监听
	 */
//	public void menuload10(View v) {
//		Intent intent = new Intent();
//		intent.setClass(this, DeleteTea.class);
//		startActivity(intent);
//	}

	/*
	 * 修改老师按钮监听
	 */
//	public void menuload11(View v) {
//		Intent intent = new Intent();
//		intent.setClass(this, UpdateTea.class);
//		startActivity(intent);
//	}

	/*
	 * 查找老师按钮监听
	 */
//	public void menuload12(View v) {
//		Intent intent = new Intent();
//		intent.setClass(this, SelectTea.class);
//		startActivity(intent);
//	}

	/**
	 * @help 返回上一个界面
	 */
	@Override
	public void onBackPressed() {
		finish();
	}
}
