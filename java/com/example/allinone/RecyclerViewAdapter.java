package com.example.allinone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolders> {

    DataPojo dataPojo;
    List<DataPojo> dataPojoList;

    public RecyclerViewAdapter(List<DataPojo> dataModelArrayList) {
        this.dataPojoList = dataModelArrayList;
    }


    @NonNull
    @Override
    public viewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.recycler_view_row, viewGroup, false);
        return new viewHolders(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolders viewHolders, int i) {

        dataPojo = dataPojoList.get(i);
        viewHolders.all_image.setImageResource(dataPojo.getAll_image());
        viewHolders.all_address.setText(dataPojo.getAll_address());
        viewHolders.location_name.setText(dataPojo.getLocation_name());
        viewHolders.location_type.setText(dataPojo.getLocation_type());

        viewHolders.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Screen2.class);
                intent.putExtra("location_name", viewHolders.location_name.getText().toString());
                intent.putExtra("location_type", viewHolders.location_type.getText().toString());
                intent.putExtra("all_address", viewHolders.all_address.getText().toString());
                intent.putExtra("all_image", viewHolders.all_image.getId());
                Log.i("loaction type ", viewHolders.location_type.getText().toString());
                Log.i("loation name ", viewHolders.location_name.getText().toString());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataPojoList.size();
    }

    class viewHolders extends RecyclerView.ViewHolder {

        ImageView all_image;
        TextView location_name, all_address, location_type;

        public viewHolders(@NonNull View itemView) {
            super(itemView);

            all_image = itemView.findViewById(R.id.all_image);
            location_name = itemView.findViewById(R.id.locaion_name);
            all_address = itemView.findViewById(R.id.all_address);
            location_type = itemView.findViewById(R.id.location_type);

        }
    }
}
