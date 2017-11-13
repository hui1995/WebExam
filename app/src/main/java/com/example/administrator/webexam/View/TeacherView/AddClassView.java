package com.example.administrator.webexam.View.TeacherView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.webexam.Activity.MainActivity;
import com.example.administrator.webexam.Contract.TeacherContract.AddClassContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddClassView extends Fragment implements AddClassContract.View {

    @Bind(R.id.input_profession)
    EditText inputProfession;
    @Bind(R.id.input_grade)
    EditText inputGrade;
    @Bind(R.id.input_classname)
    EditText inputClassname;
    @Bind(R.id.add_clas_btn)
    Button addClasBtn;
    private AddClassContract.Presenter mPresenter;


    public static AddClassView newsInstance() {

        return new AddClassView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_class_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(AddClassContract.Presenter presenter) {
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

    @OnClick(R.id.add_clas_btn)
    public void onViewClicked() {

        mPresenter.saveclass(getActivity(),inputClassname.getText().toString(),inputGrade.getText().toString(),inputProfession.getText().toString());


     getActivity().finish();
    }
}
