package com.example.a11201.godsavemeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ssc.Announcement.Announcement;
import com.example.ssc.Announcement.AnnouncementDao;

public class InsertAnnouncement extends AppCompatActivity {

    EditText edComments;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_announcement);
        edComments= (EditText) findViewById(R.id.commentsET);
        submitBtn= (Button) findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAnn();
            }
        });
    }
    void insertAnn(){
        String comments=edComments.getText().toString().trim();
        Bundle bundle_from=getIntent().getExtras();
        String publisher_id=bundle_from.getString("my_id");
        Announcement ann=new Announcement(publisher_id,comments);
        AnnouncementDao dao=new AnnouncementDao(this);
        dao.insert(ann);
        Bundle bundle_to=new Bundle();
        bundle_to.putString("my_id",publisher_id);
        bundle_to.putString("comments",comments);
        bundle_to.putString("from","发布公告");
        Intent intent=new Intent();
        intent.putExtras(bundle_to);
        intent.setClass(InsertAnnouncement.this,AnnouncementDetailActivity.class);
    }
}
