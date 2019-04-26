package com.example.readingcontacts;

import java.io.Serializable;

public class DataPojo implements Serializable {

    String contact_name;
    String contact_number;
    String image_id;


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


}
