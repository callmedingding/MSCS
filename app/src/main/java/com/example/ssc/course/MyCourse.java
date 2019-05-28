package com.example.ssc.course;

/**
 * Created by 11201 on 2019/5/22.
 */

public class MyCourse {
    private String user_id;
    private String course_id;

    public MyCourse(String user_id,String course_id){
        this.user_id=user_id;
        this.course_id=course_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }
}
