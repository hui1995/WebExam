package com.example.administrator.webexam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/12.
 */

public class ShowPagerItemAdapter extends BaseAdapter {


    private ArrayList<ExaminQuestion> examinQuestionArrayList;
    private LayoutInflater mInflater;
    private int arg;

    public ShowPagerItemAdapter(Context context, ArrayList<ExaminQuestion> examinQuestionArrayList ,int i) {

        this.examinQuestionArrayList = examinQuestionArrayList;
        mInflater = LayoutInflater.from(context);
        this.arg =i;
}

    @Override
    public int getCount() {
        return examinQuestionArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return examinQuestionArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if (holder==null){
            holder = new ViewHolder();
            view= mInflater.inflate(R.layout.show_pager_item,null);
            holder.examin_name=view.findViewById(R.id.examin_na);
            holder.option = view.findViewById(R.id.option);
            holder.pagerid=view.findViewById(R.id.pager_id);
view.setTag(holder);
        }else{
            holder=(ViewHolder) view.getTag();
        }

       holder.examin_name.setText(examinQuestionArrayList.get(i).getExaminname());
        holder.pagerid.setText(examinQuestionArrayList.get(i).getObjectId());
        if (arg==1){
            if (examinQuestionArrayList.get(i).isFinish()){
                holder.option.setText("完整试卷");
            }else{
                holder.option.setText("未完成试卷");
            }


        }else if (arg==3){

            holder.option.setText("点击设置考试");


        }else if (arg==2){
            holder.option.setText("点击开始考试");
        }
        return view;
    }



    public final class ViewHolder{

        public TextView examin_name;
        public TextView option;
        public TextView pagerid;

    }
}
