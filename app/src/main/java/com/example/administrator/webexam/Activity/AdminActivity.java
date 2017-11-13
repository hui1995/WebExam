package com.example.administrator.webexam.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.webexam.Adapter.AuthTeacherItemAdapter;

import com.example.administrator.webexam.Module.Data.Admin;
import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.Data.University;
import com.example.administrator.webexam.Module.ITeacherStatus;
import com.example.administrator.webexam.Module.IUniversityStatus;
import com.example.administrator.webexam.Module.impl.TeacherStatus;
import com.example.administrator.webexam.Module.impl.UniversityStatus;
import com.example.administrator.webexam.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdminActivity extends AppCompatActivity {

    @Bind(R.id.ls)
    ListView ls;
    ArrayList<Teacher> arrayList = new ArrayList<Teacher>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);


        final ITeacherStatus iTeacherStatus = new TeacherStatus();
        iTeacherStatus.GetTeacherNo(false, new ITeacherStatus.GetTidCallBack() {
            @Override
            public void getTid(List<Teacher> list) {

        arrayList= (ArrayList<Teacher>) list;
                AuthTeacherItemAdapter authTeacherItemAdapter = new AuthTeacherItemAdapter(getApplicationContext(), (ArrayList<Teacher>) list);
                ls.setAdapter(authTeacherItemAdapter);

            }

        });

        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {

                Toast.makeText(getApplicationContext(),arrayList.get(i).getTname(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AdminActivity.this,TeacherInfoForAdminActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tid",arrayList.get(i).getObjectId());
                bundle.putString("ttid",arrayList.get(i).getTeacherid());
                bundle.putString("uname",arrayList.get(i).getUniversity().getUname());
                bundle.putString("tname",arrayList.get(i).getTname());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }
}
