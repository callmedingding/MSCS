package com.example.ssc.teacher;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ssc.db.MyDBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class TeacherDao {
	private MyDBHelper myhelper;

	public TeacherDao(Context context) {
		this.myhelper = new MyDBHelper(context);
	}

	public void insert(Teacher teacher) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL("insert into tab_teacher values(?,?,?,?,?)", new Object[] {
				teacher.getId(), teacher.getName(), teacher.getSex(),
				teacher.getXueyuan(), teacher.getPhonenumber() });
		db.close();
	}

	public void delete(int id) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL("delete from tab_teacher where tea_id=?", new Object[] { id });
		db.close();
	}

	public void update(Teacher teacher) {
		SQLiteDatabase db = myhelper.getWritableDatabase();
		db.execSQL(
				"update tab_teacher set tea_name=?,tea_sex=?,xueyuan=?, phonenumber=? where tea_id=?",
				new Object[] { teacher.getName(), teacher.getSex(),
						teacher.getXueyuan(), teacher.getPhonenumber(),
						teacher.getId() });
		db.close();
	}

	public Teacher select(int id) {
		SQLiteDatabase db = myhelper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from tab_teacher where tea_id=?",
				new String[] { id + "" });

		Teacher teacher = null;

		if (c.moveToNext()) {
			String name = c.getString(c.getColumnIndex("tea_name"));
			String sex = c.getString(c.getColumnIndex("tea_sex"));
			String xueyuan = c.getString(c.getColumnIndex("xueyuan"));
			String phonenumber = c.getString(c.getColumnIndex("phonenumber"));
			teacher = new Teacher(id, name, sex, xueyuan, phonenumber);
		}
		c.close();
		db.close();
		return teacher;
	}
	
	public ArrayList<HashMap<String, String>> getAllData() {
		SQLiteDatabase db = myhelper.getReadableDatabase();
		Cursor cursor = db.query("tab_teacher", null, null, null, null, null, null);
		
		cursor.moveToFirst();
		// 生成动态数组，加入数据
		ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < cursor.getCount(); i++) {
			String t_id = Integer.toString(cursor.getInt(cursor.getColumnIndex("tea_id")));
			String name = cursor.getString(cursor.getColumnIndex("tea_name"));
			String sex = cursor.getString(cursor.getColumnIndex("tea_sex"));
			String xueyuan = cursor.getString(cursor.getColumnIndex("xueyuan"));
			String phonenumber = cursor.getString(cursor.getColumnIndex("phonenumber"));
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("t_id", t_id);
			map.put("name", name);
			map.put("sex", sex);
			map.put("xueyuan", xueyuan);
			map.put("phonenumber", phonenumber);
			listItem.add(map);
			
			cursor.moveToNext();
		}
		
		return listItem;
	}
	
}
