package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.ssc.course.Course;
import com.example.ssc.course.CourseDao;
import com.example.ssc.course.MyCourse;
import com.example.ssc.course.MyCourseDao;

public class CourseDetailActivity extends Activity implements OnClickListener {
	private TextView title, course_detail;
	private Button handleBtn;
	String from; //记录从哪个页面跳转过来
	Course course;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_detail);
		
		/*
		 * 标题显示
		 */
		// Bundle bundle = getIntent().getExtras();
		// SerializableMap serializableMap = (SerializableMap)
		// bundle.get("map");
		// System.out.println("哈哈哈"+serializableMap.getMap());

		/*获取Intent中的Bundle对象*/  
        Bundle bundle = this.getIntent().getExtras();  
        /*获取Bundle中的数据，注意类型和key*/  
		String str_id = bundle.getString("course_id");
		String course_id = "";
		if (!str_id.equals("")) {
			course_id = str_id;
		}
		String course_name = bundle.getString("course_name");
		from = bundle.getString("from");
		System.out.println("CourseDetailActivity:\tfrom\t"+from);
		
		title = (TextView) findViewById(R.id.title);
		course_detail = (TextView) findViewById(R.id.course_detail);///!!!!
		title.setText("课程:"+course_name);
		
		handleBtn = (Button) findViewById(R.id.handle);
		handleBtn.setOnClickListener(this);
		//两个界面用同一个view，已选的对应按钮变退课，未选的按钮变选课
		if (from.equals("我的课程")) {
			handleBtn.setText("退课");
		} else if (from.equals("立即选课")) {
			handleBtn.setText("选课");
		} else {
			handleBtn.setVisibility(View.GONE);
		}

		// 调用CourseDao查询方法进行查询
		CourseDao dao = new CourseDao(this);
		String str = dao.select(course_id);

		if (course == null) {
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
			// 显示出查询结果
			course_detail.setText(str+"");
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.handle://
			String course_id = course.getCourse_id();//获取课程号
			Bundle bundle = getIntent().getExtras();
			String user_id = bundle.getString("name");//获取学号
			if (from.equals("我的课程")) {//学生与教师都可
				// 调用MyCourseDao删除方法进行删除
				MyCourseDao dao = new MyCourseDao(this);
				MyCourse mycourse=new MyCourse(user_id,course.getCourse_id());
				dao.delete(mycourse);//是双主键
				/*
				 * 弹出对话框提示删除成功
				 */
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("提示");
				builder.setMessage("成功取消ID号为：" + course.getCourse_id() + "的选课信息！");
				builder.setPositiveButton("确定", null);
				builder.create();
				builder.show();
			} else if (from.equals("立即选课")) {
				// 调用courseDao插入方法进行插入
				MyCourseDao dao = new MyCourseDao(this);
				// 封装成课程对象

				MyCourse mycourse = new MyCourse(user_id,course_id);
				int result=dao.insert(mycourse);
				//需要账号和课程号

				switch (result){
					case 1:
						errorMsg(this, "提示", "选课成功！");
						break;
					case 2:
						errorMsg(this, "提示", "该课程人数已达上限！请重新选择！");
						//到时候显示课程应该要显示剩余数量！！！！！！
						break;
					case 3:
						errorMsg(this, "提示", "不能选该类型的课程！请重新选择！");
						break;
					case 4:
						errorMsg(this, "提示", "开课成功！");
						break;
					case 0:
					default:
						break;
				}
//				errorMsg(this, "错误信息", "密码不能为空！");
				/*
				 * 弹出对话框提示插入成功
				 */
//				AlertDialog.Builder builder = new AlertDialog.Builder(this);
//				builder.setTitle("提示");
//				builder.setMessage("恭喜，选课成功！");
//				builder.setPositiveButton("确定", null);
//				builder.create();
//				builder.show();
			} else {
				errorMsg(this, "错误信息from", from);
			}
			break;
		default:
			break;
		}
	}
	public void errorMsg(Context context, String title, String message) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("确定", null);
		builder.create();
		builder.show();
	}

}
