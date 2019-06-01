package com.example.ssc.course;
//需要结合Person

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ssc.db.MyDBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MyCourseDao {
	private MyDBHelper myhelper;

	public MyCourseDao(Context context) {
		this.myhelper = new MyDBHelper(context);
	}

	public int insert(MyCourse mycourse) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		String user_id=mycourse.getUser_id();
		String course_id=mycourse.getCourse_id();

		if(user_id.substring(0,3).equals("stu")) {
			//需判断是否能够选（该类型是否被包含在自己的set中）
			Cursor cursor_1=db.rawQuery("select * from tb_person where user_id=?",
					new String[]{user_id+""});
			cursor_1.moveToFirst();
			String course_type_set=cursor_1.getString(cursor_1.getColumnIndex("course_type_set"));
			Cursor cursor_2=db.rawQuery("select * from tb_course where course_id=?",
					new String[]{course_id+""});
			cursor_2.moveToFirst();
			String course_type_id=cursor_2.getString(cursor_2.getColumnIndex("course_type_id"));
			// 是否还有名额
			Cursor cursor_3=db.rawQuery("select count(*) from tb_stucourse where course_id=?",new String[]{course_id+""});
			cursor_3.moveToFirst();
			int count = cursor_3.getCount();
			Cursor cursor_4=db.rawQuery("select * from tb_course where course_id=?",new String[]{course_id+""});
			cursor_4.moveToFirst();
			int maxnum=cursor_4.getInt(cursor_4.getColumnIndex("maxnum"));
			Cursor cursor_5=db.rawQuery("select * from tb_stucourse where user_id=? and course_id=?",new String[]{user_id,course_id});

			if(count<maxnum&&course_type_set.contains(course_type_id+",")){
				//可以选
				db.execSQL("insert into tb_Stucourse values(?,?)", new Object[]{
						mycourse.getUser_id(), mycourse.getCourse_id()});//选课
				return 1;//成功
			}
			else if(count>=maxnum){
				//人数超过上限
				return 2;
			}else if(!course_type_set.contains(course_type_id)){
				//不能选该类型课程
				return 3;
			} else if (cursor_5.getCount()!=0) {
				return 6;
			}

		}else if(user_id.substring(0,3).equals("mng")){//管理员在插入课程时也提交开课信息
			//迁至增设课程处！！！！！！
			//课程id唯一
			Cursor cursor_5=db.rawQuery("select * from tb_teacourse where course_id=?",new String[]{course_id+""});
			if (!cursor_5.moveToFirst()) {
				db.execSQL("insert into tb_Teacourse values(?,?)", new Object[]{
						mycourse.getUser_id(), mycourse.getCourse_id()});//老师开设课程
				return 4;//似乎没必要，管理员在添加课程时就已经插入了该信息
			}else {
				return 5;//已有开课信息无法插入
			}
		}
		db.close();
		return 0;//正常？
	}

	public void delete(MyCourse mycourse) {//退课或取消课程
		SQLiteDatabase db = myhelper.getWritableDatabase();
		if(mycourse.getUser_id().substring(0,3).equals("stu")) {
			db.execSQL("delete from tb_stucourse where user_id=? and course_id=?",
					new Object[]{mycourse.getUser_id(), mycourse.getCourse_id()});//退选课程
		}else{
			Log.v("MyCourseDao:","wronging");
		}
		db.close();
	}


	public String selectAllMyCourse(String user_id){//???
		SQLiteDatabase db = myhelper.getReadableDatabase();
		Cursor cursor = null;
		if(user_id.substring(1,2)=="stu") {
			cursor = db.rawQuery("select * from tb_stuCourse where user_id=?",
					new String[]{user_id + ""});
		}else if(user_id.substring(0,2)=="tea"){
			cursor = db.rawQuery("select * from tb_teaCourse where user_id=?",
					new String[]{user_id + ""});
		}

		String course_id=cursor.getString(cursor.getColumnIndex("course_id"));
		//cursor.getString(cursor.getColumnIndex("course_id"));
		Cursor cursor_1=db.rawQuery("select * from tb_course where course_id=?",new String[]{course_id+""});
		String course_name=cursor_1.getString(cursor_1.getColumnIndex("course_name"));

		return course_name;//改
	}


	public String selectOneCourse(MyCourse mycourse) {//查某一门课程详细信息
		Cursor cursor=null;
		SQLiteDatabase db = myhelper.getReadableDatabase();
		if(mycourse.getUser_id().substring(0,2)=="stu") {
			cursor = db.rawQuery("select * from tb_course where course_id=(" +
					"select * from tb_Stucourse where user_id=? and course_id=?)",
					new String[]{mycourse.getUser_id(),mycourse.getCourse_id()});
		}else if(mycourse.getUser_id().substring(0,2)=="tea"){
			cursor = db.rawQuery("select * from tb_course where course_id=(" +
					"select * from tb_Stucourse where user_id=? and course_id=?)",
					new String[]{mycourse.getUser_id(),mycourse.getCourse_id()});
		}

		String course_name="",course_type_name="",course_status="",class_hour="",credit="";
		String weekday="",section="",week_set="";
		String building_name="",floor_name="",room_name="";
		String str="";
		int maxnum=0,left = 0;
		//是某一门具体课程，那就也需要课程号
		if (cursor.moveToNext()) {//tb_course
			course_name = cursor.getString(cursor.getColumnIndex("course_name"));

			//tb_courseType
			Cursor cursor_1=db.rawQuery("select * from tb_CourseType where course_type_id=?",
					new String[]{
							cursor.getString(cursor.getColumnIndex("course_type_id"))
					}
			);
			course_type_name=cursor_1.getString(cursor_1.getColumnIndex("course_type_name"));

			//tb_courseStatus
			Cursor cursor_2=db.rawQuery("select * from tb_CourseStatus where course_status_id=?",
					new String[]{
							cursor.getString(cursor.getColumnIndex("course_status_id"))
					});
			course_status = cursor_2.getString(cursor_2.getColumnIndex("course_status"));

			class_hour = cursor.getString(cursor.getColumnIndex("class_hour"));
			credit = cursor.getString(cursor.getColumnIndex("credit"));
			maxnum=cursor.getInt(cursor.getColumnIndex("maxnum"));
			Cursor cursor_12=db.rawQuery("select count(*) from tb_stucourse where course_id=?",
					new String[]{mycourse.getCourse_id()+""});
			left=maxnum-cursor_12.getCount();
			if(left<=0){
				return "不能选了@selectOneCourse@myCourseDao";
			}
			//可得到class_time_id和week_set_id
			Cursor cursor_3=db.rawQuery("select * from tb_CourseTime where course_time_id=?",
					new String[]{
							cursor.getString(cursor.getColumnIndex("course_time_id"))
					});
			Cursor cursor_4=db.rawQuery("select * from tb_ClassTime where class_time_id=?",
					new String[]{
					cursor_3.getString(cursor_3.getColumnIndex("class_time_id"))
					});
			//weekdayid    cursor_4.getString(cursor_4.getColumnIndex("weekday_id"));
			Cursor cursor_5=db.rawQuery("select * from tb_Weekday where weekday_id=?",
					new  String[]{
							cursor_4.getString(cursor_4.getColumnIndex("weekday_id"))
					});
			weekday=cursor_5.getString(cursor_5.getColumnIndex("weekday"));
			//section      cursor_4.getString(cursor_4.getColumnIndex("section_id"));
			Cursor cursor_6=db.rawQuery("select * from tb_Section where section_id=?",
					new  String[]{
							cursor_4.getString(cursor_4.getColumnIndex("section_id"))
					});
			section=cursor_6.getString(cursor_5.getColumnIndex("section"));

			//weekset   select * from tb_WeekSet where week_set_id=?
			Cursor cursor_7=db.rawQuery("select * from tb_WeekSet where week_set_id=?",
					new String[]{
							cursor_3.getString(cursor_3.getColumnIndex("week_set_id"))
					});
			week_set=cursor_7.getString(cursor_7.getColumnIndex("week_set"));

			//classroom_id    cursor.getString(cursor.getColumnIndex("classroom_id"))
			Cursor cursor_8=db.rawQuery("select * from tb_Classroom where classroom_id=?",
					new String[]{
							cursor.getString(cursor.getColumnIndex("classroom_id"))
					});

			Cursor cursor_9=db.rawQuery("select * from tb_Building where building_id=?",
					new String[]{
							cursor_8.getString(cursor_8.getColumnIndex("building_id"))
					});
			building_name=cursor_9.getString(cursor_9.getColumnIndex("building_name"));

			Cursor cursor_10=db.rawQuery("select * from tb_Floor where floor_id=?",
					new String[]{
							cursor_8.getString(cursor_8.getColumnIndex("floor_id"))
					});
			floor_name=cursor_10.getString(cursor_10.getColumnIndex("floor_name"));
			Cursor cursor_11=db.rawQuery("select * from tb_Room where room_id=?",
					new String[]{
							cursor_8.getString(cursor_8.getColumnIndex("room_id"))
					});
			room_name=cursor_11.getString(cursor_11.getColumnIndex("room_name"));
		}
		cursor.close();
		db.close();
		//course_name,course_type_name,course_status,class_hour,credit,maxnum,weekday,section,week_set,building_name,floor_name,room_name
		str="课程名称："+course_name+"\n"
				+"课程类型："+course_type_name+"\n"
				+"课程状态："+course_status+"\n"
				+"课程学时："+class_hour+"\n"
				+"课程学分："+credit+"\n"
				+"剩余名额："+String.valueOf(left)+"\n"
				+"上课时间：\t"+"第 "+week_set+"周"+"\n"
							+"\t\t\t\t\t\t星期"+weekday+"\t第"+section+"节"+"\n"
				+"上课地点："+building_name+floor_name+"楼"+room_name+"号室";
		return str;//用作alertdialog
	}
	
	public ArrayList<HashMap<String, String>> getAllData(String user_id) {//改
		SQLiteDatabase db = myhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from tb_StuCourse where user_id=?",new String[]{user_id});//看着改
		cursor.moveToFirst();

		// 生成动态数组，加入数据
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < cursor.getCount(); i++) {
			String course_id = cursor.getString(cursor.getColumnIndex("course_id"));
			Cursor cursor1=db.rawQuery("select * from tb_Course where course_id=?",new String[]{course_id});
			cursor1.moveToFirst();
			String course_name = cursor1.getString(cursor1.getColumnIndex("course_name"));
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("course_id", course_id);
			map.put("course_name", course_name);
			listItem.add(map);
			cursor.moveToNext();
		}
		return listItem;
	}


	//	public void update(Course course) {
//		SQLiteDatabase db = helper.getWritableDatabase();
//		db.execSQL(
//				"update tab_mycourse set course_name=?,lessonTime=?,lessonHour=?, lessonPoints=?, c_teacher=? where course_id=?",
//				new Object[] { course.getName(), course.getLessonTime(),
//						course.getLessonHour(), course.getLessonPoints(), course.getC_teacher(),
//						course.getId() });
//		db.close();
//	}
}