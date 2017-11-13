package com.example.administrator.webexam.Utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Administrator on 2017/9/29.
 */

public class TopicSingChoiceDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener {

private String s =null;//真正要获取的数据
    private int x=0;
    private OnTestListening onTestListening;


    public TopicSingChoiceDialog(Context context, String title, final String[] strings, OnTestListening onTestListening) {
        super(context);
        this.onTestListening = onTestListening;
        this.setTitle(title);
      //  final String[] strings1 = {"编译原理", "软件工程", "数据挖掘", "电子设计"};

        //    设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉多选框的数据集合
         * 第二个参数代表哪几个选项被选择，如果是null，则表示一个都不选择，如果希望指定哪一个多选选项框被选择，
         * 需要传递一个boolean[]数组进去，其长度要和第一个参数的长度相同，例如 {true, false, false, true};
         * 第三个参数给每一个多选项绑定一个监听器
         */

 /*       this.setItems(hobbies, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              setTopicID(i);
            }
        });*/
        this.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
setTopicID(strings[i]);
                        x=i;
                    }
                }
        );
        this.setPositiveButton("确定", this);
        this.show();
//this.create().show();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
onTestListening.getTopicID(s,x);
    }

    public interface OnTestListening{
        void getTopicID(String string,int i);
    }
    public void setTopicID(String s) {
        this.s = s;
    }
}
