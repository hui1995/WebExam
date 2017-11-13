package com.example.administrator.webexam.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.webexam.Module.Data.AlreadyDoneQ;
import com.example.administrator.webexam.Module.Data.JudgementQuestions;
import com.example.administrator.webexam.Module.Data.RadioQuestions;
import com.example.administrator.webexam.Module.IAlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.IExaminStatus;
import com.example.administrator.webexam.Module.impl.AlreadyDoneSomeQuestionsStatus;
import com.example.administrator.webexam.Module.impl.ExaminStatus;
import com.example.administrator.webexam.R;
import com.example.administrator.webexam.View.Examnation.IndexExaminationView;
import com.example.administrator.webexam.View.Examnation.JudgeExaminationView;
import com.example.administrator.webexam.View.Examnation.MenuExaminationView;
import com.example.administrator.webexam.View.Examnation.NoHaveFragment;
import com.example.administrator.webexam.View.Examnation.RadioExaminationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExaminationActivity extends AppCompatActivity implements RadioExaminationView.FragmentInteraction, JudgeExaminationView.FragmentInteraction,
        IndexExaminationView.FragmentInteraction {



    @Bind(R.id.examin_time)
    TextView examinTime;

    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.now_index)
    ImageView nowIndex;
    private int recLen = 3600;


    private MyAdapter myAdapter;

    private String examinname;
    private static int radiocount;
    private static int judecount;
    public static int countall;
    private IExaminStatus iExaminStatus;
    public static String examinid;
    public static List<RadioQuestions> list1 = new ArrayList<RadioQuestions>();
    public static List<JudgementQuestions> list2 = new ArrayList<JudgementQuestions>();
    LocalBroadcastManager mLocalBroadcastManager;
    ArrayList<String> arrayList = new ArrayList<String>();
    private static int index = 0;
    private String OBJECTID = "ObjectId";
    private String LOGIN_TYPE = "login_type";
    public static String ObjectId;
    private static final String Examin_ID = "examinid";
    private static final String EXAMIN_NAME = "examinname";
    private static final String TYPE_EXAMIN = "examintype";
    private static int type;

    private static boolean isTimeIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examination);
        ButterKnife.bind(this);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        examinid = bundle.getString(Examin_ID, "");
        examinname = bundle.getString(EXAMIN_NAME, "");

        type = bundle.getInt(TYPE_EXAMIN);
        SharedPreferences userInfo = this.getSharedPreferences("user_login", 0);
        ObjectId = userInfo.getString(OBJECTID, "");

        iExaminStatus = new ExaminStatus();


        if (type == 4) {
            loadDatadaily();
            countall=13;



        } else {
            iExaminStatus.countRadioGet(examinid, new IExaminStatus.GetCountCallBack() {
                @Override
                public void getCount(int count) {
                    radiocount = count;


                    iExaminStatus.countJudmGet(examinid, new IExaminStatus.GetCountCallBack() {
                        @Override
                        public void getCount(int count) {
                            judecount = count;
                            countall = radiocount + judecount + 3;

                            loadData();

                        }
                    });

                }
            });
        }


        // Toast.makeText(this,examinname+examinid,Toast.LENGTH_SHORT).show();


        for (int i = 0; i < 50; i++) {
            arrayList.add("null");

        }
    }

    @OnClick({R.id.back, R.id.now_index})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.now_index:
                if (index == 0) {

                    if (type == 1 || type == 0) {
                        Toast.makeText(getApplicationContext(), "  请点击查阅", Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(getApplicationContext(), "  请开始考试", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Intent intent = new Intent("com.leyikao.jumptopageexamin");
                    intent.putExtra("index", countall - 1);
                    mLocalBroadcastManager.sendBroadcast(intent);
                    break;
                }


        }
    }


    private void setViewPager() {

        vp.setCurrentItem(0);

        myAdapter = new MyAdapter(getSupportFragmentManager());

        vp.setAdapter(myAdapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.leyikao.jumptonextexamin");
        filter.addAction("com.leyikao.jumptopageexamin");
        lbm.registerReceiver(mMessageReceiver, filter);

    }

    private void loadDatadaily() {


        final IAlreadyDoneSomeQuestionsStatus iStatus = new AlreadyDoneSomeQuestionsStatus();
        iStatus.selectTen(ObjectId, false, new IAlreadyDoneSomeQuestionsStatus.GetWQuesitonCalBack() {
            @Override
            public void get(final List<AlreadyDoneQ> list) {

if (list.size()<10){
    Toast.makeText(getApplicationContext(),"试题量不足，请参加模拟考试",Toast.LENGTH_SHORT).show();
}else {


    for (int i = 0; i < list.size(); i++) {


        if (list.get(i).isRadio()) {


            if (i <= 9) {
                list1.add(list.get(i).getRid());

            }

            if (i == 9) {
                setViewPager();
            }

        } else {
            if (i <= 9) {
                list2.add(list.get(i).getJid());
            }


            if (i == 9) {
                setViewPager();
            }
        }
    }
}
            }
        });


    }

    private void loadData() {

        // System.out.println("SSSSSSSSSSSSSSSSSS"+examinid);

        iExaminStatus.GetRadioAll(examinid, new IExaminStatus.GetAllQuestionCallBack() {
            @Override
            public void getAllquestion(List<RadioQuestions> list) {

                list1 = list;
                iExaminStatus.getJudmeAll(examinid, new IExaminStatus.GetAllSelectCallBack() {
                    @Override
                    public void getAllQuestion(List<JudgementQuestions> list) {
                        list2 = list;
                        setViewPager();

                    }
                });


            }
        });


    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.leyikao.jumptonextexamin")) {
                jumpToNext();
            } else if (intent.getAction().equals("com.leyikao.jumptopageexamin")) {
                int index = intent.getIntExtra("index", 0);
                jumpToPage(index);
            }
        }
    };

    public void jumpToNext() {
        int position = vp.getCurrentItem();

        vp.setCurrentItem(position + 1);

    }

    public void jumpToPage(int index) {
        vp.setCurrentItem(index);
    }

    @Override
    public void process(String str, int i) {

        if (str == null) {
            str = "null";
        }

        arrayList.set(i, str);


    }

    @Override
    public void nowsetTime() {
        if (type == 1 || type == 0) {

        } else {
            new Thread(new TimeThread()).start();
        }

    }

    private class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            index = position - 1;

            if (type == 0 || type == 1) {
                if (countall <= 27) {
                    if (position == 0) {
                        return new IndexExaminationView().newsInstance(examinname, type);
                    } else if (position == countall - 1) {
                        return new MenuExaminationView().newInstance(arrayList, isTimeIn, type, false, examinid);

                    } else if (position == countall - 2) {
                        return new NoHaveFragment().newsInstance();


                    } else {
                        return new RadioExaminationView().newsInstance(position, type);

                    }


                } else if (countall < 52) {

                    if (position <= 25) {

                        if (position == 0) {
                            return new IndexExaminationView().newsInstance(examinname, type);
                        } else {
                            return new RadioExaminationView().newsInstance(position, type);


                        }
                    } else {


                        if (position == countall - 1) {
                            return new MenuExaminationView().newInstance(arrayList, isTimeIn, type, false, examinid);
                        } else if (position == countall - 2) {
                            return new NoHaveFragment().newsInstance();


                        } else {
                            return new JudgeExaminationView().newsInstance(position, type);
                        }


                    }


                } else {

                    if (position <= 25) {


                        if (position == 0) {

                            return new IndexExaminationView().newsInstance(examinname, type);
                        } else {
                            Log.i("GGG", "SSSSSSSSSSSSSSS");
                            return new RadioExaminationView().newsInstance(position, type);

                        }
                    } else {

                        if (position == 12) {
                            return new MenuExaminationView().newInstance(arrayList, isTimeIn, type, true, examinid);

                        } else if (position == 11) {
                            return new NoHaveFragment().newsInstance();


                        } else {
                            return new JudgeExaminationView().newsInstance(position, type);
                        }
                    }


                }


            } else if (type == 4) {

                if (position <= list1.size()) {

                    if (position == 0) {
                        return new IndexExaminationView().newsInstance(examinname, type);
                    } else {
                        return new RadioExaminationView().newsInstance(position, type);
                    }

                } else {
                    if (position == countall - 1) {
                        return new MenuExaminationView().newInstance(arrayList, isTimeIn, type, true, examinid);
                    } else if (position == countall - 2) {
                        return new NoHaveFragment().newsInstance();
                    } else {
                        return new JudgeExaminationView().newsInstance(position, type);
                    }
                }
            } else {

                if (position <= 25) {
                    if (position == 0) {


                        return new IndexExaminationView().newsInstance(examinname, type);
                    } else {

                        return new RadioExaminationView().newsInstance(position, type);
                    }
                } else {
                    if (position == 52) {

                        return new MenuExaminationView().newInstance(arrayList, isTimeIn, type, true, examinid);
                    } else {

                        if (position == 51) {
                            return new NoHaveFragment().newsInstance();

                        }


                        return new JudgeExaminationView().newsInstance(position, type);
                    }


                }
            }

        }

        @Override
        public int getCount() {
            return countall;


        }
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    recLen--;
                    int mint = recLen / 60;
                    int second = recLen % 60;
                    String time = mint + ":" + second;

                    examinTime.setText(time);
                    if (recLen <= 0) {

                    }
                    break;
                case 2:

                    examinTime.setText("考试结束");
                    vp.setCurrentItem(52);
                    isTimeIn = false;


            }

            super.handleMessage(msg);
        }
    };


    public class TimeThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Message message = new Message();
                    if (recLen > 0) {

                        Thread.sleep(1000);


                        message.what = 1;
                        handler.sendMessage(message);
                    } else if (recLen == 0) {

                        message.what = 2;
                        handler.sendMessage(message);

                    } else {

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
