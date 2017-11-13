package com.example.administrator.webexam.View.Examnation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
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

import com.example.administrator.webexam.Activity.ExaminationActivity;
import com.example.administrator.webexam.Contract.ExaminationContract;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RadioExaminationView extends Fragment implements ExaminationContract.View {

    LocalBroadcastManager mLocalBroadcastManager;
    @Bind(R.id.question_show)
    TextView questionShow;
    @Bind(R.id.option_A)
    RadioButton optionA;
    @Bind(R.id.option2_B)
    RadioButton option2B;
    @Bind(R.id.option_C)
    RadioButton optionC;
    @Bind(R.id.option_D)
    RadioButton optionD;
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
    private RadioQuestions radioQuestions;
    private String answertrue;

    private FragmentInteraction listener;
    private String EXAMIN_TYPE_GET="examintypeget";
    private int type;


    public RadioExaminationView newsInstance(int index,int type) {

        RadioExaminationView radioExaminationView = new RadioExaminationView();
        Bundle bundle = new Bundle();
        bundle.putInt(INDEX, index);
        bundle.putInt(EXAMIN_TYPE_GET,type);
        radioExaminationView.setArguments(bundle);

        return radioExaminationView;
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentInteraction){
            listener =(FragmentInteraction)context;
        }
        else{
            throw new IllegalArgumentException("activity must implements FragmentInteraction");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        listener =null;
    }

    public interface FragmentInteraction{
        void process(String str,int i);
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
        View view = inflater.inflate(R.layout.fragment_radio_examination_view, container, false);
        ButterKnife.bind(this, view);
        radioQuestions = ExaminationActivity.list1.get(index - 1);

        questionShow.setText("（" + index + "） 单选题:   " + radioQuestions.getQuestion());
        optionA.setText("A." + radioQuestions.getOption1());
        option2B.setText("B." + radioQuestions.getOption2());
        optionC.setText("C." + radioQuestions.getOption3());
        optionD.setText("D." + radioQuestions.getOption4());
        anyslerInfo.setText(radioQuestions.getAnswer());
        ansyler.setText(radioQuestions.getAnalytic());

        answser.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                getanswer(i);
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

    private void getanswer(int i){

        if (i==optionA.getId()){
            answertrue="A";
        }else if (i==option2B.getId()){
            answertrue="B";

        }else if (i==optionC.getId()){
            answertrue="C";

        }else if (i==optionD.getId()){
            answertrue="D";

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
}
