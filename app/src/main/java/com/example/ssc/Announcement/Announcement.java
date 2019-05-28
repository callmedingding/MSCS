package com.example.ssc.Announcement;

/**
 * Created by 11201 on 2019/5/25.
 */

public class Announcement {
    private String user_id;
    private String comments;
    public Announcement(String user_id,String comments){
        this.user_id=user_id;
        this.comments=comments;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
