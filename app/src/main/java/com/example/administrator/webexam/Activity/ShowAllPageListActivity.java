package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.Presenter.ShowAllPageListPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.ActivityUtils;
import com.example.administrator.webexam.View.ShowAllPageListView;

public class ShowAllPageListActivity extends AppCompatActivity {
    public String SHOW_PAGER_ALL ="showpager";
    private String OBJECTID ="ObjectId";
    private String ObjectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_page_list);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int i = bundle.getInt(SHOW_PAGER_ALL,0);
        SharedPreferences userInfo = this.getSharedPreferences("user_login", 0);
        ObjectId= userInfo.getString(OBJECTID,"");
        ShowAllPageListView  showAllPageListView = (ShowAllPageListView) getSupportFragmentManager().findFragmentById(R.id.content_Frame);


        if (showAllPageListView==null){

            showAllPageListView =new ShowAllPageListView().newsInstance(i);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),showAllPageListView,R.id.content_Frame);



        }
        ExaminStatus examinStatus = new ExaminStatus();

        new ShowAllPageListPresenter(showAllPageListView, examinStatus,i,ObjectId);





    }
}
