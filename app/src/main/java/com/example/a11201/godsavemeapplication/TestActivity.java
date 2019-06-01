package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import com.example.ssc.db.MyDBHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestActivity extends Activity {
    private Spinner spinner;
    private Button button3;
    SimpleAdapter adapter;
    AlertDialog.Builder builder;
    AlertDialog builder2;
    View getlistview;
    ListView listview;
    String set="";
//    boolean[] bl;
    List<Map<String,String>> typelist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
//        spinner= (Spinner) findViewById(R.id.spinner_test);
        button3= (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedb();
            }
        });
//        initview();

    }
    void updatedb(){
        MyDBHelper myDBHelper=new MyDBHelper(this);
        SQLiteDatabase db=myDBHelper.getWritableDatabase();
        db.execSQL("insert into tb_class values(0,'无班级')");
        db.execSQL("insert into tb_college values(0,'无学院')");
        db.execSQL("insert into tb_courseType values(0,'不用选课')");
    }
//    public void initview(){
////        MyDBHelper myDBHelper=new MyDBHelper(this);
////        SQLiteDatabase db=myDBHelper.getWritableDatabase();
////        Cursor cursor=db.rawQuery("select * from tb_class",null);
////        List<Map<String,String>> classlist=new ArrayList<>();
////        if(cursor.moveToFirst()){
////            for (int i=0;i<cursor.getCount();i++){
////                Map<String,String> map=new HashMap<>();
////                map.put("class_name",cursor.getString(cursor.getColumnIndex("class_name")));
////                map.put("class_id",cursor.getString(cursor.getColumnIndex("class_id")));
////                classlist.add(map);
////                cursor.moveToNext();
////            }
////        }
////        SimpleAdapter simpleAdapter=new SimpleAdapter(
////                this,
////                classlist,
////                R.layout.spinner_list,
////                new String[]{"class_name"},
////                new int[]{R.id.name});
////        spinner.setAdapter(simpleAdapter);
////        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
////            @Override
////            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                /*adapterView是指当前的listview；
////                 *view是当前listview中的item的view的布局,就是可用这个view获取里面控件id后操作控件
////                 * i是当前item在listview中适配器的位置
////                 * l是当前item在listview里第几行的位置
////                 */
////                //获得选中项中的HashMap对象
////                HashMap<String,String> map=(HashMap<String,String>)spinner.getItemAtPosition(position);
////                String Text=map.get("class_id");
////                Toast.makeText(TestActivity.this,Text,Toast.LENGTH_SHORT).show();
////            }
////
////            @Override
////            public void onNothingSelected(AdapterView<?> parent) { }
////        });
//        button3 = (Button) findViewById(R.id.button3);
//        showMutilAlertDialog();
//        button3.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//
//                CreateDialog();
//            }
//        });
//    }

