package com.edwisor.scrollviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> mVersions = new ArrayList<>();
    private ArrayList<String> mVersionNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        mListView = (ListView) findViewById(R.id.lv_versions);

        mVersions.add("Version 1");
        mVersions.add("Version 2");
        mVersions.add("Version 3");
        mVersions.add("Version 4");
        mVersions.add("Version 5");
        mVersions.add("Version 6");
        mVersions.add("Version 7");


        mImages.add(R.drawable.image1);
        mImages.add(R.drawable.image_2);
        mImages.add(R.drawable.image3);
        mImages.add(R.drawable.image4);
        mImages.add(R.drawable.image4);
        mImages.add(R.drawable.image_2);
        mImages.add(R.drawable.image1);
        mImages.add(R.drawable.image3);


        mVersions.add("Version 8");

        mVersionNames.add("Marshmallow");
        mVersionNames.add("Lollipop");
        mVersionNames.add("Kitkat");
        mVersionNames.add("JellyBean");
        mVersionNames.add("IceCream Sanwich");
        mVersionNames.add("Honeycomb");
        mVersionNames.add("Gingerbread");
        mVersionNames.add("Froyo");


        mListView.setAdapter(new MyAdapter());


    }


    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mVersions.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_row, parent, false);
                holder = new ViewHolder();
                holder.bindView(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.imageView.setBackgroundResource(R.drawable.marsh);
            holder.textView1.setText("The version is: " + mVersions.get(position));
            return convertView;
        }

        class ViewHolder {

            ImageView imageView;
            TextView textView1;
            TextView textView2;

            void bindView(View view) {
                imageView = (ImageView) view.findViewById(R.id.iv_image);
                textView1 = (TextView) view.findViewById(R.id.tv_version_row);
                textView2 = (TextView) view.findViewById(R.id.tv_version_name);
            }

        }
    }
}
