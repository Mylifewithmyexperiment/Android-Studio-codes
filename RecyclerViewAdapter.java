package com.example.jiosecure;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    // defining adapter with generic parameter passed as recyclerView View holder class as its argument to have relation between them

    private String[] data;

    public RecyclerViewAdapter(String[] data) {
        this.data = data;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //create view and keep it in recycler view
        // using layout inflator to call xml
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_row, viewGroup, false);
        // only two lines

        return new viewHolder(view);// corresponding view holder is created and is loaded by viewHolder


    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int position_of_data) {
        // it is used to bind the data of xml together
        String data_binding = data[position_of_data];
//in either way we can do it .
//viewHolder.title.setText(data_binding);
viewHolder.desc.setText(data_binding);

    }

    @Override
    public int getItemCount() {   // return total no of data
        return data.length;
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView title, desc;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.iv_icons);
            title = itemView.findViewById(R.id.tv_title);
            desc = itemView.findViewById(R.id.tv_desc);


        }
    }


}
