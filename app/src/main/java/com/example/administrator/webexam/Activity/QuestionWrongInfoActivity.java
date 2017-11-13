package com.example.administrator.webexam.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.webexam.Module.Data.JudgementQuestions;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.Module.IAlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.IExaminStatus;
import com.example.administrator.webexam.Module.impl.AlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionWrongInfoActivity extends AppCompatActivity {

    @Bind(R.id.question_show)
    TextView questionShow;
    @Bind(R.id.option_1)
    RadioButton option1;
    @Bind(R.id.option_2)
    RadioButton option2;
    @Bind(R.id.option_3)
    RadioButton option3;
    @Bind(R.id.option_4)
    RadioButton option4;
    @Bind(R.id.answser)
    RadioGroup answser;
    @Bind(R.id.answser_title)
    TextView answserTitle;
    @Bind(R.id.anysler_info)
    TextView anyslerInfo;
    @Bind(R.id.anysler_title)
    TextView anyslerTitle;
    @Bind(R.id.ansyler)
    TextView ansyler;
    @Bind(R.id.back)
    Button back;
    @Bind(R.id.your_answer)
    TextView yourAnswer;
    boolean isRadio = false;
    String answer = null;
    String rjid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_wrong_info);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int type=bundle.getInt("type");
        IExaminStatus iExaminStatus = new ExaminStatus();

          rjid = bundle.getString("rjid");
        answer = bundle.getString("answer");
           isRadio = bundle.getBoolean("isRadio");

            if (isRadio) {

                iExaminStatus.getQuestionRadio(rjid, new IExaminStatus.GetQuestionRadio() {
                    @Override
                    public void get(RadioQuestions radioQuestions) {
                        initViewRadio(radioQuestions,answer);

                    }
                });
            } else {
                iExaminStatus.GetQuestionJudge(rjid, new IExaminStatus.GetQuestionJudge() {
                    @Override
                    public void get(JudgementQuestions judgementQuestions) {
                        initViewJudge(judgementQuestions,answer);

                    }
                });
            }








        }





    private void initViewRadio(RadioQuestions radioQuestions, String answer) {

        questionShow.setText(radioQuestions.getQuestion());
        option1.setText(radioQuestions.getOption1());
        option2.setText(radioQuestions.getOption2());
        option3.setText(radioQuestions.getOption3());
        option4.setText(radioQuestions.getOption4());
        anyslerInfo.setText(radioQuestions.getAnswer());
        ansyler.setText(radioQuestions.getAnalytic());
        yourAnswer.setText(answer);


    }

    private void initViewJudge(JudgementQuestions judgementQuestions, String anser) {
yourAnswer.setText(anser);
        questionShow.setText(judgementQuestions.getQuestion());


     option1.setText("正确");
        option2.setText("错误");
        option3.setVisibility(View.GONE);
        option4.setVisibility(View.GONE);

        if (judgementQuestions.getAnswer()){
            anyslerInfo.setText("正确");
        }else {
            anyslerInfo.setText("错误");
        }

        ansyler.setText(judgementQuestions.getAnalytic());


    }

    @OnClick(R.id.back)
    public void onViewClicked() {
    }
}
