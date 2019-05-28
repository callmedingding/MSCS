package com.example.ssc.course;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ssc.db.MyDBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class CourseDao {
	private MyDBHelper myhelper;

	public CourseDao(Context context) {
		this.myhelper = new MyDBHelper(context);
	}

	public void insert(Course course) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL("insert into tab_course values(?,?,?,?,?,?,?,?,?,?,?)", new Object[] {
				course.getCourse_id(), course.getCourse_name(), course.getCourse_type_id(),
				course.getCourse_status_id(), course.getClass_hour(), course.getCredit(),
				course.getMaxnum(),course.getCourse_time_id(), course.getClassroom_id(),
				course.getTime_and_classroom()});
		db.close();
	}

	public void delete(String course_id) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL("delete from tb_Course where course_id=?", new Object[] { course_id });
		db.close();
	}

	public void update(Course course) {//开发者
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL(
				"update tb_course set course_name=?,course_type_id=?,course_status_id=?, class_hour=?, credit=?," +
						"maxnum=?,course_time_id=?,classroom_id=?,time_and_classroom=? where course_id=?",
				new Object[] { course.getCourse_name(), course.getCourse_type_id(),
						course.getCourse_status_id(), course.getClass_hour(), course.getCredit(),
						course.getMaxnum(),course.getCourse_time_id(),course.getClassroom_id()
						,course.getTime_and_classroom(),course.getCourse_id() });
		db.close();
	}

	//具体课程信息，for one
	public String select(String course_id) {
		SQLiteDatabase db = myhelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from tb_course where course_id=?",new String[] { course_id + "" });

		String course_name="",course_type_name="",course_status="",class_hour="",credit="",maxnum="";
		String weekday="",section="",week_set="";
		String building_name="",floor_name="",room_name="";
		String str="";

		/*
		 if(mycourse.getUser_id().contains("stu")) {
		 cursor = db.rawQuery("select * from tb_course where course_id=(" +
		 "select * from tb_Stucourse where user_id=? and course_id=?)",
		 new String[]{mycourse.getUser_id(),mycourse.getCourse_id()});
		 }else if(mycourse.getUser_id().contains("tea")){
		 cursor = db.rawQuery("select * from tb_course where course_id=(" +
		 "select * from tb_Stucourse where user_id=? and course_id=?)",
		 new String[]{mycourse.getUser_id(),mycourse.getCourse_id()});
		 }


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
		 maxnum=cursor.getString(cursor.getColumnIndex("maxnum"));

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
		 +"上限人数："+maxnum+"\n"
		 +"上课时间：\t"+"第 "+week_set+"周"+"\n"
		 +"\t\t\t\t\t\t星期"+weekday+"\t第"+section+"节"+"\n"
		 +"上课地点："+building_name+floor_name+"楼"+room_name+"号室";
		 */

		//是某一门具体课程，那就也需要课程号
		if (cursor.moveToNext()) {
			course_name = cursor.getString(cursor.getColumnIndex("course_name"));

			//tb_courseType
			Cursor cursor_1=db.rawQuery("select * from tb_CourseType where course_type_id=?",
					new String[]{
							cursor.getString(cursor.getColumnIndex("course_type_id"))
					});
			course_type_name=cursor_1.getString(cursor_1.getColumnIndex("course_type_name"));

			//tb_courseStatus
			Cursor cursor_2=db.rawQuery("select * from tb_CourseStatus where course_status_id=?",
					new String[]{
							cursor.getString(cursor.getColumnIndex("course_status_id"))
					});
			course_status = cursor_2.getString(cursor_2.getColumnIndex("course_status"));

			class_hour = cursor.getString(cursor.getColumnIndex("class_hour"));
			credit = cursor.getString(cursor.getColumnIndex("credit"));
			maxnum=cursor.getString(cursor.getColumnIndex("maxnum"));

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
			str="课程名称："+course_name+"\n"
					+"课程类型："+course_type_name+"\n"
					+"课程状态："+course_status+"\n"
					+"课程学时："+class_hour+"\n"
					+"课程学分："+credit+"\n"
					+"上限人数："+maxnum+"\n"
					+"上课时间：\t"+"第 "+week_set+"周"+"\n"
					+"\t\t\t\t\t\t星期"+weekday+"\t第"+section+"节"+"\n"
					+"上课地点："+building_name+floor_name+"楼"+room_name+"号室";
		}
		cursor.close();
		db.close();
		return str;
	}

	//search all
	public ArrayList<HashMap<String, String>> getAllData() {
		SQLiteDatabase db = myhelper.getReadableDatabase();
		Cursor cursor = db.query("tb_course", null, "where course_status_td='1'", null, null, null, null);
		//不开放和满额的不显示
		cursor.moveToFirst();
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < cursor.getCount(); i++) {//改
			String course_id = Integer.toString(cursor.getColumnIndex("course_id"));
			String course_name = cursor.getString(cursor.getColumnIndex("course_name"));
//			String course_type_id = cursor.getString(cursor.getInt(cursor.getColumnIndex("course_time_id")));
//			String course_status_id = Integer.toString(cursor.getInt(cursor.getColumnIndex("course_status_id")));
//			String class_hour = Integer.toString(cursor.getInt(cursor.getColumnIndex("class_hour")));
//			String credit = cursor.getString(cursor.getInt(cursor.getColumnIndex("credit")));
//			String maxnum = cursor.getString(cursor.getInt(cursor.getColumnIndex("maxnum")));
//			String course_time_id = cursor.getString(cursor.getColumnIndex("course_time_id"));
//			String classroom_id = cursor.getString(cursor.getColumnIndex("classroom_id"));
			//不用显示time_and_classroom
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("course_id", course_id);
			map.put("course_name", course_name);
			listItem.add(map);
			
			cursor.moveToNext();
		}
		
		return listItem;
	}
	
}
