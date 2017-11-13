package com.example.administrator.webexam.View.Examnation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.ExaminationActivity;
import com.example.administrator.webexam.Contract.ExaminationContract;
import com.example.administrator.webexam.Module.Data.JudgementQuestions;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class JudgeExaminationView extends Fragment implements ExaminationContract.View {

    LocalBroadcastManager mLocalBroadcastManager;
    @Bind(R.id.question_show)
    TextView questionShow;
    @Bind(R.id.option_true)
    RadioButton optionTrue;
    @Bind(R.id.option2_false)
    RadioButton option2False;
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
    @Bind(R.id.next_question)
    Button nextQuestion;
    private String INDEX = "index";
    private int index;
    private JudgementQuestions judgementQuestions;
    private FragmentInteraction listener;
    private String answertrue=null;
    private String EXAMIN_TYPE_GET="examintypeget";
    private int type;




    public JudgeExaminationView newsInstance(int index,int type) {
        JudgeExaminationView judgeExaminationView = new JudgeExaminationView();
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        bundle.putInt(EXAMIN_TYPE_GET,type);
        judgeExaminationView.setArguments(bundle);

        return judgeExaminationView;
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof FragmentInteraction){
            listener = (FragmentInteraction)context;
        }else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener =null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(INDEX);
            type=getArguments().getInt(EXAMIN_TYPE_GET);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());

        View view = inflater.inflate(R.layout.fragment_judge_examination_view, container, false);

        ButterKnife.bind(this, view);
        if (type==4){
            int co =ExaminationActivity.list1.size()+1;
            Log.i("MMM","CC"+co);
            judgementQuestions=ExaminationActivity.list2.get(index-co);
        }else{
            judgementQuestions = ExaminationActivity.list2.get(index - 26);
        }

        questionShow.setText("（"+index+"） 判断题:  "+judgementQuestions.getQuestion());
        if (judgementQuestions.getAnswer()){
            anyslerInfo.setText("正确");
        }else{
            anyslerInfo.setText("错误");
        }
        ansyler.setText(judgementQuestions.getAnalytic());
        answser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
getAnswer(i);
            }
        });

        if (type==0||type==1){
            ansyler.setVisibility(View.VISIBLE);
            answserTitle.setVisibility(view.VISIBLE);
            anyslerTitle.setVisibility(View.VISIBLE);
            anyslerInfo.setVisibility(View.VISIBLE);

        }

        return view;
    }

    private void getAnswer(int i){

        if (i==optionTrue.getId()){
            answertrue= "true";
            Toast.makeText(getActivity(),"true",Toast.LENGTH_SHORT).show();
        }else{
            answertrue="false";
            Toast.makeText(getActivity(),"false",Toast.LENGTH_SHORT).show();
        }
        listener.process(answertrue,index-1);


    }
    @Override
    public void setPresenter(ExaminationContract.Presenter presenter) {

    }

    @Override
    public void gonext() {



        Intent intent = new Intent("com.leyikao.jumptonextexamin");
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.next_question)
    public void onViewClicked() {
        gonext();
    }




    public interface FragmentInteraction{

        void process(String b, int i);
    }
}
