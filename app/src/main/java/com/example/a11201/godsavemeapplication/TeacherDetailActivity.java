package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ssc.teacher.Teacher;
import com.example.ssc.teacher.TeacherDao;

public class TeacherDetailActivity extends Activity {
	private TextView title, teacher_detail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher_detail);

		/* 获取Intent中的Bundle对象 */
		Bundle bundle = this.getIntent().getExtras();
		/* 获取Bundle中的数据，注意类型和key */
		String str_id = bundle.getString("t_id");
		Integer t_id = 0;
		if (!str_id.equals("")) {
			t_id = Integer.parseInt(str_id);
		}
		String t_name = bundle.getString("t_name");

		title = (TextView) findViewById(R.id.title);
		teacher_detail = (TextView) findViewById(R.id.teacher_detail);
		title.setText("课程:" + t_name);

		// 调用teaDao查询方法进行查询
		TeacherDao dao = new TeacherDao(this);
		Teacher tea = dao.select(t_id);

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
			teacher_detail.setText("老师ID：" + tea.getId() + "\n\n" + "老师姓名："
					+ tea.getName() + "\n\n" + "老师性别：" + tea.getSex() + "\n\n"
					+ "老师学院：" + tea.getXueyuan() + "\n\n" + "老师联系方式："
					+ tea.getPhonenumber());
		}
	}

}
