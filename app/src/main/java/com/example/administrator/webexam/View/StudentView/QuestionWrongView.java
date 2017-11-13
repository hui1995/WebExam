package com.example.administrator.webexam.View.StudentView;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.QuestionWrongActivity;
import com.example.administrator.webexam.Activity.QuestionWrongInfoActivity;
import com.example.administrator.webexam.Adapter.QuestionWrongItemAdapter;
import com.example.administrator.webexam.Adapter.ScoreHistoryItemAdapter;

import com.example.administrator.webexam.Contract.StudentContract.QuestionWrongContract;
import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;
import com.example.administrator.webexam.R;

import java.util.ArrayList;
import java.util.concurrent.RunnableFuture;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionWrongView extends Fragment implements QuestionWrongContract.View {


    @Bind(R.id.question_wrong_count)
    TextView questionWrongCount;
    @Bind(R.id.ls)
    ListView ls;
    @NonNull
    private QuestionWrongContract.Presenter mPresenter;
    private Handler handler=null;

    public QuestionWrongView newsInstance() {
        return new QuestionWrongView();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question_wrong_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(QuestionWrongContract.Presenter presenter) {
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
    public void initView(final ArrayList<AlreadyDoneQ> arrayList, int count) {







                        QuestionWrongItemAdapter questionWrongItemAdapter = new QuestionWrongItemAdapter(getActivity(),arrayList);


                        ls.setAdapter(questionWrongItemAdapter);




        String str="<small><small>总计  </small></small>  "+count+" <small><small> 题</small><small>";
        questionWrongCount.setText(Html.fromHtml(str));


        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (arrayList.get(i).isRadio()){
                    mPresenter.getQuestionInfo(arrayList.get(i).getRid().getObjectId(),true);
                }else{
                    mPresenter.getQuestionInfo(arrayList.get(i).getJid().getObjectId(),false);
                }

            }
        });

    }

    @Override
    public void questionINfo(String object, String youranswer,boolean isRadio) {
        Intent intent = new Intent(getActivity(), QuestionWrongInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("rjid",object);
        bundle.putString("answer",youranswer);
        bundle.putBoolean("isRadio",isRadio);
        bundle.putInt("type",1);
        intent.putExtras(bundle);
        startActivity(intent);
      //  getActivity().finish();
    }
}
