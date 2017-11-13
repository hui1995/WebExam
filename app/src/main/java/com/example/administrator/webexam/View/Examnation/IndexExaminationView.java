package com.example.administrator.webexam.View.Examnation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.webexam.Contract.ExaminationContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexExaminationView extends Fragment implements ExaminationContract.View {

    @Bind(R.id.examin_name)
    TextView examinName;


    @Bind(R.id.now_go_examin)
    Button nowGoExamin;
    private String examinna;
    private String EXAMINNAME = "examinname";
    LocalBroadcastManager mLocalBroadcastManager;
    private FragmentInteraction listener;
    private String EXAMIN_TYPE_GET="examintypeget";
    private int type;


    public IndexExaminationView newsInstance(String exaimname,int examin_type_get) {
        IndexExaminationView indexExaminationView = new IndexExaminationView();
        Bundle args = new Bundle();
        args.putString(EXAMINNAME, exaimname);
        args.putInt(EXAMIN_TYPE_GET,examin_type_get);
        indexExaminationView.setArguments(args);

        return indexExaminationView;
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

            examinna = getArguments().getString(EXAMINNAME);
            type=getArguments().getInt(EXAMIN_TYPE_GET);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        View view = inflater.inflate(R.layout.fragment_index_examination_view, container, false);
        ButterKnife.bind(this, view);

        if (type==0||type==1){
            nowGoExamin.setText("点击查看试卷");
            examinName.setText(examinna);
        }else if (type==4){
            examinName.setText("每日一练");

        }else{
            examinName.setText(examinna);
        }

        return view;
    }

    @Override
    public void setPresenter(ExaminationContract.Presenter presenter) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.examin_name, R.id.now_go_examin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.examin_name:
                break;
            case R.id.now_go_examin:
                gonext();
                break;
        }
    }

    @Override
    public void gonext() {

        Intent intent = new Intent("com.leyikao.jumptonextexamin");
        mLocalBroadcastManager.sendBroadcast(intent);
        listener.nowsetTime();
    }

    public interface FragmentInteraction{

        void nowsetTime();
    }
}
