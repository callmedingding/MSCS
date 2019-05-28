package com.example.a11201.godsavemeapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ssc.Code.CodeUtils;
import com.example.ssc.SharedPreference.SharedHelper;
import com.example.ssc.db.MyDBHelper;

import java.util.Map;

public class LoginActivity extends Activity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getName();
    private EditText edUser, edKey, edCode;//用户，密码，验证码
    private ImageView ivCode;//验证码图片
    private ImageView clrImg;
    private Button btnLogin;

    private String realCode;
    private String userId;
    private String userKey;

    private SharedHelper sh;
    private Context mContext;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initview();//初始化界面
    }

    @Override
    protected void onStart() {
        super.onStart();
        //导入上次登录的账号及密码
        mContext = getApplicationContext();
        sh = new SharedHelper(mContext);
        //初始化时显示上次登录账户及密码
        Map<String, String> data = sh.read();
        edUser.setText(data.get("username"));
        edKey.setText(data.get("userkey"));

    }

    public void findId() {
        edUser = (EditText) findViewById(R.id.ed_user);
        edKey = (EditText) findViewById(R.id.ed_key);
        clrImg = (ImageView) findViewById(R.id.clrImg);
        edCode = (EditText) findViewById(R.id.ed_vaild);
        ivCode = (ImageView) findViewById(R.id.iv_code);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    /**
     * 初始化界面函数
     */
    public void initview() {
        findId();
        //初始化显示验证码
        ivCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
        realCode = CodeUtils.getInstance().getCode().toLowerCase();
        ivCode.setOnClickListener(this);
        //监听函数
        clrImg.setOnClickListener(this);
        ivCode.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //重新生成图片验证码
            case R.id.iv_code:
                ivCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
                realCode = CodeUtils.getInstance().getCode().toLowerCase();
                Log.v(TAG, "realCode" + realCode);
                break;
            //清除密码
            case R.id.clrImg:
                edKey.setText(null);
                //Toast.makeText(this, "点击了清除密码", Toast.LENGTH_SHORT).show();
                break;
            //登录按钮，先验证验证码是否正确，再调用login函数
            case R.id.btnLogin:

                login(view);
//                Intent intent;
//                intent = new Intent(this, SearchDataActivity.class);
//                startActivity(intent);
                break;
        }
    }

    /**
     * 点击登录按钮后的反应
     */
    //需等加入数据库后修改
    public void login(View view) {
        userId = edUser.getText().toString().trim();
        userKey = edKey.getText().toString().trim();
        String codeStr = edCode.getText().toString().trim();

        Log.e("codeStr", codeStr);
        Log.e("code", realCode);

        // 判断用户名是否为空
        if (userId.equals("")) {
            errorMsg(this, "错误信息", "用户名不能为空！");
        }
        // 判断密码是否为空
        else if (userKey.equals("")) {
            errorMsg(this, "错误信息", "密码不能为空！");
        } else if (codeStr == null || TextUtils.isEmpty(codeStr)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        MyDBHelper myDBHelper=new MyDBHelper(this);
        SQLiteDatabase db=myDBHelper.getWritableDatabase();
//        Cursor cursor=db.query("tb_Person",new String[]{"user_id","user_key"},"user_id='stu12301'",null,null,null,null);
        String name="";
        Cursor cursor=db.query("tb_Person",null,"user_id='"+userId+"' and user_key='"+userKey+"'",null,null,null,null);
//        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
//            name=cursor.getString(1);
//        }
        if (!cursor.moveToFirst()) {//要改
            errorMsg(this, "错误信息", "用户名或密码错误，请重新输入");
            Log.e("CURSOR:\n","NULL");
        } else {
            if (realCode.equalsIgnoreCase(codeStr)) {
                Toast.makeText(this, "验证码正确", Toast.LENGTH_SHORT).show();
                userId = edUser.getText().toString();
                userKey = edKey.getText().toString();
                sh.save(userId, userKey);
                name=cursor.getString(1);
                // 全部正确跳转到主菜单页面
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("user_id",userId);//后面备用
                bundle.putString("name", name);
                intent.putExtras(bundle);
                intent.setClass(this, MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                //错误则更新验证码
                ivCode.setImageBitmap(CodeUtils.getInstance().createBitmap());
                realCode = CodeUtils.getInstance().getCode().toLowerCase();
                //清空验证码框
                edCode.setText(null);
            }
        }
        cursor.close();
        db.close();
    }

    // 错误消息对话框
    public void errorMsg(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("确定", null);
        builder.create();
        builder.show();
    }

    /**
     {
     //初始添加学生
     int stu_id=1001;
     String stu_name="zhangsan";
     String stu_sex="男";
     String stu_idcard="201511621301";
     String psw="123456";
     String stu_academy="Computer";
     String stu_profession="compputer";
     int grade=2;
     Stu stu = new Stu(stu_id, stu_name, stu_sex, stu_idcard, psw,
     stu_academy, stu_profession, grade);
     StuDao dao = new StuDao(this);
     dao.insert(stu);
     }
     */
}