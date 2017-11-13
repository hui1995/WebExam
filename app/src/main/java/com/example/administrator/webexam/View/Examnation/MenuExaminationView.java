package com.example.administrator.webexam.View.Examnation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.webexam.Activity.AddQuestionPagerActivity;
import com.example.administrator.webexam.Activity.ExaminationActivity;
import com.example.administrator.webexam.Activity.ShowAllPageListActivity;
import com.example.administrator.webexam.Activity.ShowScoreActivity;
import com.example.administrator.webexam.Contract.ExaminationContract;
import com.example.administrator.webexam.Presenter.ExaminationPresenter;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.Utils.NoScrollGridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuExaminationView extends Fragment implements ExaminationContract.View {

    LocalBroadcastManager mLocalBroadcastManager;
    int count = ExaminationActivity.countall - 3;
    int[] mIds = new int[52];
    public String SHOW_PAGER_ALL = "showpager";
    @Bind(R.id.gridview)
    NoScrollGridView gridview;
    @Bind(R.id.tv_submit_result)
    TextView tvSubmitResult;
    private static final String ANSWER_TRUE = "answer";
    private ArrayList<String> arrayList = new ArrayList<String>();
    private ExaminationContract.Presenter mPresenter;
    private Boolean isTimeIn;
    private static final String IS_TIME_IN ="istimein";
    private static final String  EXAMIN_TYPE_GET="examintypeget";
    private static final String TEACHER_EDIT_FINISH="finish";
    private static final String EXAMIN_ID="examinid";
    private static final String SCORD="scord";
    private int type;
    private boolean isfinish;
    private String examinid;



    public static MenuExaminationView newInstance(ArrayList<String> arrayList,boolean isTimeIn,int type,boolean isfinish,String exmainid) {
        MenuExaminationView fragment = new MenuExaminationView();

        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ANSWER_TRUE, arrayList);
        bundle.putBoolean(IS_TIME_IN,isTimeIn);
        bundle.putBoolean(TEACHER_EDIT_FINISH,isfinish);
        bundle.putInt(EXAMIN_TYPE_GET,type);
        bundle.putString(EXAMIN_ID,exmainid);
        fragment.setArguments(bundle);


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            arrayList = getArguments().getStringArrayList(ANSWER_TRUE);
            isTimeIn= getArguments().getBoolean(IS_TIME_IN);
            type=getArguments().getInt(EXAMIN_TYPE_GET);
            isfinish=getArguments().getBoolean(TEACHER_EDIT_FINISH);
            examinid=getArguments().getString(EXAMIN_ID);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());

        View view = inflater.inflate(R.layout.fragment_menu_examination_view, container, false);
        ButterKnife.bind(this, view);
        initData();

        mPresenter = new ExaminationPresenter();
        MyAdapter adapter = new MyAdapter(getActivity());
        gridview.setAdapter(adapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent("com.leyikao.jumptopageexamin");
                intent.putExtra("index", i + 1);
                mLocalBroadcastManager.sendBroadcast(intent);

            }
        });
        if (!isTimeIn){
            submitExaminTimeOut();
        }
if (isfinish){
    if (type==1||type==0){
        tvSubmitResult.setText("返回试卷列表");
    }else{
        tvSubmitResult.setText("提交试卷");
    }


}else{
    tvSubmitResult.setText("继续编辑试卷");
}


        return view;


    }

    private void initData() {

        for (int i = 0; i <= count; i++) {
            mIds[i] = i + 1;
        }
    }


    @Override
    public void setPresenter(ExaminationContract.Presenter presenter) {

    }

    @Override
    public void gonext() {
        int count = mPresenter.countscord();
        Intent intent = new Intent(getActivity(), ShowScoreActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(EXAMIN_TYPE_GET,type);
        bundle.putInt(SCORD,count);

        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().finish();





    }
    private void submitExaminTimoeIn(){
        new AlertDialog.Builder(getActivity())
                .setTitle("完成考试")
                .setMessage("是否提交试卷")
                .setPositiveButton("提交试卷", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.checkAnswer(arrayList,type,getActivity());
                          gonext();

                    }
                })
                .setNegativeButton("继续考试", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }
private void submitExaminTimeOut(){

    new AlertDialog.Builder(getActivity())
            .setTitle("完成考试")
            .setMessage("是否提交试卷")
            .setPositiveButton("提交试卷", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mPresenter.checkAnswer(arrayList,type,getActivity());
                    gonext();

                }
            })

            .show();
}


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tv_submit_result)
    public void onViewClicked() {

        if (isfinish){

            if (type==0){
                Intent intent = new Intent(getActivity(), ShowAllPageListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(SHOW_PAGER_ALL, 0);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().finish();
            }else if (type==1){

                Intent intent = new Intent(getActivity(), ShowAllPageListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(SHOW_PAGER_ALL, 1);
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().finish();

            }else {

                submitExaminTimoeIn();

            }


        }else{
            Intent intent = new Intent(getActivity(), AddQuestionPagerActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("EID",examinid);

            intent.putExtras(bundle);
            startActivity(intent);
        }


    }

    private class MyAdapter extends BaseAdapter {

        private Context context;

        public MyAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Override
        public Object getItem(int i) {
            return mIds[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView tv = new TextView(context);
            tv.setGravity(Gravity.CENTER);
            tv.setLayoutParams(new GridView.LayoutParams(70, 70));
            tv.setPadding(8, 8, 8, 8);

            tv.setText(mIds[i] + "");

            if (arrayList.get(i) == "null") {

                tv.setBackgroundResource(R.drawable.option_btn_single_normal);

            } else {

                if (type==1||type==0){
                    tv.setBackgroundResource(R.drawable.option_btn_single_normal);
                }else {
                    tv.setBackgroundResource(R.mipmap.option_btn_single_checked);
                }

            }
            return tv;
        }

    }
}
