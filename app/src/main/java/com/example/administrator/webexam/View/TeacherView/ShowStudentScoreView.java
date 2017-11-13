package com.example.administrator.webexam.View.TeacherView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.webexam.Adapter.ScoreHistoryItemAdapter;

import com.example.administrator.webexam.Contract.TeacherContract.ShowStudentScoreContract;
import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowStudentScoreView extends Fragment implements ShowStudentScoreContract.View {


    @Bind(R.id.ls)
    ListView ls;
    private ShowStudentScoreContract.Presenter mPresenter;

    public ShowStudentScoreView newsInstance() {
        // Required empty public constructor

        return new ShowStudentScoreView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_student_score_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(ShowStudentScoreContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void initView(ArrayList<ReportCard> arrayList) {


        ScoreHistoryItemAdapter scoreHistoryItemAdapter = new ScoreHistoryItemAdapter(getActivity(),arrayList,true);
        ls.setAdapter(scoreHistoryItemAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
