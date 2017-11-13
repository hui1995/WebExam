package com.example.administrator.webexam.View.StudentView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.webexam.Activity.ExaminationActivity;
import com.example.administrator.webexam.Activity.QuestionWrongActivity;
import com.example.administrator.webexam.Activity.ShowAllPageListActivity;
import com.example.administrator.webexam.Contract.StudentContract.StudentMainContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexStudentView extends Fragment implements StudentMainContract.IndexView {
    @Bind(R.id.analogexamin)
    Button analogexamin;
    @Bind(R.id.question_bank)
    Button questionBank;
    @Bind(R.id.daily_practice)
    Button dailyPractice;
    @Bind(R.id.wrong_question)
    Button wrongQuestion;
    private StudentMainContract.Presenter mPresenter;
    public String SHOW_PAGER_ALL ="showpager";
    private static final String Examin_ID ="examinid";
    private static final String EXAMIN_NAME="examinname";
    private static final String TYPE_EXAMIN="examintype";



    public static IndexStudentView newInstance() {


        return new IndexStudentView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

   View view = inflater.inflate(R.layout.fragment_index, container, false);


    ButterKnife.bind(this, view);








        return view;
    }

    @Override
    public void setPresenter(StudentMainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.startIndex();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.analogexamin, R.id.question_bank, R.id.daily_practice, R.id.wrong_question})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.analogexamin:
                goanalogexamin();
                break;
            case R.id.question_bank:
                goquestionbank();
                break;
            case R.id.daily_practice:
                godailypractive();
                break;
            case R.id.wrong_question:
                mPresenter.getStudentId();
                break;
        }
    }


    private void goquestionbank(){

        Intent intent = new Intent(getActivity(), ShowAllPageListActivity.class);
        Bundle bundle= new Bundle();
        bundle.putInt(SHOW_PAGER_ALL,2);
        intent.putExtras(bundle);
        startActivity(intent);
      //  getActivity().finish();

    }

    private void goanalogexamin(){
        mPresenter.getExaminIDANdName();

    }

    private void godailypractive(){
        Intent intent = new Intent(getActivity(), ExaminationActivity.class);
        Bundle bundle= new Bundle();
        bundle.putInt(TYPE_EXAMIN,4);
        bundle.putString(Examin_ID,"no");
        bundle.putString(EXAMIN_NAME,"no");
        intent.putExtras(bundle);
        startActivity(intent);
      //  getActivity().finish();


    }
    /*private void gowrongquestion(){





    }
*/
    @Override
    public void GetExamin(String eid, String ename) {
        Intent intent = new Intent(getActivity(), ExaminationActivity.class);
        Bundle bundle= new Bundle();
        bundle.putInt(TYPE_EXAMIN,2);
        bundle.putString(Examin_ID,eid);
        bundle.putString(EXAMIN_NAME,ename);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().finish();

    }

    @Override
    public void goQuestionWrong(String sid) {

        Intent intent = new Intent(getActivity(),QuestionWrongActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("sid",sid);
        bundle.putBoolean("isClass",false);
        intent.putExtras(bundle);

        startActivity(intent);

    }
}
