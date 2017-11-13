package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.webexam.Module.ITeacherStatus;
import com.example.administrator.webexam.Module.impl.TeacherStatus;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeacherInfoForAdminActivity extends AppCompatActivity {

    @Bind(R.id.uname)
    TextView uname;
    @Bind(R.id.tname)
    TextView tname;
    @Bind(R.id.tid)
    TextView tid;
    @Bind(R.id.agree)
    Button agree;
    @Bind(R.id.delete)
    Button delete;
    private ITeacherStatus teacherStatus;
    private String tid1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info_for_admin);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String uname1 = bundle.getString("uname");
        String tname1 = bundle.getString("tname");
  tid1 = bundle.getString("tid");
        String ttid1 = bundle.getString("ttid");
        uname.setText(uname1);
        tname.setText(tname1);
        tid.setText(ttid1);

      teacherStatus = new TeacherStatus();
    }

    @OnClick({R.id.agree, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.agree:
                teacherStatus.updateTeacher(tid1,true);
                finish();
                break;
            case R.id.delete:
                teacherStatus.deleteTeacher(tid1);
                finish();
                break;
        }
    }


}
