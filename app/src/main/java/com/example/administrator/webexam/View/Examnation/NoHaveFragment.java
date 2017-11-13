package com.example.administrator.webexam.View.Examnation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.webexam.Contract.ExaminationContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoHaveFragment extends Fragment implements ExaminationContract.View {

    LocalBroadcastManager mLocalBroadcastManager;
    @Bind(R.id.gogogo)
    TextView gogogo;

    public NoHaveFragment newsInstance() {
        // Required empty public constructor

        return new NoHaveFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_no_have, container, false);
        ButterKnife.bind(this, view);
        return view;
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

    @OnClick(R.id.gogogo)
    public void onViewClicked() {
        gonext();
    }
}
