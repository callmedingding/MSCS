package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.example.ssc.course.CourseDao;
import com.example.ssc.course.MyCourse;
import com.example.ssc.course.MyCourseDao;

//课程详情框
public class CourseDetailActivity extends Activity implements OnClickListener {
	private TextView title, course_detail;
	private Button handleBtn;
	String from,my_id=""; //记录从哪个页面跳转过来
	MyCourse mycourse;//查询已选，可根据user_id和course_id达到不同目的
//	Course course;
	Bundle bundle_from=new Bundle(),bundle_to=new Bundle();
	Intent intent=new Intent();
	Button backBtn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.course_detail);
		title = (TextView) findViewById(R.id.title);
		course_detail = (TextView) findViewById(R.id.course_detail);
		backBtn= (Button) findViewById(R.id.fanhui);
		backBtn.setOnClickListener(this);
		/*获取Intent中的Bundle对象*/  
        bundle_from = this.getIntent().getExtras();
        /*获取Bundle中的数据，注意类型和key*/
		my_id=bundle_from.getString("my_id");
		String str_id = bundle_from.getString("course_id");//从前一页面获取的课程号
		String course_id = "";
		if (!str_id.equals("")) {
			course_id = str_id;

		}
		mycourse=new MyCourse(my_id,course_id);//到时候直接插入选课信息
		String course_name = bundle_from.getString("course_name");
		from = bundle_from.getString("from");//从哪个页面来
		System.out.println("CourseDetailActivity:\tfrom\t"+from);

		title.setText("课程:"+course_name);
		
		handleBtn = (Button) findViewById(R.id.handle);//按钮选课或退课
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
		String str = dao.select(course_id);//课程具体信息，字符串
		course_detail.setText(str+"");
//		if (str.equals("")) {
//			/*
//			 * 弹出对话框提示查询失败
//			 */
//			AlertDialog.Builder builder = new AlertDialog.Builder(this);
//			builder.setTitle("提示");
//			builder.setMessage("课程已满！");
//			builder.setPositiveButton("确定", null);
//			builder.create();
//			builder.show();
//		} else {
//			// 显示出查询结果
//			course_detail.setText(str+"");
//		}
	}

	@Override
	public void onClick(View arg0) {
		bundle_from = getIntent().getExtras();
		String course_id = bundle_from.getString("course_id");//获取课程号
		String user_id = bundle_from.getString("my_id");//获取学号
		switch (arg0.getId()) {
			case R.id.fanhui:
				bundle_to.putString("my_id",user_id);
				bundle_to.putString("from","课程详情");
				intent.putExtras(bundle_to);
				if (from.equals("我的课程")) {
					intent.setClass(this,MyCourseActivity.class);
				} else if (from.equals("立即选课")) {
					intent.setClass(this,SelectCourseActivity.class);
				}
				startActivity(intent);

		case R.id.handle://选课or退课？
			if (from.equals("我的课程")) {//学生与教师都可
				//退课， 调用MyCourseDao删除方法进行删除
				MyCourseDao dao = new MyCourseDao(this);
				mycourse=new MyCourse(user_id,course_id);
				dao.delete(mycourse);//是双主键
				intent.setClass(this,MyCourseActivity.class);
				/*
				 * 弹出对话框提示删除成功
				 */
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("提示");
				builder.setMessage("成功取消ID号为：" + course_id+ "的选课信息！");
				builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface arg0, int arg1){
						arg0.dismiss();
						bundle_to.putString("user_id",my_id);
						bundle_to.putString("from","课程详情");
						intent.putExtras(bundle_to);
						startActivity(intent);
					}
				});
				builder.create();
				builder.show();
			} else if (from.equals("立即选课")) {//just for student
				//选课， 调用courseDao插入方法进行插入
				MyCourseDao dao = new MyCourseDao(this);
				// 封装成课程对象

				MyCourse mycourse = new MyCourse(user_id,course_id);
				int result=dao.insert(mycourse);
				//需要账号和课程号
				switch (result){
					case 1:
						intent.setClass(this,MyCourseActivity.class);
						errorMsg(this, "提示", "选课成功！");
						break;
					case 2:
						intent.setClass(this,MyCourseActivity.class);
						errorMsg(this, "提示", "该课程人数已达上限！请重新选择！");
						//到时候显示课程应该要显示剩余数量！！！！！！
						break;
					case 3:
						intent.setClass(this,SelectCourseActivity.class);
						errorMsg(this, "提示", "不能选该类型的课程！请重新选择！");
						break;
					case 4:
						intent.setClass(this,MyCourseActivity.class);
						errorMsg(this, "提示", "开课成功！");
						break;
					case 5:
						intent.setClass(this,SelectCourseActivity.class);
						errorMsg(this,"提示","该课程已选，无法插入课程信息！");//from infooHandle
						break;
					case 6:
						intent.setClass(this,SelectCourseActivity.class);
						errorMsg(this,"提示","该课程已选，无法插入课程信息！");
						break;
					case 0:
					default:
						intent.setClass(this,SelectCourseActivity.class);
						errorMsg(this, "提示", "???！");
						break;
				}

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
		builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface arg0, int arg1){
				arg0.dismiss();
				bundle_to.putString("user_id",my_id);
				bundle_to.putString("from","课程详情");
				intent.putExtras(bundle_to);
				startActivity(intent);
			}
		});
		builder.create();
		builder.show();
	}


	/**
	 * @help 返回上一个界面
	 */
	@Override
	public void onBackPressed() {
		finish();
	}

}
