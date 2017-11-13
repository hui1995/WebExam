package com.example.administrator.webexam.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/9/28.
 */

public class StudentMangItemAdapter extends BaseAdapter {



    private Context context;
    private ArrayList<Student> students;
    private OnButtonClick onButtonClick;
    private OnDeleteClcik onDeleteClcik;
    private  LayoutInflater mInflater;

    public StudentMangItemAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

  ViewHolder viewHolder=null;

        if (view==null){
            viewHolder = new ViewHolder();
            view = mInflater.inflate(R.layout.add_student_item,null);

            viewHolder.student_id = view.findViewById(R.id.show_student_id_2);

            viewHolder.student_name=view.findViewById(R.id.show_student_name_2);
            viewHolder.apply = view.findViewById(R.id.btn_add_student);
            viewHolder.delete=view.findViewById(R.id.btn_del_student);
            view.setTag(viewHolder);
        }else{
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.student_id.setText(students.get(i).getSuid());
        viewHolder.student_name.setText(students.get(i).getSname());


        viewHolder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                onButtonClick.onClick(students.get(i).getObjectId());
                students.remove(i);
                notifyDataSetChanged();
            }
        });
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteClcik.onClick(students.get(i).getObjectId());
                students.remove(i);
                notifyDataSetChanged();
            }
        });



        return view;
    }


   public final class ViewHolder{
        TextView student_id;
        TextView student_name;

        Button apply;
       Button delete;
    }

    public OnButtonClick getOnButonClick() {

        return onButtonClick;
    }

    public OnDeleteClcik getOnDeleteClcik(){
        return onDeleteClcik;
    }



    public void setOnButtonClick(OnButtonClick onButtonClick, OnDeleteClcik onDeleteClcik) {
        this.onButtonClick = onButtonClick;
        this.onDeleteClcik=onDeleteClcik;
    }


    public interface OnButtonClick {
        public void onClick(String sid);
    }


    public interface OnDeleteClcik{
        public void onClick(String id);
    }
}
