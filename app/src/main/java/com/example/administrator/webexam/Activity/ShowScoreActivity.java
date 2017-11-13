package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowScoreActivity extends AppCompatActivity {

    @Bind(R.id.yourscore)
    TextView yourscore;
    @Bind(R.id.back)
    TextView back;
    private static final String  EXAMIN_TYPE_GET="examintypeget";

    private static final String SCORD="scord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();
        int type = bundle.getInt(EXAMIN_TYPE_GET);
        int scord=bundle.getInt(SCORD);

        if (type==2){
            yourscore.setText("本次模拟考试你的成绩是"+scord+"分");

        }else{
            yourscore.setText("本次考试你的成绩是"+scord+"分");
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {

finish();

    }
}
