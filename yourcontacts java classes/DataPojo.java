package com.example.yourcontacts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// model class for fetching contact name, number and image

public class DataPojo implements Serializable {

    String contact_name;
    String contact_number;
    String image_id;
    List<DataPojo> dataPojos_of_contact_list = new ArrayList<>();


    public DataPojo(String contact_name, String contact_number, String image_id) {
        this.contact_name = contact_name;
        this.contact_number = contact_number;
        this.image_id = image_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getImage_id() {
        return image_id;
    }

    @Override
    public String toString() {
        return "DataPojo{" +
                "contact_name='" + contact_name + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", image_id='" + image_id + '\'' +
                ", dataPojos_of_contact_list=" + dataPojos_of_contact_list +
                '}';


    }
}
