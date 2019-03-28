package com.example.jiosecure;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>
{

  //  private List<String> mData;
    List<DataPOJO> data_pojo_list;
   public DataPOJO str_top_des_data_pojo;
    private LayoutInflater mInflater;

    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<DataPOJO> data) {
        this.mInflater = LayoutInflater.from(context);
        this.data_pojo_list = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_view_row, parent, false);
        return new ViewHolder(view);
    }
    // binds the data to the TextView in each row

    @Override
    public void onBindViewHolder(ViewHolder holder, int position ) {
        str_top_des_data_pojo = data_pojo_list.get(position);
        holder.title.setText(str_top_des_data_pojo.getDevice_name());
        holder.myImageView.setImageResource(str_top_des_data_pojo.getImageId());
        holder.description.setText(str_top_des_data_pojo.getDevice_description());
        // when user click on each item it should get it count and is passed as reference from here ...

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener!= null)
                { mClickListener.onItemClick(v, str_top_des_data_pojo);
                }
            }
        });

    }

    // total number of rows
    @Override
    public int getItemCount() {

        return data_pojo_list.size();
    }

    //need to check to get name
    public String getImageId() {

        return str_top_des_data_pojo.getDevice_name();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView description;
        ImageView myImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myImageView=itemView.findViewById(R.id.iv_icons);
            title=itemView.findViewById(R.id.tv_title);
            description = itemView.findViewById(R.id.tv_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
          //  if (mClickListener != null) mClickListener.onItemClick(view);
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view,DataPOJO dataPOJO );
    }
}

