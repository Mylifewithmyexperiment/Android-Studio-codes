package com.example.redittclient;

public class DataPojo {

    public int image_id;
    public String title_heading;
    public String title_sub_heading;
    public String main_heading;

    DataPojo(int image_id, String title_heading, String title_sub_heading, String heading) {
        this.image_id = image_id;
        this.title_heading = title_heading;
        this.title_sub_heading = title_sub_heading;
        this.main_heading = heading;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getTitle_heading() {
        return title_heading;
    }

    public void setTitle_heading(String title_heading) {
        this.title_heading = title_heading;
    }



    public String getTitle_sub_heading() {
        return title_sub_heading;
    }

    public void setTitle_sub_heading(String title_sub_heading) {
        this.title_sub_heading = title_sub_heading;
    }

    public String get_main_heading() {
        return main_heading;
    }

    public void setMain_heading(String main_heading) {
        this.main_heading = main_heading;
    }

    @Override
    public String toString() {
        return "DataPojo{" +
                "image_id=" + image_id +
                ", title_heading='" + title_heading + '\'' +
                ", title_sub_heading='" + title_sub_heading + '\'' +
                ", main_heading='" + main_heading + '\'' +
                '}';
    }
}
