package com.example.administrator.webexam.View.StudentView;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.ExaminationActivity;
import com.example.administrator.webexam.Activity.HistoryScoreActivity;
import com.example.administrator.webexam.Activity.QuestionWrongActivity;
import com.example.administrator.webexam.Contract.StudentContract.MyClassContract;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyClassView extends Fragment implements MyClassContract.View {

    @Bind(R.id.class_id)
    TextView classId;
    @Bind(R.id.classname)
    TextView classname;
    @Bind(R.id.profession)
    TextView profession;
    @Bind(R.id.gradeclass)
    TextView gradeclass;
    @Bind(R.id.my_teacher)
    TextView myTeacher;
    @Bind(R.id.now_examin)
    TextView nowExamin;
    @Bind(R.id.examin_history)
    Button examinHistory;
    @Bind(R.id.examin_fail)
    Button examinFail;
    @Bind(R.id.examin_now)
    Button examinNow;
    @Bind(R.id.grade_me)
    TextView gradeMe;
    @Bind(R.id.radio_question)
    TextView radioQuestion;
    @Bind(R.id.examin_answer)
    Button examinAnswer;
    @NonNull
    private MyClassContract.Presenter mPresenter;
    private static final String Examin_ID = "examinid";
    private static final String EXAMIN_NAME = "examinname";
    private static final String TYPE_EXAMIN = "examintype";
    private int mParam1=1;

    private static final String ARG_PARAM1 = "param1";


    public MyClassView newsInsantace() {


        return new MyClassView();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_class_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(MyClassContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }


    @Override
    public void setClassInfo(Student student) {
        classId.setText(student.getCid().getObjectId());
        classname.setText(student.getCid().getCname());
        gradeclass.setText(student.getCid().getCgrade());
        profession.setText(student.getCid().getPname());
        myTeacher.setText(student.getCid().getTid().getTname());
        if (student.getCid().getEid() == null) {
            nowExamin.setText("暂无考试");
        } else {

            nowExamin.setText(student.getCid().getEid().getExaminname());

        }
        mPresenter.saveCid(getActivity(),student.getCid().getObjectId());

    }

    @Override
    public void ToastNoExamin() {
        Toast.makeText(getActivity(), "暂时无任何考试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void GOExamining(String eid, String ename, boolean isExamining) {
//此处将！去掉
        if (mPresenter.checkHadExaminNow() && isExamining) {
            Intent intent = new Intent(getActivity(), ExaminationActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(Examin_ID, eid);
            bundle.putString(EXAMIN_NAME, ename);
            bundle.putInt(TYPE_EXAMIN, 3);
            intent.putExtras(bundle);
            startActivity(intent);
          //  getActivity().finish();
        } else {
            Toast.makeText(getActivity(), "您已考试完毕", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setScore(int i, int i2) {
        gradeMe.setText(i + "分");
        radioQuestion.setText(100-i2 + "%");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.examin_history, R.id.examin_fail, R.id.examin_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.examin_history:
                Intent intent = new Intent(getActivity(), HistoryScoreActivity.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("isClass",true);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.examin_fail:
                mPresenter.getQuestionInfo();
                break;
            case R.id.examin_now:

                mPresenter.getExaminid();
                break;
        }
    }


    @Override
    public void showQuestionWrong(String sid) {
        Intent intent = new Intent(getActivity(), QuestionWrongActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("sid", sid);
        bundle.putBoolean("isClass", true);
        bundle.putString("examinid",Examin_ID);
        intent.putExtras(bundle);

        startActivity(intent);
     //   getActivity().finish();
    }

    @Override
    public void ToastNoAnser() {
        Toast.makeText(getActivity(),"目前尚未公布答案或没有考试",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void GOShowAnswer(String eid,String sid,String ename) {

        Intent intent = new Intent(getActivity(),ExaminationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PARAM1,mParam1);
        bundle.putString(Examin_ID,eid);
        bundle.putString(EXAMIN_NAME,ename);
        bundle.putInt(TYPE_EXAMIN,mParam1);

        intent.putExtras(bundle);
        startActivity(intent);
       // getActivity().finish();

    }

    @OnClick(R.id.examin_answer)
    public void onViewClicked() {

        mPresenter.getExaminAnswer();

    }
}
