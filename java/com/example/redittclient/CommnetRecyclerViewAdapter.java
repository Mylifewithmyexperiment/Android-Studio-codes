package com.example.redittclient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

class CommnetRecyclerViewAdapter  extends RecyclerView.Adapter<CommnetRecyclerViewAdapter.viewHolderCommnet> {

    List<DataPojo> commentDataPojo ;
    DataPojo dataPojo;

    public CommnetRecyclerViewAdapter(ArrayList<DataPojo> comment_data_pojo) {
        this.commentDataPojo=comment_data_pojo;
    }

    @NonNull
    @Override
    public viewHolderCommnet onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(viewGroup.getContext());
        View v= layoutInflater.inflate(R.layout.comment_recycler_view_row,viewGroup,false);
        viewHolderCommnet viewHolderCommnet= new viewHolderCommnet(v);
        return viewHolderCommnet;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderCommnet viewHolderCommnet, int i) {

        dataPojo =commentDataPojo.get(i);
        viewHolderCommnet.title_heading.setText(dataPojo.getTitle_heading());
        viewHolderCommnet.title_subheading.setText(dataPojo.getTitle_sub_heading());
        viewHolderCommnet.main_heading.setText(dataPojo.get_main_heading());






    }

    @Override
    public int getItemCount() {
        return commentDataPojo.size();
    }

    public class viewHolderCommnet extends RecyclerView.ViewHolder {
        TextView title_heading;
        TextView title_subheading;
        TextView main_heading;
        TextView sub_reddit, like_count;
        ImageView LikeUp, LikeDown, Share, Comment,title_image;



        public viewHolderCommnet(@NonNull View itemView) {
            super(itemView);
            title_image = itemView.findViewById(R.id.title_image);
            title_heading = itemView.findViewById(R.id.title_heading);
            title_subheading = itemView.findViewById(R.id.title_subheading);
            main_heading = itemView.findViewById(R.id.main_heading);
            sub_reddit = itemView.findViewById(R.id.sub_reddit);
            like_count = itemView.findViewById(R.id.link_count);
            LikeUp = itemView.findViewById(R.id.like_up_icon);
            LikeDown = itemView.findViewById(R.id.like_down_icon);
            Share = itemView.findViewById(R.id.share_icon);
            Comment= itemView.findViewById(R.id.comment_icon);


        }
    }
}
