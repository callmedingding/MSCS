package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.teacher.Teacher;
import com.example.ssc.teacher.TeacherDao;


public class SelectTea extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText teaselectET;
	/*
	 * 定义TextView
	 */
	private TextView teaselectTV;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_tea);
		/*
		 * 初始化EditText
		 */
		teaselectET = (EditText) findViewById(R.id.teaselectET);
		// 初始化TextView
		teaselectTV = (TextView) findViewById(R.id.teaselectTV3);
	}

	/*
	 * 查询按钮监听
	 */
	public void teaSelectLoad(View v) {
		/*
		 * 获取EditText中的值
		 */
		String str_id = teaselectET.getText().toString().trim();
		Integer id = 0;
		if (!str_id.equals("")) {
			id = Integer.parseInt(str_id);
		}
		// 调用teaDao查询方法进行查询
		TeacherDao dao = new TeacherDao(this);
		Teacher tea = dao.select(id);
		
		System.out.println("哈哈哈"+tea);

		if (tea == null) {
			/*
			 * 弹出对话框提示查询失败
			 */
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("提示");
			builder.setMessage("您查询的老师信息不存在！");
			builder.setPositiveButton("确定", null);
			builder.create();
			builder.show();
		} else {
			// 显示出查询结果
			teaselectTV.setText("老师ID：" + tea.getId() + "\n" + "老师姓名："
					+ tea.getName() + "\n" + "老师性别：" + tea.getSex() + "\n"
					+ "老师学院：" + tea.getXueyuan() + "\n" + "老师联系方式："
					+ tea.getPhonenumber());
		}
	}
}