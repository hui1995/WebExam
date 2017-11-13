package com.example.administrator.webexam.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.administrator.webexam.Presenter.TeacherPresenter.AddQuestionPagerPresenter;
import com.example.administrator.webexam.View.TeacherView.AddQuestionEndView;
import com.example.administrator.webexam.View.TeacherView.AddQuestionIndexView;
import com.example.administrator.webexam.View.TeacherView.AddQuestionPagerView;
import com.example.administrator.webexam.View.TeacherView.AddQuestionSelectView;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 */

public class ItemAddQuestionAdapter extends FragmentStatePagerAdapter{


    Context context;



    public ItemAddQuestionAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
if (position<=25){

    if (position==0){


      return new AddQuestionIndexView().newsInstance();
    }else {

        return new AddQuestionPagerView().newsInstance(position);

    }

}else{
    if (position==51){
        return new AddQuestionEndView().newInstance();
    }else {
        return new AddQuestionSelectView().newsInstance(position);
    }
}


    }

    @Override
    public int getCount() {
        return 52;
    }
}
