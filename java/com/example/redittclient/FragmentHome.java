package com.example.redittclient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    private static final String TAG_OF_HOME_FRAGMENT= "FragmentHome";

   // private Button btnHome;
   FragmentHome fragmentHome;
   RecyclerViewAdapter recyclerViewAdapter;
   ArrayList<DataPojo> data_pojo_put_data  = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_fragment, container,false);


        //call recycler_view
        RecyclerView recyclerView =view.findViewById(R.id.recyclerView);
        //set layout // set adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter= new RecyclerViewAdapter(placing_data_in_home_fragment_recycler_view());
        recyclerView.setAdapter(recyclerViewAdapter);
        fragmentHome = this;

        return view;
    }

    // when you have to put data put data in array list
    String title_heading="title heading";
    String title_sub_heading ="title sub heading";
    String heading ="heading ";
    int image_id =1;

    public  ArrayList<DataPojo> placing_data_in_home_fragment_recycler_view()
    {

        data_pojo_put_data.add(new DataPojo( image_id, title_heading, title_sub_heading, heading ));
        data_pojo_put_data.add(new DataPojo( image_id, title_heading, title_sub_heading, "apple" ));
        data_pojo_put_data.add(new DataPojo( image_id, title_heading, title_sub_heading, "microsoft" ));
        data_pojo_put_data.add(new DataPojo( image_id, title_heading, title_sub_heading, "google" ));
        data_pojo_put_data.add(new DataPojo( image_id, title_heading, title_sub_heading, "amazon" ));
        data_pojo_put_data.add(new DataPojo( image_id, title_heading, title_sub_heading, "yahoo" ));

        return data_pojo_put_data;
    }
}
