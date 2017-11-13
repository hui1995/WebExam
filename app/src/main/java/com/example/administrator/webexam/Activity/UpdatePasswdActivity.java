package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.webexam.Module.IStudentStatus;
import com.example.administrator.webexam.Module.ITeacherStatus;
import com.example.administrator.webexam.Module.impl.StudentStatus;
import com.example.administrator.webexam.Module.impl.TeacherStatus;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePasswdActivity extends AppCompatActivity {

    @Bind(R.id.edit_passwd)
    EditText editPasswd;
    @Bind(R.id.submit_passwd)
    Button submitPasswd;
    private int type;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_passwd);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        id = bundle.getString("id", "");
        type = bundle.getInt("type", 0);



    }

    @OnClick(R.id.submit_passwd)
    public void onViewClicked() {
        if (type == 0) {



            ITeacherStatus iTeacherStatus = new TeacherStatus();
            iTeacherStatus.updatePasswd(id,editPasswd.getText().toString());
        }else{
            IStudentStatus iStudentStatus = new StudentStatus();
            iStudentStatus.updatePasswd(id,editPasswd.getText().toString());
        }
    }
}
