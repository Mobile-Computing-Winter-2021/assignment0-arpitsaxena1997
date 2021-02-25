package com.example.helloworld;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class StudentData {
    private List<Stud> studList;

    private StudentData(){
        studList = new ArrayList<Stud>();

        for(int i = 1; i< 31; i++){
            Stud s = new Stud("MT"+String.valueOf(i), "Student"+String.valueOf(i), "CSE", "mt"+String.valueOf(i)+"@iiitd.ac.in");
            studList.add(s);
        }
    }

    public List<Stud> getStudList(){
        return studList;
    }

    public Stud getStudnetData(String rN) {
        if (sStudentData != null) {
            for(Stud std : studList) {
                if(rN.equals(std.dRollNo)) {
                    return std;
                }
            }
        }
        return null;
    }

    private  static StudentData sStudentData;

    public static  StudentData get() {
        if (sStudentData == null) {
            sStudentData = new StudentData();
        }
        return  sStudentData;
    }
}
