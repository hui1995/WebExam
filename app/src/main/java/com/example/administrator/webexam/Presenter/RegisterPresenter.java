package com.example.administrator.webexam.Presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.webexam.Contract.RegisterContract;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.Data.Teacher;
import com.example.administrator.webexam.Module.Data.University;
import com.example.administrator.webexam.Module.IStudentStatus;
import com.example.administrator.webexam.Module.ITeacherStatus;
import com.example.administrator.webexam.Module.IUniversityStatus;
import com.example.administrator.webexam.View.Register.RegisterView;

import java.util.List;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.sms.BmobSMS;
import cn.bmob.sms.exception.BmobException;
import cn.bmob.sms.listener.RequestSMSCodeListener;
import cn.bmob.sms.listener.VerifySMSCodeListener;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/9/21.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private static boolean isTeacher;

@NonNull
private final RegisterContract.RegView mView;
    @NonNull
    private final RegisterContract.RegPhoneView mRPhoneView;

    @NonNull
    private final RegisterContract.RegTeacherView mRTeacherView;

    @NonNull
    private final RegisterContract.RegStudentView mRStudentView;
    private boolean checkgood =false;//验证码是否正确；
    private String phone,passwd,nickname,name,univiersity,teacherid;
    @NonNull
    private final IStudentStatus iStudentStatus;
    @NonNull
    private final ITeacherStatus iTeacherStatus;
    @NonNull
    private final IUniversityStatus iUniversityStatus;

    public RegisterPresenter(@NonNull RegisterView mView, @NonNull RegisterContract.RegPhoneView mRPhoneView, @NonNull RegisterContract.RegTeacherView mRTeacherView, @NonNull RegisterContract.RegStudentView mRStudentView, @NonNull IStudentStatus studentStatus, @NonNull ITeacherStatus teacherStatus, @NonNull IUniversityStatus universityStatus) {



        this.mView =checkNotNull(mView) ;
        this.mRPhoneView = checkNotNull(mRPhoneView) ;
        this.mRTeacherView = checkNotNull(mRTeacherView);
        this.mRStudentView = checkNotNull(mRStudentView);
       // this.iUniversityStatus = iUniversityStatus;

        mView.setPresenter(this);
        mRPhoneView.setPresenter(this);
        mRTeacherView.setPresenter(this);
        mRStudentView.setPresenter(this);
        iStudentStatus = studentStatus;
       iTeacherStatus = teacherStatus;
  iUniversityStatus = universityStatus;
    }


    @Override
    public void start() {


    }
//保存注册类型
    @Override
    public void saveSignup_Type(boolean isTeacher, Context context) {
      /*  SharedPreferences sharedPreferences = context.getSharedPreferences("signinfo", Activity.MODE_APPEND);
   SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("signup_type",singup_type);
        editor.apply()*/;
        this.isTeacher = isTeacher;
    }

    //核对手机号，是否是正确手机格式
    @Override
    public boolean checkPhone(String phone) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        this.phone = phone;
      //  return m.matches();

        return true;
    }

    //核对密码
    @Override
    public boolean checkPasswd(String passwd, String passwd2) {


        if (!passwd.isEmpty()||!passwd2.isEmpty()){
            if (passwd.equals(passwd2)){
                this.passwd = passwd;
                return true;
            }
        }


        return false;

    }
//获取验证码
    @Override
    public void getcode(String phone, final Context context) {

System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG");

//放入module层
     BmobSMS.requestSMSCode(context,phone,"计算机网络考试", new RequestSMSCodeListener() {
            @Override
            public void done(Integer integer, BmobException e) {


                if (e == null){
                  mRPhoneView.nextgetcode(isTeacher);
                }else {

        mRPhoneView.setCodefail();


                }
            }
        });


    }




//从数据库获取注册信息
    @Override
    public boolean isNoSignupPhone(String phone) {


        return true;
    }
//核对昵称
    @Override
    public boolean isNoEmptyNickname(String nickname) {

        if (!nickname.isEmpty()){
            this.nickname = nickname;
            return true;
        }
        return false;
    }
//核对验证码
    @Override
    public void checkSMS(Context context,String smscode) {


        BmobSMS.verifySmsCode(context, phone, smscode, new VerifySMSCodeListener() {


            @Override
            public void done(BmobException e) {


                if (e == null){
                    if (isTeacher){
                       mRTeacherView.signupgood();
                    }else{
                        mRStudentView.signupgood();
                    }


                }else{
                    if (isTeacher){
                        mRTeacherView.signupfail();
                    }else {
                        mRStudentView.signupfail();
                    }

                  /*  Log.i("bmob", "验证失败：code ="+e.getErrorCode()+",msg = "+e.getLocalizedMessage());
                    System.out.println(e.getErrorCode()+"msg="+e.getLocalizedMessage());*/

                }

            }
        });



    }
    //将学生注册信息传到数据库

    @Override
    public void signupStudent() {

        Student student = new Student();
        student.setSphone(phone);
        student.setSnickname(nickname);
        student.setSpasswd(passwd);
        student.setAutherntication(false);
        iStudentStatus.save(student);

    }
//保存教师注册信息
    @Override
    public void signupTeacher() {

        final University univers = new University();
        univers.setUname(univiersity);


        iUniversityStatus.save(univers, new IUniversityStatus.GetObjectIdCallback() {
            @Override
            public void getObject(String id) {
                System.out.println("-------------"+id);
                if (!id.equals("fail")){


                  //  University university = new University();


                    Teacher teacher = new Teacher();
                    University university = new University();

                    university.setObjectId(id);
                    teacher.setTphone(phone);
                    teacher.setTnickname(nickname);
                    teacher.setTpasswd(passwd);
                    teacher.setTname(name);
                    teacher.setTeacherid(teacherid);
                   teacher.setUniversity(university);
                    teacher.setAuthentication(false);


                    iTeacherStatus.save(teacher);
                }else{
                    mRTeacherView.ToastNoUniversity();
                }

            }
        });



    }
//教师注册时核对信息
    @Override
    public boolean checkinfo(String name, String teacherid, String univerisity) {

        if (!name.isEmpty()){
            if (!teacherid.isEmpty()){
                if (!univerisity.isEmpty()){
                    this.name = name;
                    this.teacherid = teacherid;
                    this.univiersity = univerisity;
                    return true;

                }else {
                    mRTeacherView.ToastNoUniversity();
                }

            }else {
                mRTeacherView.ToasNoTeacherID();
            }


        }else {

            mRTeacherView.ToastNoName();
        }
        return false;
    }

    @Override
    public void selectphoneStudent() {
        selectphoneTeacher();
       /* iStudentStatus.selectphone(phone, new IStudentStatus.CheckNoPhoneCalback() {
            @Override
            public void getisphoneload(@NonNull List<Student> students) {

//mRPhoneView.test(phone,students.get(0).getSphone());

if (students.isEmpty()){
 selectphoneTeacher();
}else{

    mRPhoneView.toastphoneexit();
}

            }
        });*/
    }

    @Override
    public void selectphoneTeacher() {
        iTeacherStatus.selectphone(phone, new ITeacherStatus.CheckNoPhoneCallback() {
            @Override
            public void getisphoneload(@NonNull List<Teacher> teacher) {
                if (teacher.isEmpty()){
                    mRPhoneView.gonextindex();

                }else{
                   mRPhoneView.toastphoneexit();
                }
            }
        });

    }
}
