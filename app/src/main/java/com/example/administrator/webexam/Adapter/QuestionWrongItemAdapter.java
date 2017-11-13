package com.example.administrator.webexam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/17.
 */

public class QuestionWrongItemAdapter extends BaseAdapter {


    private ArrayList<AlreadyDoneQ> arrayList;
    private LayoutInflater mInflater;
    private int arg;

    public QuestionWrongItemAdapter(Context context, ArrayList<AlreadyDoneQ> arrayList ) {

        this.arrayList = arrayList;
        mInflater = LayoutInflater.from(context);


    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        //判断是否缓存
        if (view ==null){

            holder = new ViewHolder();
            view= mInflater.inflate(R.layout.question_wrong_item,null);
            holder.question_wrong_id=view.findViewById(R.id.question_wrong_id);
            holder.question_wrong_name=view.findViewById(R.id.question_wrong_name);
            view.setTag(holder);
        }else{
            //找到缓存布局
            holder=(ViewHolder) view.getTag();
        }


        if (arrayList.get(i).isRadio()){
            holder.question_wrong_name.setText(arrayList.get(i).getRid().getQuestion());

        }else{
            holder.question_wrong_name.setText(arrayList.get(i).getJid().getQuestion());
        }
holder.question_wrong_id.setText(String.valueOf(i+1));


  if (i%4==0){
            holder.question_wrong_id.setBackgroundResource(R.mipmap.red_id);
        }else if (i%4==1){
            holder.question_wrong_id.setBackgroundResource(R.mipmap.greed_id);
        }else if(i%4==2) {
      holder.question_wrong_id.setBackgroundResource(R.mipmap.orange_id);
  }else{
      holder.question_wrong_id.setBackgroundResource(R.mipmap.blue_id);

  }



        return view;
    }
    public final class ViewHolder{

        public TextView question_wrong_id;
        public TextView question_wrong_name;

    }
}