//    public void showMutilAlertDialog(){
//        MyDBHelper myDBHelper=new MyDBHelper(this);
//        SQLiteDatabase db=myDBHelper.getWritableDatabase();
//        Cursor cursor=db.rawQuery("select * from tb_courseType",null);
//        if(cursor.moveToFirst()){
//            for (int i=0;i<cursor.getCount();i++){
//                Map<String,String> map=new HashMap<>();
//                map.put("course_type_name",cursor.getString(cursor.getColumnIndex("course_type_name")));
//                map.put("course_type_id",cursor.getString(cursor.getColumnIndex("course_type_id")));
//                typelist.add(map);
//                cursor.moveToNext();
//            }
//        }
//        boolean[] isCheck=new boolean[typelist.size()];
//        //初始化
//        for (int i = 0; i < isCheck.length; i++) {
//            isCheck[i] = false;
//        }
//
////    }
//    class ItemOnClick implements AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
//            CheckBox cBox = (CheckBox) view.findViewById(R.id.X_checkbox);
//            HashMap<String,String> map=(HashMap<String,String>)arg0.getItemAtPosition(position);
//            String Text=map.get("course_type_id");
//            if (cBox.isChecked()) {
//                cBox.setChecked(false);
//                set=set.replace(Text+",","");
//                Log.i("TAG", set);
//            } else {
//                set=set+Text+",";
//                cBox.setChecked(true);
//                Log.i("TAG", set);
//            }
//
////            if (position == 0 && (cBox.isChecked())) {
////                //如果是选中 全选  就把所有的都选上 然后更新
////                for (int i = 0; i < bl.length; i++) {
////                    bl[i] = true;
////                }
////                adapter.notifyDataSetChanged();
////            } else if (position == 0 && (!cBox.isChecked())) {
////                //如果是取消全选 就把所有的都取消 然后更新
////                for (int i = 0; i < bl.length; i++) {
////                    bl[i] = false;
////                }
////                adapter.notifyDataSetChanged();
////            }
////            if (position != 0 && (!cBox.isChecked())) {
////                // 如果把其它的选项取消   把全选取消
////                bl[0] = false;
////                bl[position]=false;
////                adapter.notifyDataSetChanged();
////            } else if (position != 0 && (cBox.isChecked())) {
////                //如果选择其它的选项，看是否全部选择
////                //先把该选项选中 设置为true
////                bl[position]=true;
////                int a = 0;
////                for (int i = 1; i < bl.length; i++) {
////                    if (bl[i] == false) {
////                        //如果有一个没选中  就不是全选 直接跳出循环
////                        break;
////                    } else {
////                        //计算有多少个选中的
////                        a++;
////                        if (a == bl.length - 1) {
////                            //如果选项都选中，就把全选 选中，然后更新
////                            bl[0] = true;
////                            adapter.notifyDataSetChanged();
////                        }
////                    }
////                }
////            }
//        }
//
//    }
//
//

//    public void CreateDialog() {
//        // 动态加载一个listview的布局文件进来
//        LayoutInflater inflater = LayoutInflater.from(this);
//        getlistview = inflater.inflate(R.layout.alertdialog_listview, null);
//
//        // 给ListView绑定内容
//        ListView listview = (ListView) getlistview.findViewById(R.id.X_listview);
//        adapter = new SetSimpleAdapter(this,typelist , R.layout.checkbox_list, new String[] { "course_type_name" },
//                new int[] { R.id.X_text });
//        // 给listview加入适配器
//        listview.setAdapter(adapter);
//        listview.setItemsCanFocus(false);
//        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        listview.setOnItemClickListener(new ItemOnClick());
//
//        builder = new AlertDialog.Builder(this);
//        builder.setTitle("请选择可选课程类型");
//        builder.setIcon(R.drawable.ic_launcher);
//        //设置加载的listview
//        builder.setView(getlistview);
//        builder.setPositiveButton("确定", new DialogOnClick());
//        builder.setNegativeButton("取消", new DialogOnClick());
//        builder.create().show();
////    }
//
////    class DialogOnClick implements DialogInterface.OnClickListener {
////        @Override
////        public void onClick(DialogInterface dialog, int which) {
////            switch (which) {
////                case Dialog.BUTTON_POSITIVE:
////                    //确定按钮的事件
////                    break;
////                case Dialog.BUTTON_NEGATIVE:
////                    //取消按钮的事件
////                    break;
////                default:
////                    break;
////            }
////        }
////    }
//
//    //重写simpleadapterd的getview方法
//    class SetSimpleAdapter extends SimpleAdapter {
//        public SetSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from,
//                                int[] to) {
//            super(context, data, resource, from, to);
//        }
////        @Override
////        public View getView(int position, View convertView, ViewGroup parent) {
////            if (convertView == null) {
////                convertView = LinearLayout.inflate(getBaseContext(), R.layout.alertdialog_listview, null);
////            }
////            CheckBox ckBox = (CheckBox) convertView.findViewById(R.id.X_checkbox);
////            //每次都根据 bl[]来更新checkbox
////            if (bl[position] == true) {
////                ckBox.setChecked(true);
////            } else if (bl[position] == false) {
////                ckBox.setChecked(false);
////            }
////            return super.getView(position, convertView, parent);
////        }
////    }

}


