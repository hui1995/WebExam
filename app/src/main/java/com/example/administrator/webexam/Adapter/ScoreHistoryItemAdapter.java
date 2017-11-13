package com.example.administrator.webexam.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.webexam.Module.Data.ReportCard;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/16.
 */

public class ScoreHistoryItemAdapter extends BaseAdapter {
    private ArrayList<ReportCard> arrayList;
    private LayoutInflater mInflater;
    private int arg;
    private boolean isQuestion;

    public ScoreHistoryItemAdapter(Context context, ArrayList<ReportCard> arrayList ,boolean isQuestion) {

        this.arrayList = arrayList;
        mInflater = LayoutInflater.from(context);
        this.isQuestion=isQuestion;

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
            view= mInflater.inflate(R.layout.score_history_item,null);
       holder.examinname=view.findViewById(R.id.examin_name);
            holder.examinscore=view.findViewById(R.id.examin_score);
            view.setTag(holder);
        }else{
            //找到缓存布局
            holder=(ViewHolder) view.getTag();
        }


    holder.examinscore.setText(arrayList.get(i).getScore()+"分");


        if (i%4==0){
            holder.examinscore.setBackgroundResource(R.mipmap.red_id);
        }else if (i%4==1){
            holder.examinscore.setBackgroundResource(R.mipmap.greed_id);
        }else if(i%4==2) {
            holder.examinscore.setBackgroundResource(R.mipmap.orange_id);
        }else{
            holder.examinscore.setBackgroundResource(R.mipmap.blue_id);

        }
if (isQuestion){
    holder.examinname.setText(arrayList.get(i).getSid().getSname());
}else {
    holder.examinname.setText(arrayList.get(i).getEid().getExaminname());
}




        return view;
    }
    public final class ViewHolder{

        public TextView examinname;
        public TextView examinscore;

    }
}
