package com.example.allinone;

public class DataPojo  {

    int all_image;
    String location_name,location_type,all_address;

    public DataPojo(int all_image, String location_name, String location_type, String all_address) {
        this.all_image = all_image;
        this.location_name = location_name;
        this.location_type = location_type;
        this.all_address = all_address;
    }

    public int getAll_image() {
        return all_image;
    }

    public String getLocation_name() {
        return location_name;
    }

    public String getLocation_type() {
        return location_type;
    }

    public String getAll_address() {
        return all_address;
    }
}
