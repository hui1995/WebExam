package com.example.administrator.webexam.View.StudentView;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.webexam.Adapter.ScoreHistoryItemAdapter;

import com.example.administrator.webexam.Contract.StudentContract.HistoryScoreContract;
import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryScoreView extends Fragment implements HistoryScoreContract.View {

    @Bind(R.id.ls)
    ListView ls;
    private HistoryScoreContract.Presenter mPresenter;
    private static Boolean isClass;


    public HistoryScoreView newsInstance(boolean isClass) {

        HistoryScoreView historyScoreView = new HistoryScoreView();
        Bundle args = new Bundle();
        args.putBoolean("isClass", isClass);
        historyScoreView.setArguments(args);
        return historyScoreView;
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isClass = getArguments().getBoolean("isClass");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history_score_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(HistoryScoreContract.Presenter presenter) {

        mPresenter = checkNotNull(presenter);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void initView(ArrayList<ReportCard> reportCards) {

        ScoreHistoryItemAdapter adapter = new ScoreHistoryItemAdapter(getActivity(),reportCards,false);

        ls.setAdapter(adapter);

      //  Log.i("JJJ",iNfoBeen.get(0).getS1());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
