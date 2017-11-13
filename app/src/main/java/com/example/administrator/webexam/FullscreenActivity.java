package com.example.administrator.webexam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.AdminActivity;
import com.example.administrator.webexam.Activity.LoginActivity;
import com.example.administrator.webexam.Activity.MainActivity;
import com.example.administrator.webexam.Module.Data.Student;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.sms.BmobSMS;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.example.administrator.webexam.R.id.phone;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    private static final int START_ACTIVITY_LOGIN = 0x1;
    private static final int START_ACTIVITY_MAIN = 0x2;
    private String type;
    private String LOGIN_TYPE="login_type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        ButterKnife.bind(this);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ViewGroup mContentView = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
            ViewCompat.setFitsSystemWindows(mChildView, false);
        }

        Bmob.initialize(this, "7d0c7d78e1e7f4da39b739bf0f280b3f");
        BmobSMS.initialize(this, "7d0c7d78e1e7f4da39b739bf0f280b3f");

        SharedPreferences userInfo = this.getSharedPreferences("user_login", 0);
    Boolean islogin =userInfo.getBoolean("islogin",false);
     type = userInfo.getString("type","student");
        if (islogin){

            handler.sendEmptyMessageDelayed(START_ACTIVITY_MAIN,3);
        }else {
            handler.sendEmptyMessageDelayed(START_ACTIVITY_LOGIN,3);
        }

         /* startActivity(new Intent(FullscreenActivity.this, LoginActivity.class));
           finish();*/

    }


    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case START_ACTIVITY_LOGIN:
                startActivity(new Intent(FullscreenActivity.this,LoginActivity.class));
                    finish();
                    break;
                case START_ACTIVITY_MAIN:
                    if (type.equals("admin")){
                        Intent intent = new Intent(FullscreenActivity.this, AdminActivity.class);
                        startActivity(intent);
                        finish();

                    }else {

                        Intent intent = new Intent(FullscreenActivity.this, MainActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString(LOGIN_TYPE, type);
                        intent.putExtras(bundle);
                        startActivity(intent);

                        finish();

                    }
                    break;
            }
        }
    };
}
