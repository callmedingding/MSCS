package com.example.ssc.infohandle;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a11201.godsavemeapplication.R;
import com.example.ssc.db.MyDBHelper;
import com.example.ssc.student.Stu;
import com.example.ssc.student.StuDao;


public class UpdateStu extends Activity {
	/*
	 * 定义EditText
	 */
	private EditText stuupdateET1;
	private EditText stuupdateET2;
	private EditText stuupdateET3;
	private EditText stuupdateET4;
	private EditText stuupdateET5;
	private EditText stuupdateET6;
	private EditText stuupdateET7;
	private EditText stuupdateET8;
	private EditText stuupdateET9;
	private EditText stuupdateET10;
	private EditText stuupdateET11;
	private Button btn_update;
    Bundle bundle = getIntent().getExtras();
    String from=bundle.getString("from");
    String user_id = bundle.getString("user_id");

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_stu);
		/*
		 * 初始化EditText
		 */
		stuupdateET1 = (EditText) findViewById(R.id.stuupdateET1);
		stuupdateET2 = (EditText) findViewById(R.id.stuupdateET2);
		stuupdateET3 = (EditText) findViewById(R.id.stuupdateET3);
		stuupdateET4 = (EditText) findViewById(R.id.stuupdateET4);
		stuupdateET5 = (EditText) findViewById(R.id.stuupdateET5);
		stuupdateET6 = (EditText) findViewById(R.id.stuupdateET6);
		stuupdateET7 = (EditText) findViewById(R.id.stuupdateET7);
		stuupdateET8 = (EditText) findViewById(R.id.stuupdateET8);
		stuupdateET9 = (EditText) findViewById(R.id.stuupdateET9);
		stuupdateET10 = (EditText) findViewById(R.id.stuupdateET10);
		stuupdateET11 = (EditText) findViewById(R.id.stuupdateET11);
		btn_update=(Button)findViewById(R.id.stuupdatebtn);
		btn_update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 stuUpdateLoad(v);//修改
            }
        });
        //填充个人信息

        if(from.equals("首页")){
            getStu(user_id);
        }
        if(from.equals("信息维护")){
            user_id="";

        }
	}

    //获取原来的信息
    public void getStu(String user_id) {
        if(user_id==""){//从信息维护而来，信息需全部自己输入

        }else{//从修改个人而来，自动输入
            MyDBHelper myhelper=new MyDBHelper(this.getBaseContext());
            SQLiteDatabase db = myhelper.getReadableDatabase();
            Cursor cursor = db.rawQuery("select * from tb_person where user_id=?",new String[] { user_id + "" });
            if(cursor.moveToFirst()){
                stuupdateET1.setText(user_id);//当事人修改自己的？
                stuupdateET2.setText(cursor.getString(cursor.getColumnIndex("name")));
                //密码
                stuupdateET3.setText(cursor.getString(cursor.getColumnIndex("user_key")));
                //账号类型
                Cursor cursor_1=db.rawQuery("select * from tb_UserType where type_id=?",
                        new String[]{
                                cursor.getString(cursor.getColumnIndex("type_id"))
                        }
                );
                stuupdateET4.setText(cursor_1.getString(cursor_1.getColumnIndex("type")));
                //性别
                Cursor cursor_2=db.rawQuery("select * from tb_Sex where sex_id=?",
                        new String[]{
                                cursor.getString(cursor.getColumnIndex("sex_id"))
                        }
                );
                stuupdateET5.setText(cursor_2.getString(cursor_2.getColumnIndex("sex")));
                //电话
                stuupdateET6.setText(cursor.getString((cursor.getColumnIndex("tel"))));
                //电子邮箱
                stuupdateET7.setText(cursor.getString(cursor.getColumnIndex("email")));
                //班级
                stuupdateET8.setText(cursor.getString(cursor.getColumnIndex("type")));
                //学院
                Cursor cursor_3=db.rawQuery("select * from tb_class where class_id=?",
                        new String[]{
                                cursor.getString(cursor.getColumnIndex("class_id"))
                        }
                );
                stuupdateET9.setText(cursor_3.getString(cursor_3.getColumnIndex("class_name")));
                //账号状态
                Cursor cursor_4=db.rawQuery("select * from tb_UserStatus where status_id=?",
                        new String[]{
                                cursor.getString(cursor.getColumnIndex("status_id"))
                        }
                );
                stuupdateET10.setText(cursor_4.getString(cursor_4.getColumnIndex("status")));
                //可选课程类型
                String[] course_type=cursor.getString(cursor.getColumnIndex("course_type_set")).split(",");
                String courseType_str="";
                Cursor cursor_5=null;
                for(int i=0;i<course_type.length;i++){
                    cursor_5=db.rawQuery("select * tb_course_type where course_type_id=?",
                            new String[]{course_type[i]+""});
                    String str=cursor_5.getString(cursor_5.getColumnIndex("course_type_name"));
                    courseType_str=courseType_str+str+",";
                }
                stuupdateET11.setText(courseType_str);
            }
        }
    }

	/*
	 * 更新按钮监听
	 */
	public void stuUpdateLoad(View v) {
		/*
		 * 获取每个EditText中的值
		 */
		String str_id = stuupdateET1.getText().toString().trim();
		String user_id = "";
		if (!str_id.equals("")) {
			user_id = str_id;
		}
		String name = stuupdateET2.getText().toString().trim();
		String user_key = stuupdateET3.getText().toString().trim();
		String type_id_str = stuupdateET4.getText().toString().trim();
		String sex_id_str = stuupdateET5.getText().toString().trim();
		String tel_str = stuupdateET6.getText().toString().trim();
		String email = stuupdateET7.getText().toString().trim();
		String class_id_str = stuupdateET8.getText().toString().trim();
		String college_id_str = stuupdateET9.getText().toString().trim();
		String status_id_str = stuupdateET10.getText().toString().trim();
		String course_type_set = stuupdateET11.getText().toString().trim();

		Integer type_id=Integer.parseInt(type_id_str);
		Integer sex_id=Integer.parseInt(sex_id_str);
		Integer tel=Integer.parseInt(tel_str);
		Integer class_id=Integer.parseInt(class_id_str);
		Integer college_id=Integer.parseInt(college_id_str);
		Integer status_id=Integer.parseInt(status_id_str);

		Bundle bundle = getIntent().getExtras();
		String user = bundle.getString("user_id");
		if(user.substring(0,2)!="mng"){
			//只能改部分
			Stu stu=new Stu(user, name, user_key, type_id, sex_id, tel,email,class_id,college_id,status_id,course_type_set);
			StuDao dao = new StuDao(this);
			dao.updatePart(stu);

		}else{
            //管理员修改私人信息
			// 封装成学生对象
			Stu stu = new Stu(user, name, user_key, type_id, sex_id, tel,email,class_id,college_id,status_id,course_type_set);

			// 调用StuDao更新方法进行更新
			StuDao dao = new StuDao(this);
			dao.update(stu);
		}


		/*
		 * 弹出对话框提示更新成功
		 */
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("提示");
		builder.setMessage("成功更新ID号为：" + user_id + "的学生信息！");
		builder.setPositiveButton("确定", null);
		builder.create();
		builder.show();
	}

}