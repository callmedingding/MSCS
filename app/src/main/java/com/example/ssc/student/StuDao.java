package com.example.ssc.student;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ssc.db.MyDBHelper;

public class StuDao {
	private MyDBHelper myhelper;

	public StuDao(Context context) {
		this.myhelper = new MyDBHelper(context);
	}

	public void insert(Stu stu) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL(
				"insert into tb_person values(?,?,?,?,?,?,?,?,?,?,?)",
				new Object[] { stu.getUser_id(), stu.getName(), stu.getUser_key(),
						stu.getType_id(), stu.getSex_id(), stu.getTel(),
						stu.getEmail(), stu.getClass_id(),stu.getCollege_id(),
						stu.getStatus_id(),stu.getCourse_type_set() });
		db.close();
	}

	public void delete(String user_id) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL("delete from tb_person where stu_id = ?", new Object[] { user_id });
		db.close();
	}

	public void update(Stu stu) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL(
				"update tb_person set name=?,stu_key=?,type_id=?,sex_id=?,tel=?,email=?," +
						"class_id=?,college_id=?,status_id=?,course_type_set=? where stu_id=?",
				new Object[] { stu.getName(), stu.getUser_key(),
						stu.getType_id(), stu.getSex_id(), stu.getTel(),
						stu.getEmail(), stu.getClass_id(),stu.getCollege_id(),
						stu.getStatus_id(),stu.getCourse_type_set(),stu.getUser_id() });
		db.close();
	}
	public void updatePart(Stu stu) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL(
				"update tb_person set stu_key=?,tel=?,email=? where stu_id=?",
				new Object[] { stu.getUser_key(), stu.getTel(),
						stu.getEmail(), stu.getUser_id()});
		db.close();
	}

	public Stu select(String user_id) {
		SQLiteDatabase db = myhelper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from tb_person where user_id=?",
				new String[] { user_id + "" });

		Stu stu = null;
		if (c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("name"));
			String user_key = c.getString(c.getColumnIndex("user_key"));
			Integer type_id = c.getInt(c.getColumnIndex("type_id"));
			Integer sex_id = c.getInt(c.getColumnIndex("sex_id"));
			Integer tel = c.getInt(c.getColumnIndex("tel"));
			String email = c.getString(c.getColumnIndex("email"));
			Integer class_id = c.getInt(c.getColumnIndex("class_id"));
			Integer college_id = c.getInt(c.getColumnIndex("college_id"));
            Integer status_id = c.getInt(c.getColumnIndex("status_id"));
			String course_type_set = c.getString(c.getColumnIndex("course_type_set"));


			stu = new Stu(user_id, name, user_key, type_id, sex_id, tel,email, class_id,college_id,status_id,course_type_set);
		}
		c.close();
		db.close();
		return stu;
	}
}
