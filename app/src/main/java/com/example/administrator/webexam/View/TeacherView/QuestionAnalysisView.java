package com.example.administrator.webexam.View.TeacherView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.webexam.Adapter.QuestionAnsylterItemAdapter;
import com.example.administrator.webexam.Adapter.ScoreHistoryItemAdapter;

import com.example.administrator.webexam.Contract.TeacherContract.QuestionAnalysisContract;
import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;
import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionAnalysisView extends Fragment implements QuestionAnalysisContract.View {

    @Bind(R.id.ls)
    ListView ls;
    private QuestionAnalysisContract.Presenter mPresenter;


    public QuestionAnalysisView newsInstance() {

        return new QuestionAnalysisView();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_analysis_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(QuestionAnalysisContract.Presenter presenter) {
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
    public void initView(ArrayList<AlreadyDoneQ> list,int count) {
        QuestionAnsylterItemAdapter ansylterItemAdapter = new QuestionAnsylterItemAdapter(getActivity(),list,count);
        ls.setAdapter(ansylterItemAdapter);
    }
}
