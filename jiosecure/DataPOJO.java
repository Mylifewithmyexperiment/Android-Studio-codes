package com.example.jiosecure;

class DataPOJO
{
    public static int device_imageId;
    public  String device_name;
    public String device_description;


    public int getImageId() {
        return device_imageId;
    }

    public void setImageId(int imageId) {
        this.device_imageId = imageId;
    }

    public String getDevice_name() { return device_name; }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_description() {
        return device_description;
    }

    public void setDevice_description(String device_description) {
        this.device_description = device_description; }

    DataPOJO(int imageIds, String text, String description)
    {
        this.device_imageId = imageIds;
        this.device_name =text;
        this.device_description =description;
    }


}
