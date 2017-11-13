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
import com.example.administrator.webexam.Presenter.TeacherPresenter.AddQuestionPagerPresenter;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddQuestionSelectView extends Fragment implements AddQuestionPagerContract.View {
    LocalBroadcastManager mLocalBroadcastManager;
    @Bind(R.id.tv_getQuestion)
    TextView tvGetQuestion;
    @Bind(R.id.answser_true)
    RadioButton answserTrue;
    @Bind(R.id.answser_false)
    RadioButton answserFalse;
    @Bind(R.id.answser)
    RadioGroup answser;
    @Bind(R.id.ansyler)
    TextView ansyler;
    @Bind(R.id.next_question)
    Button nextQuestion;
    @Bind(R.id.order)
    TextView order;
    private TextView textView;
    private String answerinfo;
    private static final String ARG_PARAM1 = "param1";
    private int mParam1;
    @NonNull
    private static final int REQUEST_MYINFO = 3;
    private AddQuestionPagerContract.Presenter mPresenter;

    public AddQuestionSelectView newsInstance(int index) {
AddQuestionSelectView addQuestionSelectView = new AddQuestionSelectView();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1,index);
        addQuestionSelectView.setArguments(args);


        return addQuestionSelectView;
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
          mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_add_question_select_view, container, false);
        ButterKnife.bind(this, view);
        order.setText(mParam1+".(判断题)");
        mPresenter = new AddQuestionPagerPresenter(this, getActivity());
        answser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
selectRadionBtn(i);
            }
        });
        return view;
    }

    private void selectRadionBtn(int i) {
        if (i == answserFalse.getId()) {
            answerinfo = answserFalse.getText().toString();
        } else {
            answerinfo = answserTrue.getText().toString();


        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_getQuestion, R.id.ansyler, R.id.next_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_getQuestion:
                editText(tvGetQuestion);
                break;

            case R.id.ansyler:
                editText(ansyler);
                break;
            case R.id.next_question:

                saveQuestion();
                break;
        }
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

    private void saveQuestion() {
        mPresenter.save(tvGetQuestion.getText().toString(), answerinfo, ansyler.getText().toString());


    }

    @Override
    public void setPresenter(AddQuestionPagerContract.Presenter presenter) {

    }

    @Override
    public void Toast() {
        Toast.makeText(getActivity(), "请填写问题", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gonext() {
        Intent intent = new Intent("com.leyikao.jumptonext");
        mLocalBroadcastManager.sendBroadcast(intent);
    }
}
