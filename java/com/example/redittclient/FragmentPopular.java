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

public class FragmentPopular extends Fragment {

private static String TAG="FragmentPopular";
private  String data ="Popular";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.popular_fragmnet,container,false);
        // call recycler view
        //set layout
        //set adapter

     /*   RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
       LinearLayoutManager layoutManager = new LinearLayoutManager(inflater.getContext());
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(data);
        recyclerView.setAdapter(recyclerViewAdapter);
*/
        return  view;
    }
}
