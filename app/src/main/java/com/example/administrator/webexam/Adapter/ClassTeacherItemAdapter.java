package com.example.administrator.webexam.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.administrator.webexam.Module.Data.Classmate;
import com.example.administrator.webexam.R;


import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/27.
 */

public class ClassTeacherItemAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Classmate> classmates;
    private  LayoutInflater mInflater;


    public ClassTeacherItemAdapter(Context context, ArrayList<Classmate> classmates) {

        this.classmates = classmates;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return classmates.size();
    }

    @Override
    public Object getItem(int i) {
        return classmates.get(i);
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
            view= mInflater.inflate(R.layout.class_teacher_item,null);
            holder.profession= view.findViewById(R.id.profession);
            holder.classname=view.findViewById(R.id.classname);
            holder.gradeclass= view.findViewById(R.id.gradeclass);
            holder.class_id=view.findViewById(R.id.class_id);
            view.setTag(holder);
        }else{
            //找到缓存布局
            holder=(ViewHolder) view.getTag();
        }

        holder.profession.setText(classmates.get(i).getPname());
        holder.gradeclass.setText(classmates.get(i).getCgrade());
        holder.classname.setText(classmates.get(i).getCname());
        holder.class_id.setText(classmates.get(i).getObjectId());


        return view;
    }


public final class ViewHolder{

   public TextView profession;
    public TextView classname;
    public TextView gradeclass;
    public TextView class_id;
}

    /*class ClassTeacherViewHolder extends ViewHolder {
        View ClassTeacherView;
        TextView profession;
        TextView classname;
        TextView gradeclass;


        public ClassTeacherViewHolder(View itemView) {
            super(itemView);
            ClassTeacherView = itemView;
            profession = itemView.findViewById(R.id.profession);
            classname = itemView.findViewById(R.id.classname);
            gradeclass = itemView.findViewById(R.id.gradeclass);


        }
    }

    class AddClassViewHolder extends RecyclerView.ViewHolder {


        View AddTeacherView;
       // Button addClass;
        TextView addClass;

        public AddClassViewHolder(View itemView) {
            super(itemView);
            AddTeacherView = itemView;
            addClass = itemView.findViewById(R.id.add_class);
        }
    }*/
}
