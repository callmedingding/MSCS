package com.example.ssc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    //学号，姓名，性别，身份证号，密码，学院，专业，年级
    String createTableStu = "create table tab_stu (stu_id int , stu_name varchar(10), "
            + "stu_sex varchar(10), stu_idcard varchar(18),"
            + " stu_password varchar(10), stu_academy varchar(20), "
            + "stu_profession varchar(20), stu_grade int,primary key('stu_id')) ";
    //教工号，姓名，性别，学院，手机号
    String createTabTeacher = "create table tab_teacher (tea_id int, tea_name varchar(10) ,"
            + "tea_sex varchar(10) , academy varchar(25) ,"
            + "phonenumber varchar(20) , primary key('tea_id') )";

    //课程号，课程名，开课学期，课时，学分，任课教师
    String createTabCourse = "create table tab_course (course_id int, course_name varchar(25) ,"
            + "lessonTime varchar(25) , lessonHour int ,"
            + "lessonPoints int , c_teacher varchar(25), primary key('course_id') )";

    //课程号，课程名，上课时间，学时，学分，任课老师
    String createTabMyCourse = "create table tab_mycourse (course_id int, course_name varchar(25) ,"
            + "lessonTime varchar(25) , lessonHour int ,"
            + "lessonPoints int , c_teacher varchar(25), primary key('course_id') )";


	public DBHelper(Context context) {
        super(context, "db_mscs.db", null, 1);
        this.context=context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
        		/*
		 * 创建tab_stu【学生表】
		 */
        db.execSQL(createTableStu);
        Toast.makeText(context,"Create stu succeeded",Toast.LENGTH_SHORT).show();

		/*
		 * 创建tab_teacher【老师表】
		 */
		db.execSQL(createTabTeacher);
        Toast.makeText(context,"Create tea succeeded",Toast.LENGTH_SHORT).show();

		/*
		 * 创建tab_cource【课程表】
		 */
		db.execSQL(createTabCourse);
        Toast.makeText(context,"Create course succeeded",Toast.LENGTH_SHORT).show();

		/*
		 * 创建tab_mycource【我的课程表】
		 */
		db.execSQL(createTabMyCourse);
        Toast.makeText(context,"Create MyCourse succeeded",Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("insert into tab_stu values(12301,zhangsan,M,12301,123456,Computer,computer,2)");
		//db.execSQL("insert into tab_teacher values(1001,susan,F,Computer,1001)");
		//Log.v("MESSAGE;","FINISHED onUpgrade. ");

	}

}