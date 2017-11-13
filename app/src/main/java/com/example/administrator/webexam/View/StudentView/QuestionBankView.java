package com.example.administrator.webexam.View.StudentView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.webexam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionBankView extends Fragment {


    public QuestionBankView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question_bank_view, container, false);
    }

}
