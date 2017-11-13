package com.example.administrator.webexam.Module;

import android.support.annotation.NonNull;

import com.example.administrator.webexam.Module.Data.University;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public interface IUniversityStatus {

    interface GetObjectIdCallback{

        void getObject(String id);
    }


    interface GetAllUniversity{
        void getAll(List<University> universities);
    }
    interface GetUniversitu{
        void getUniversity(University university);
    }


    void save(University university, @NonNull GetObjectIdCallback getObjectIdCallback);
    void findAll(GetAllUniversity getAllUniversity);
    void findone(String uid,GetUniversitu getUniversitu);
}
