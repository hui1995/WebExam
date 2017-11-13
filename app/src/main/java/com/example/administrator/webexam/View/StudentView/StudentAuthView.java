package com.example.administrator.webexam.View.StudentView;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.webexam.Contract.StudentContract.StudentAuthContract;
import com.example.administrator.webexam.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentAuthView extends Fragment implements StudentAuthContract.View {


    @Bind(R.id.input_invisite)
    EditText inputInvisite;
    @Bind(R.id.input_student_id)
    EditText inputStudentId;
    @Bind(R.id.input_student_name)
    EditText inputStudentName;
    @Bind(R.id.add_clas_btn)
    Button addClasBtn;
    private StudentAuthContract.Presenter mPresenter;
    @NonNull
    private static final int REQUEST_MYINFO = 3;

    public static StudentAuthView newsInstance() {


        return new StudentAuthView();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_auth, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(StudentAuthContract.Presenter presenter) {

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

        mPresenter.Auth(getActivity(),inputInvisite.getText().toString(),inputStudentId.getText().toString(),inputStudentName.getText().toString());

    getActivity().finish();
    }

    @Override
    public void Toast() {
        Toast.makeText(getActivity(),"请填写每一项内容",Toast.LENGTH_SHORT).show();
    }
}
