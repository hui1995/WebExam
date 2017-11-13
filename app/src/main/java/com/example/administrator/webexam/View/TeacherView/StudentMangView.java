package com.example.administrator.webexam.View.TeacherView;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.webexam.Adapter.StudentMangItemAdapter;

import com.example.administrator.webexam.Contract.TeacherContract.StudentMangContract;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.R;
import com.google.common.base.Strings;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentMangView extends Fragment implements StudentMangContract.View {

    @Bind(R.id.list_apply_student)
    ListView listApplyStudent;
    private StudentMangContract.Presenter mPrsenter;


    public static StudentMangView newsInstance() {
        // Required empty public constructor
        return new StudentMangView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_student_mang_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void setPresenter(@NonNull StudentMangContract.Presenter presenter) {
        mPrsenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPrsenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initView() {
       mPrsenter.getStudentNOAuth();
    }

    @Override
    public void addIteminfo(final ArrayList<Student> student) {


        final StudentMangItemAdapter studentMangItemAdapter = new StudentMangItemAdapter(getActivity(),student);
        listApplyStudent.setAdapter(studentMangItemAdapter);

        studentMangItemAdapter.setOnButtonClick(new StudentMangItemAdapter.OnButtonClick() {
            @Override
            public void onClick(String sid) {


                mPrsenter.updateAuthTrue(sid);


            }
        }, new StudentMangItemAdapter.OnDeleteClcik() {
            @Override
            public void onClick(String id) {
                mPrsenter.updateAuthFales(id);
            }
        });

    }
}
