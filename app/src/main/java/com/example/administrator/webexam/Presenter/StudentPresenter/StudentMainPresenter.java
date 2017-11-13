package com.example.administrator.webexam.Presenter.StudentPresenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.administrator.webexam.Contract.StudentContract.StudentMainContract;
import com.example.administrator.webexam.Module.Data.ExaminQuestion;
import com.example.administrator.webexam.Module.Data.Student;
import com.example.administrator.webexam.Module.IExaminStatus;
import com.example.administrator.webexam.Module.IStudentStatus;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Administrator on 2017/9/26.
 */

public class StudentMainPresenter implements StudentMainContract.Presenter {


    @NonNull
    private final StudentMainContract.IndexView indexView;
    @NonNull
    private final StudentMainContract.ClassView classView;
    @NonNull
    private final StudentMainContract.MeView meView;

    @NonNull
    private final IStudentStatus iStudentStatus;

    @NonNull
    private final IExaminStatus iExaminStatus;

    private final String ObjectId;
/*    private String NICKNAME="nickname";
    private String TYPE="type";
    private String ISLOGIN ="islogin";
    private String OBJECTID ="ObjectId";*/
private String ISLOGIN ="islogin";
    private static Student student2;


    public StudentMainPresenter(@NonNull StudentMainContract.IndexView indexView, @NonNull StudentMainContract.ClassView classView, @NonNull StudentMainContract.MeView meView, @NonNull IStudentStatus iStudentStatus, @NonNull IExaminStatus iExaminStatus, String Objectid) {
        this.indexView = checkNotNull(indexView);
        this.classView = checkNotNull(classView);
        this.meView = checkNotNull(meView);
        this.iStudentStatus = iStudentStatus;
        this.iExaminStatus = iExaminStatus;
        this.ObjectId=Objectid;
        indexView.setPresenter(this);
        classView.setPresenter(this);
        meView.setPresenter(this);

    }

    @Override
    public void start() {

    }

    @Override
    public void loginout(Context context) {

        SharedPreferences userInfo = context.getSharedPreferences("user_login",0);

        userInfo.edit().putBoolean(ISLOGIN, false).commit();

    }

    @Override
    public void startIndex() {
        getStudentInfo();
    }

    @Override
    public void startClass() {

    }

    @Override
    public void startMe(Context context) {
        setInfo(student2);


    }

    @Override
    public void isauth() {
        iStudentStatus.selectall(ObjectId, new IStudentStatus.LoadAllMyInfoCallback() {
            @Override
            public void getallinfo(Student student) {



                if (student.getCid().getObjectId()==""){
                    meView.toAuth();

                }else{
                    if (student.isAutherntication()){
                        meView.ToastAuthgood();
                    }else{
                        meView.ToastAuthing();
                    }

                }
            }
        });

    }

    @Override
    public void getStudentId() {
        indexView.goQuestionWrong(ObjectId);
    }


    //判断是否认证成功，
    @Override
    public void isgoClass(Context context) {
        iStudentStatus.selectall(ObjectId, new IStudentStatus.LoadAllMyInfoCallback() {
            @Override
            public void getallinfo(Student student) {
                if (student.getCid()==null){
                    classView.toAuth();

                }else{
                    if (student.isAutherntication()){
                        classView.ToastAuthgood();
                    }else{
                        classView.ToastAuthing();
                    }

                }
            }
        });




    }
    //获取学生信息

    public void getStudentInfo(){

        iStudentStatus.selectall(ObjectId, new IStudentStatus.LoadAllMyInfoCallback() {
            @Override
            public void getallinfo(Student student) {
            student2 =student;
            }
        });

    }

    private void setInfo(Student student){
        meView.initView(student.getSnickname(),student.getSphone());
    student2 =student;
    }
//获取考试信息；
    @Override
    public void getExaminIDANdName() {
        iExaminStatus.selectExam(true, new IExaminStatus.GetAllExaminCallBack() {
            @Override
            public void getAll(List<ExaminQuestion> examinQuestions) {
                int size = examinQuestions.size()-1;
                int i= (int) (Math.random()*size);


                indexView.GetExamin(examinQuestions.get(i).getObjectId(),examinQuestions.get(i).getExaminname());


            }
        });
    }

    //获取id

    @Override
    public void GetIdInfo() {
        meView.GetId(ObjectId);
    }
}
