package com.example.jiosecure;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolders> {
    // defining adapter with generic parameter passed as recyclerView View holder class as its argument to have relation between them

    private String[] data;
    List<DataPOJO> dataPOJOList_containing_all_getter_and_setter;
    DataPOJO dataPOJO_of_image_title_of_pojo_type;

    public RecyclerViewAdapter(List<DataPOJO> dataPOJOList_containing_all_getter_and_setter) {

        this.dataPOJOList_containing_all_getter_and_setter= dataPOJOList_containing_all_getter_and_setter;
    }

    @NonNull
    @Override
    public viewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //create view and keep it in recycler view
        // using layout inflator to call xml
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_row, viewGroup, false);
        // only two lines
        return new viewHolders(view);// corresponding view holder is created and is loaded by viewHolders
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolders viewHolder_class_reference, int position_of_data) {
        // it is used to bind the data of xml together

        // here we will not use string as we have to get all image device_name and device_description together so it is better to use pojo
        // so as to get its getter and allocate its data accordingly.

        //String data_binding = data[position_of_data];

        dataPOJO_of_image_title_of_pojo_type = dataPOJOList_containing_all_getter_and_setter.get(position_of_data);
            //in either way we can do it .
           //viewHolder_class_reference.device_name.setText(data_binding);
        // viewHolder_class_reference.device_description.setText(data_binding);

        // now we are fetching all device name, image and description and placing them in recycler view class
        viewHolder_class_reference.device_name.setText(dataPOJO_of_image_title_of_pojo_type.getDevice_name());
        viewHolder_class_reference.device_description.setText(dataPOJO_of_image_title_of_pojo_type.getDevice_description());
        viewHolder_class_reference.imgIcon.setImageResource(dataPOJO_of_image_title_of_pojo_type.getImageId());

    }

    @Override
    public int getItemCount() {   // return total no of data
        return dataPOJOList_containing_all_getter_and_setter.size();
    }

    public class viewHolders extends RecyclerView.ViewHolder {

        ImageView imgIcon;
        TextView device_name, device_description;

        public viewHolders(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.iv_icons);
            device_name = itemView.findViewById(R.id.tv_title);
            device_description = itemView.findViewById(R.id.tv_desc);


        }
    }
}
