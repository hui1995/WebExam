package com.example.administrator.webexam.View.TeacherView;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.EditActivity;
import com.example.administrator.webexam.Contract.TeacherContract.AddQuestionPagerContract;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.Presenter.TeacherPresenter.AddQuestionPagerPresenter;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddQuestionPagerView extends Fragment implements AddQuestionPagerContract.View {
    @Bind(R.id.tv_getQuestion)
    TextView tvGetQuestion;
    @Bind(R.id.tv_option1)
    TextView tvOption1;
    @Bind(R.id.tv_option2)
    TextView tvOption2;
    @Bind(R.id.tv_option3)
    TextView tvOption3;
    @Bind(R.id.tv_option4)
    TextView tvOption4;
    @Bind(R.id.answser_A)
    RadioButton answserA;
    @Bind(R.id.answser_B)
    RadioButton answserB;
    @Bind(R.id.answser_C)
    RadioButton answserC;
    @Bind(R.id.answser_D)
    RadioButton answserD;
    @Bind(R.id.answser)
    RadioGroup answser;
    @Bind(R.id.ansyler)
    TextView ansyler;
    @Bind(R.id.next_question)
    Button nextQuestion;
    LocalBroadcastManager mLocalBroadcastManager;
    @Bind(R.id.order)
    TextView order;
    private TextView textView;
    private String answerinfo;
    private int mParam1;
    private static final String ARG_PARAM1 = "param1";

    @NonNull
    private static final int REQUEST_MYINFO = 3;
    private ArrayList<RadioQuestions> radioQuestionses = new ArrayList<RadioQuestions>();

    // private AddQuestionPagerContract.Presenter mPresenter;
    private AddQuestionPagerContract.Presenter mPresenter;

    public static AddQuestionPagerView newsInstance(int index) {
AddQuestionPagerView addQuestionPagerView = new AddQuestionPagerView();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1,index);
        addQuestionPagerView.setArguments(args);

        return addQuestionPagerView;
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() !=null){
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mPresenter = new AddQuestionPagerPresenter(this, getActivity());
        View view = inflater.inflate(R.layout.fragment_add_question_pager_view, container, false);
        ButterKnife.bind(this, view);
        order.setText(mParam1+".(单选题)");

        answser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                selectRadionBtn(i);
            }
        });
        return view;
    }

    private void selectRadionBtn(int i) {
        if (i == answserB.getId()) {
            answerinfo = answserB.getText().toString();
        } else if (i == answserC.getId()) {
            answerinfo = answserC.getText().toString();
        } else if (i == answserD.getId()) {
            answerinfo = answserD.getText().toString();
        } else {
            answerinfo = answserA.getText().toString();
        }

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

    @OnClick({R.id.tv_getQuestion, R.id.tv_option1, R.id.tv_option2, R.id.tv_option3, R.id.tv_option4, R.id.ansyler, R.id.next_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getQuestion:
                editText(tvGetQuestion);
                break;
            case R.id.tv_option1:
                editText(tvOption1);
                break;
            case R.id.tv_option2:
                editText(tvOption2);
                break;
            case R.id.tv_option3:
                editText(tvOption3);
                break;
            case R.id.tv_option4:
                editText(tvOption4);
                break;

            case R.id.ansyler:
                editText(ansyler);
                break;
            case R.id.next_question:
                saveQuestion();


                break;
        }
    }


    void saveQuestion() {

        RadioQuestions radioQuestions = new RadioQuestions();
        radioQuestions.setQuestion(tvGetQuestion.getText().toString());
        radioQuestions.setOption1(tvOption1.getText().toString());
        radioQuestions.setOption2(tvOption2.getText().toString());
        radioQuestions.setOption3(tvOption3.getText().toString());
        radioQuestions.setOption4(tvOption4.getText().toString());
        radioQuestions.setAnalytic(ansyler.getText().toString());

        mPresenter.save(tvGetQuestion.getText().toString(), tvOption1.getText().toString(), tvOption2.getText().toString(), tvOption3.getText().toString(), tvOption4.getText().toString(), answerinfo, ansyler.getText().toString());
        // AddQuestionPagerPresenter addQuestionPagerPresenter = new AddQuestionPagerPresenter();
        // addQuestionPagerPresenter.save(radioQuestions);
        // Toast.makeText(getActivity(),radioQuestions.getQuestion(),Toast.LENGTH_SHORT).show();

    }


    @Override
    public void setPresenter(AddQuestionPagerContract.Presenter presenter) {


    }

    @Override
    public void Toast() {
        Toast.makeText(getActivity(), "sssss", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gonext() {
        Intent intent = new Intent("com.leyikao.jumptonext");
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    private void editText(TextView tv) {
        textView = tv;
        Intent intent = new Intent(getActivity(), EditActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("editinfo", textView.getText().toString());
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_MYINFO);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 3:

                setQuestion(data);
                break;
        }
    }

    private void setQuestion(Intent data) {
        Bundle bundle2 = data.getExtras();
        String editinfo = bundle2.getString("editinfo");
        textView.setText(editinfo);

    }
}
