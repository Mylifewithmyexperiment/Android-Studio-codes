package com.example.readingcontacts;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {

    private static RadioButton lastChecked;
    private static int lastCheckedPos = -2;   // so that their is no radio button which need to be selected in the entire list initially
    public List<DataPojo> data_Pojo_of_contact_list;
    DataPojo dataPojo_of_contacts;
    MainActivity mainActivity;
    String take_contact_name, take_contact_number;
    RecyclerViewAdapter recyclerViewAdapter;
    RadioButton new_radio_button;
    RadioButton lastChecked_Radio_button = null;
    RadioButton row_radio_btn;


    RecyclerViewAdapter(MainActivity mainActivityContext, List<DataPojo> dataPojos_of_contact_list) {
        this.data_Pojo_of_contact_list = dataPojos_of_contact_list;
        this.mainActivity = mainActivityContext;
        recyclerViewAdapter = this;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_view_row, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, final int position) {

        dataPojo_of_contacts = data_Pojo_of_contact_list.get(position);

        //for image
        try {
            Bitmap image = null;
            if (!dataPojo_of_contacts.getImage_id().equals("") && dataPojo_of_contacts.getImage_id() != null) {
                image = BitmapFactory.decodeFile(dataPojo_of_contacts.getImage_id());
                if (image != null) {
                    viewHolder.contact_image.setImageBitmap(image);
                } else {
                    image = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.reddit_icon);
                    viewHolder.contact_image.setImageBitmap(image);
                }
            } else {
                image = BitmapFactory.decodeResource(mainActivity.getResources(), R.drawable.reddit_icon);
                viewHolder.contact_image.setImageBitmap(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //for contact name
        if (dataPojo_of_contacts.getContact_name() != null) {
            viewHolder.contact_name.setText(dataPojo_of_contacts.getContact_name());
        } else {
            viewHolder.contact_name.setText("No name Set");
        }

        //for contact number
        if (dataPojo_of_contacts.getContact_number() != null) {
            viewHolder.contact_number.setText(dataPojo_of_contacts.getContact_number());
        } else {
            viewHolder.contact_number.setText("No number Set ");
        }

        //radio button operations
        viewHolder.radioButton = viewHolder.itemView.findViewById(R.id.radio_button);

        // for making complete view_holder clickable
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, "View get clicked", Toast.LENGTH_SHORT).show();
                LinearLayout linearLayout = (LinearLayout) v;
                row_radio_btn = ((RadioButton) linearLayout.getChildAt(2));

                lastChecked = new_radio_button;
                // for single select deselect
                if (row_radio_btn.isChecked()) {
                    row_radio_btn.setChecked(false);
                    if (lastChecked != null) {
                        lastChecked.setChecked(false);
                    }
                } else {
                    row_radio_btn.setChecked(true);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data_Pojo_of_contact_list.size();
    }

    public void updateList(List<DataPojo> newList) {
        data_Pojo_of_contact_list = new ArrayList<>();
        data_Pojo_of_contact_list.addAll(newList);
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView contact_image;
        TextView contact_name, contact_number;
        RadioButton radioButton;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            contact_image = itemView.findViewById(R.id.contact_image);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_number = itemView.findViewById(R.id.contact_number);
            radioButton = itemView.findViewById(R.id.radio_button);
        }
    }
}
