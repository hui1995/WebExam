package com.example.administrator.webexam.View;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.ExaminationActivity;
import com.example.administrator.webexam.Adapter.ShowPagerItemAdapter;
import com.example.administrator.webexam.Contract.ShowAllPageListContract;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowAllPageListView extends Fragment implements ShowAllPageListContract.View {


    private static ShowAllPageListContract.Presenter mPresenter;
    @Bind(R.id.showListpager)
    ListView showListpager;
    private int mParam1;
    private static final String ARG_PARAM1 = "param1";
    private static final String Examin_ID ="examinid";
    private static final String EXAMIN_NAME="examinname";
    private static final String TYPE_EXAMIN="examintype";

    public ShowAllPageListView newsInstance(int i) {
        // Required empty public constructor
        ShowAllPageListView showAllPageListView = new ShowAllPageListView();


        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, i);
        showAllPageListView.setArguments(args);
        return showAllPageListView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_all_page_list_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(ShowAllPageListContract.Presenter presenter) {


        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showList(final ArrayList<ExaminQuestion> arrayList) {

        ShowPagerItemAdapter showPagerItemAdapter = new ShowPagerItemAdapter(getActivity(),arrayList,mParam1);

        showListpager.setAdapter(showPagerItemAdapter);


        showListpager.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView =view.findViewById(R.id.examin_na);
                TextView textView1=view.findViewById(R.id.pager_id);

                   Intent intent = new Intent(getActivity(),ExaminationActivity.class);
                           Bundle bundle = new Bundle();
                bundle.putInt(ARG_PARAM1,mParam1);
                bundle.putString(Examin_ID,textView1.getText().toString());
                bundle.putString(EXAMIN_NAME,textView.getText().toString());
                bundle.putInt(TYPE_EXAMIN,mParam1);

                intent.putExtras(bundle);
                startActivity(intent);
              //  getActivity().finish();

                      //  Toast.makeText(getActivity(),textView.getText().toString()+textView1.getText().toString()+"SSSSSSSSSS",Toast.LENGTH_SHORT).show();

            }
        });

        showListpager.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!arrayList.get(i).isPublic()&&arrayList.get(i).isFinish()) {


                    if (arrayList.get(i).isExamining()) {
                        Toast.makeText(getActivity(), "正在考试，不可公开", Toast.LENGTH_SHORT).show();
                    } else {

                        showExamin(arrayList.get(i).getObjectId());

                    }
                }else{
                    Toast.makeText(getActivity(), "您的试卷未完成或者已公开", Toast.LENGTH_SHORT).show();

                }

                return false;
            }
        });
    }

    private void showExamin(final String eid){
        new AlertDialog.Builder(getActivity())
                .setTitle("提示")
                .setMessage("是否公开试卷，操作不可倪")
                .setPositiveButton("公开", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
             mPresenter.updateExamin(eid);

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();


    }
}
