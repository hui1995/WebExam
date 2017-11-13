package com.example.administrator.webexam.View.TeacherView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.webexam.Contract.TeacherContract.ExaminHistoryClassContract;
import com.example.administrator.webexam.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExaminHistoryClassView extends Fragment implements ExaminHistoryClassContract.View{

    private ExaminHistoryClassContract.Presenter mPresenter;


    public ExaminHistoryClassView newsInstance() {

        return new ExaminHistoryClassView();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_examin_history_class_view, container, false);
    }

    @Override
    public void setPresenter(ExaminHistoryClassContract.Presenter presenter) {
        mPresenter=checkNotNull(presenter);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }
}
