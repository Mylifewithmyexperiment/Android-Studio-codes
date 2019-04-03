package com.example.redittclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class SubReddit extends AppCompatActivity {

    RecyclerView recyclerView;

    ArrayList<DataPojo> commentSectionArrayList = new ArrayList<DataPojo>();
    CommnetRecyclerViewAdapter commnetRecyclerViewAdapter = new CommnetRecyclerViewAdapter(filling_data_in_recycler_view());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_reddit);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commnetRecyclerViewAdapter);
    }


    public ArrayList<DataPojo> filling_data_in_recycler_view()
    {
        commentSectionArrayList.add(new DataPojo(1,"coment_title_heading","comment_title_subheading",
        "heading"));

        return commentSectionArrayList;
    };

}
