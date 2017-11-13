package com.example.administrator.webexam.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.webexam.Adapter.ItemAddQuestionAdapter;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddQuestionPagerActivity extends AppCompatActivity {

    @Bind(R.id.vp)
    ViewPager vp;

    private static Boolean isEdit = true;
    public static String eid;

    private static final String EXAMIN_ID = "examinid";
    @Bind(R.id.back)
    ImageView back;

    private ItemAddQuestionAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question_pager);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        eid = bundle.getString("EID");


        if (eid.isEmpty()) {
            isEdit = true;
        } else {
            isEdit = false;
        }

        setViewPager();

    }

    private void setViewPager() {


        pagerAdapter = new ItemAddQuestionAdapter(getSupportFragmentManager(), this);
        vp.setAdapter(pagerAdapter);


        if (isEdit) {

            vp.setCurrentItem(0);
        } else {

            vp.setCurrentItem(ExaminationActivity.countall - 2);
        }


        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }

        });


        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.leyikao.jumptonext");
        filter.addAction("com.leyikao.jumptopage");
        lbm.registerReceiver(mMessageReceiver, filter);

    }

    private void setToolBar() {

    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.leyikao.jumptonext")) {
                jumpToNext();
            } else if (intent.getAction().equals("com.leyikao.jumptopage")) {
                int index = intent.getIntExtra("index", 0);
                jumpToPage(index);
            }
        }
    };

    public void jumpToNext() {
        int position = vp.getCurrentItem();
        vp.setCurrentItem(position + 1);

    }

    public void jumpToPage(int index) {
        vp.setCurrentItem(index);
    }

    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:

                finish();
                break;

        }
    }
}
