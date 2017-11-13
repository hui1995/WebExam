package com.example.administrator.webexam.View.TeacherView;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Adapter.ShowPagerItemAdapter;
import com.example.administrator.webexam.Contract.TeacherContract.ExaminAuthContract;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExaminAuthView extends Fragment implements ExaminAuthContract.View {
    private String eid;
    @Bind(R.id.ls)
    ListView ls;
    private ExaminAuthContract.Presenter mPresenter;


    public ExaminAuthView newsInstance() {


        return new ExaminAuthView();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_examin_auth_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(ExaminAuthContract.Presenter presenter) {

        mPresenter = checkNotNull(presenter);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void init(final ArrayList<ExaminQuestion> arrayList) {
        final ShowPagerItemAdapter showPagerItemAdapter = new ShowPagerItemAdapter(getActivity(), arrayList, 3);
        ls.setAdapter(showPagerItemAdapter);
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                TextView textView = view.findViewById(R.id.pager_id);
eid=arrayList.get(i).getObjectId();

                mPresenter.getMyClassinfo(textView.getText().toString());

            }
        });
    }
    @Override
public void ShowClassinfo(final String[] strings, final boolean[] booleen){


    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setIcon(R.drawable.ic_dashboard_black_24dp)
            .setTitle("选择考试班级")
            .setMultiChoiceItems(strings, booleen, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                    if (b){
                        booleen[i] =b;
                    }
                }
            })
    .setPositiveButton("考试", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            String text = "";

            for(int i1 = 0; i1 < strings.length; i1++) {
                text += booleen[i1]? i1 + "," : "";
            }

            mPresenter.setExaminForClass(text,eid);

           dialogInterface.dismiss();
        }
    })
    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();

        }
    });
    builder.create().show();




}


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



}
