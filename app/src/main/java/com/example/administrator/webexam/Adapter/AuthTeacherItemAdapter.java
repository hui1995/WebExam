package com.example.administrator.webexam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.IUniversityStatus;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/10/17.
 */

public class AuthTeacherItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Teacher> arrayList;
    private LayoutInflater mInflater;


    public AuthTeacherItemAdapter(Context context, ArrayList<Teacher> arrayList) {

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
            view= mInflater.inflate(R.layout.auth_teahcer_item_view,null);
            holder.uname= view.findViewById(R.id.uname);
            holder.tid=view.findViewById(R.id.tid);
            holder.tname= view.findViewById(R.id.tname);

            view.setTag(holder);
        }else{
            //找到缓存布局
            holder=(ViewHolder) view.getTag();
        }

        holder.uname.setText(arrayList.get(i).getUniversity().getUname());
        holder.tid.setText(arrayList.get(i).getTeacherid());
        holder.tname.setText(arrayList.get(i).getTname());



        return view;
    }

    public final class ViewHolder{

        public TextView uname;
        public TextView tname;
        public TextView tid;

    }
}
