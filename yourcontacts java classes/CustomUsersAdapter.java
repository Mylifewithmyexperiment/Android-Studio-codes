package com.example.yourcontacts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class CustomUsersAdapter extends ArrayAdapter<DataPojo> {

    // variable Declaration

    Context context;
    int lastSelectedPosition = -1;
    List<DataPojo> dataPojos;
    Intent intent;
    RadioButton radioButton;
    RadioButton rb;
    RadioButton lastcheck;

    public CustomUsersAdapter(Context context, List<DataPojo> users) {
        super(context, R.layout.custom_view_row, users);
        this.context = context;
        this.dataPojos = users;

    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ImageView contact_image;
        TextView contact_number, contact_name;


        // Checking if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_view_row, parent, false);
            radioButton = convertView.findViewById(R.id.radio_button);
        }

        DataPojo datapojo = getItem(position);
        // calling image, name and number on the screen
        contact_image = convertView.findViewById(R.id.contact_image);
        contact_name = convertView.findViewById(R.id.contact_name);
        contact_number = convertView.findViewById(R.id.contact_number);
        radioButton = convertView.findViewById(R.id.radio_button);

        rb = radioButton;

// code for making initial default condition to be false in radio button selection.
        if (position == lastSelectedPosition) {
            rb.setChecked(true);

        } else {
            rb.setChecked(false);
        }

        // radio button action for single choice
        radioButton.setChecked(position == lastSelectedPosition);
        radioButton.setTag(position);
        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lastSelectedPosition = (Integer) v.getTag();

                //code for single click selection and deselection
                // since the layout implemented is linear list view with single choice so only one item will be selected at a time.
                lastcheck = (RadioButton) v;

                if (lastcheck.isChecked()) {
                    if (rb.isChecked()) {
                        rb.setChecked(false);
                    }

                }
                Toast.makeText(context, "Selected -> " + dataPojos.get(position).getContact_name() + " " + dataPojos.get(position).getContact_number(), Toast.LENGTH_SHORT).show();
                intent = new Intent(context, NextActivity.class);
                // adding the selected data to bundle to fetch them in next screen
                Bundle bundle = new Bundle();
                bundle.putString("contact_name", dataPojos.get(position).getContact_name());
                bundle.putString("contact_no", dataPojos.get(position).getContact_number());
                intent.putExtras(bundle);
                notifyDataSetChanged();

            }


        });
        // checking and displaying contact name, image and number on screen on basis of their availability.
        // for name
        if (!datapojo.getContact_name().equals("")
                && datapojo.getContact_name() != null) {
            contact_name.setText(datapojo.getContact_name());
        } else {
            contact_name.setText("No Name");
        }

        // for number
        if (datapojo.getContact_number() != null && !datapojo.getContact_number().equals("")) {
            contact_number.setText(
                    datapojo.getContact_number());
        } else {
            contact_number.setText("No Contact Number");
        }

        // for image
        try {
            Bitmap image = null;
            if (!datapojo.getImage_id().equals("") && datapojo.getImage_id() != null) {

                image = BitmapFactory.decodeFile(datapojo.getImage_id());

                if (image != null) {
                    contact_image.setImageBitmap(image);
                } else {
                    image = BitmapFactory.decodeResource(context.getResources(), R.drawable.reddit_icon);
                    contact_image.setImageBitmap(image);
                }
            } else {
                image = BitmapFactory.decodeResource(context.getResources(), R.drawable.reddit_icon);
                contact_image.setImageBitmap(image);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }


    // method for updating list on the basis of text search results
    void updateList(List<DataPojo> newStrings) {
        dataPojos = new ArrayList<>();
        dataPojos.addAll(newStrings);
        notifyDataSetChanged();
    }


}

