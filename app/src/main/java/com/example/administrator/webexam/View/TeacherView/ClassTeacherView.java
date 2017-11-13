package com.example.administrator.webexam.View.TeacherView;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Activity.AddClassActivity;
import com.example.administrator.webexam.Activity.ClassMainTeacherActivity;
import com.example.administrator.webexam.Activity.ExaminAuthActivity;
import com.example.administrator.webexam.Activity.StudentMangActivity;
import com.example.administrator.webexam.Adapter.ClassTeacherItemAdapter;
import com.example.administrator.webexam.Contract.TeacherContract.TeacherMainContract;
import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassTeacherView extends Fragment implements TeacherMainContract.ClassView {


    @Bind(R.id.class_teachet)
    ListView classTeachet;
    @Bind(R.id.add_class)
    FloatingActionButton addClass;
    @Bind(R.id.examin_admin)
    FloatingActionButton examinAdmin;
   /* @Bind(R.id.add_class)
    Button addClass;
    @Bind(R.id.examin_admin)
    Button examinAdmin;*/

    private ClassTeacherItemAdapter classTeacherItemAdapter;
    private TeacherMainContract.Presenter mPresenter;
    public static String CLASS_ID = "classid";


    public static ClassTeacherView newsInstance() {
        // Required empty public constructor
        return new ClassTeacherView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_class_teacher_view, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(TeacherMainContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.start();
        mPresenter.startClass(getActivity());
    }

    @Override
    public void initView(ArrayList<Classmate> list) {


        classTeacherItemAdapter = new ClassTeacherItemAdapter(getActivity(), list);
        classTeachet.setAdapter(classTeacherItemAdapter);

        classTeachet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = view.findViewById(R.id.class_id);


                Intent intent = new Intent(getActivity(), ClassMainTeacherActivity.class);


                Bundle bundle = new Bundle();
                bundle.putString(CLASS_ID, textView.getText().toString());

                intent.putExtras(bundle);
                startActivity(intent);
             //   getActivity().finish();
                //Toast.makeText(getActivity(),textView.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void noAuto() {
        Toast.makeText(getActivity(), "教师认证中，无法使用该功能", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void nextToAddClass() {
        //Toast.makeText(getActivity(),"sssssssssssss",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getActivity(), AddClassActivity.class));

    }

    @Override
    public void nextToStudentMang() {
        startActivity(new Intent(getActivity(), StudentMangActivity.class));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.add_class, R.id.examin_admin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_class:
                // startActivity(new Intent(getActivity(), LoginActivity.class));
                //   Toast.makeText(getActivity(),"ssssssssssss",Toast.LENGTH_SHORT).show();
                mPresenter.isAuto();
                break;
            case R.id.examin_admin:
                startexaminAdmin();
             /*  startActivity(new Intent(getActivity(),StudentMangActivity.class));
                getActivity().finish();*/


                break;

        }
    }

    private void startexaminAdmin() {

        startActivity(new Intent(getActivity(), ExaminAuthActivity.class));
      //  getActivity().finish();
    }


}
