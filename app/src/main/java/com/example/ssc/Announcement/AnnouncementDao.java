package com.example.ssc.Announcement;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ssc.db.MyDBHelper;

import java.util.ArrayList;
import java.util.HashMap;



public class AnnouncementDao {
    private MyDBHelper myhelper;

    public AnnouncementDao(Context context) {
        this.myhelper = new MyDBHelper(context);
    }

    public void insert(Announcement ann) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL(
                "insert into tb_Announcement(user_id,comments) values(?,?)",
                new Object[] { ann.getUser_id(), ann.getComments()});
        db.close();
    }

    public void delete(String id) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL("delete from tb_Announcement where id = ?", new Object[] { id });
        db.close();
    }

    public void update(Announcement ann) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        db.execSQL(
                "update tb_Announcement set comments=?",
                new Object[] { ann.getComments()+"" });
        db.close();
    }

    //selectAllAnnouncement
    public ArrayList<HashMap<String, String>> selectAllData() {
        SQLiteDatabase db = myhelper.getReadableDatabase();
        Cursor cursor = db.query("tb_Announcement", null, null, null, null, null,null);

        cursor.moveToFirst();
        // 生成动态数组，加入数据
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < cursor.getCount(); i++) {//改
            String publisher_id = Integer.toString(cursor.getColumnIndex("user_id"));
            String comments = cursor.getString(cursor.getColumnIndex("comments"));

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("publisher_id", publisher_id);
            map.put("comments", comments);
            listItem.add(map);

            cursor.moveToNext();
        }

        return listItem;
    }
}
