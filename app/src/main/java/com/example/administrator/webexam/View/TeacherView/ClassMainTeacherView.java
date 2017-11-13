package com.example.administrator.webexam.View.TeacherView;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.QuestionAnalysisActivity;
import com.example.administrator.webexam.Activity.ShowStudentScoreActivity;
import com.example.administrator.webexam.Activity.StudentMangActivity;
import com.example.administrator.webexam.Contract.TeacherContract.ClassMainTeacherContract;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassMainTeacherView extends Fragment implements ClassMainTeacherContract.View {


    @Bind(R.id.class_id)
    TextView classId;
    @Bind(R.id.classname)
    TextView classname;
    @Bind(R.id.profession)
    TextView profession;
    @Bind(R.id.gradeclass)
    TextView gradeclass;
    @Bind(R.id.auth_true)
    TextView authTrue;
    @Bind(R.id.auth_false)
    TextView authFalse;
    @Bind(R.id.now_examin)
    TextView nowExamin;
    @Bind(R.id.student_mang)
    Button studentMang;
    @Bind(R.id.test_ansylet)
    Button testAnsylet;

    @Bind(R.id.grade_rank)
    Button gradeRank;
    @Bind(R.id.test_edit)
    Button testEnd;
    @Bind(R.id.test_alrealy)
    TextView testAlrealy;

    @Bind(R.id.score_hight)
    TextView scoreHight;
    @Bind(R.id.score_low)
    TextView scoreLow;
    @Bind(R.id.score_avae)
    TextView scoreAvae;
    private ClassMainTeacherContract.Presenter mPresenter;
    private String classid_s;
    private String eid;
    private int countpeop;


    public ClassMainTeacherView newsInstance(String s) {
        // Required empty public constructor
        ClassMainTeacherView fragment = new ClassMainTeacherView();
        Bundle args = new Bundle();
        args.putString(ClassTeacherView.CLASS_ID, s);

        fragment.setArguments(args);


        return fragment;


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            classid_s = getArguments().getString(ClassTeacherView.CLASS_ID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class_main_teacher_view, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void setPresenter(ClassMainTeacherContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);


    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setAllStudent(int i) {
        authTrue.setText(""+ i);

    }

    @Override
    public void setTrueStudent(int i) {
        authFalse.setText(""+i);

    }

    @Override
    public void setInfoForClass(Classmate classmate) {
        classId.setText(classmate.getObjectId());
        classname.setText(classmate.getCname());
        gradeclass.setText(classmate.getCgrade());
        profession.setText(classmate.getPname());
         nowExamin.setText(classmate.getEid().getExaminname());
    }

    @Override
    public void setNowExamin(String s, String eid) {
        nowExamin.setText(s);
        this.eid = eid;
    }

    @Override
    public void setInfoExamin(int count, int high, int low, int ave) {
        scoreLow.setText(low+"分");
        scoreHight.setText(high+"分");
        scoreAvae.setText(ave+"分");
        testAlrealy.setText(count+"人");
        countpeop=count;
    }

    @Override
    public void goQustionAnanlsis(String eid,String cid) {
        Intent i = new Intent(getActivity(), QuestionAnalysisActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString("eid",eid);
        bundle.putString("cid",cid);
        i.putExtras(bundle);
        startActivity(i);
    }

    @Override
    public void goGrankScore(String eid, String cid) {
        Intent i = new Intent(getActivity(), ShowStudentScoreActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("cid",cid);
        bundle.putString("eid",eid);
        i.putExtras(bundle);
        startActivity(i);
    }

    @OnClick({R.id.student_mang, R.id.test_ansylet, R.id.grade_rank})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.student_mang:
                setStudentMang();
                break;
            case R.id.test_ansylet:
                mPresenter.getQustionInfoTo();
                break;

            case R.id.grade_rank:
                mPresenter.getGrankScoreTo();


                break;
        }
    }




    private void setStudentMang() {

        Intent intent = new Intent(getActivity(), StudentMangActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ClassTeacherView.CLASS_ID, classid_s);
        intent.putExtras(bundle);
        startActivity(intent);
      //  getActivity().finish();
    }

    @OnClick(R.id.test_edit)
    public void onViewClicked() {

        new AlertDialog.Builder(getActivity())
                .setTitle("结束考试")
                .setMessage("本操作将结束现在进行的考试")
                .setPositiveButton("结束考试", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        mPresenter.updateExaming(classid_s);
                        Toast.makeText(getActivity(),eid,Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("继续考试", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
}
