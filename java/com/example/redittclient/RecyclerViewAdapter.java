package com.example.redittclient;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolders> {

    DataPojo dataPojo;
    List<DataPojo> rv_list_data_pojo_main_array_list;


    public RecyclerViewAdapter() {
    }


    public RecyclerViewAdapter(List<DataPojo> dataPojos) {
        this.rv_list_data_pojo_main_array_list = dataPojos;
    }


    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View v = layoutInflater.inflate(R.layout.recycler_item_view_layout_new, viewGroup, false);
        ViewHolders xyz = new ViewHolders(v);
        return xyz;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders viewHolder, int position) {

        // first we are fetching from data pojo the position then we are calling its getters
        dataPojo = rv_list_data_pojo_main_array_list.get(position);
        viewHolder.title_heading.setText(dataPojo.getTitle_heading());
        viewHolder.title_subheading.setText(dataPojo.getTitle_sub_heading());
        viewHolder.main_heading.setText(dataPojo.get_main_heading());


    }

    @Override
    public int getItemCount() {
        return rv_list_data_pojo_main_array_list.size();
    }

    public void updateList(List<DataPojo> newList) {
        rv_list_data_pojo_main_array_list = new ArrayList<DataPojo>();//re initialise it
        rv_list_data_pojo_main_array_list.addAll(newList);
        notifyDataSetChanged();
    }

    public void subReddit(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SubReddit.class);  // check for making intent
                v.getContext().startActivity(intent);
            }
        });


    }

    public class ViewHolders extends RecyclerView.ViewHolder {
        TextView title_heading;
        TextView title_subheading;
        TextView main_heading;
        WebView webView;
        TextView sub_reddit, like_count;
        ImageView LikeUp, LikeDown, Share, Comment;

         int flag =0;

        public ViewHolders(@NonNull View itemView) {
            super(itemView);
            title_heading = itemView.findViewById(R.id.title_heading);
            title_subheading = itemView.findViewById(R.id.title_subheading);
            main_heading = itemView.findViewById(R.id.main_heading);
            sub_reddit = itemView.findViewById(R.id.sub_reddit);
            like_count = itemView.findViewById(R.id.link_count);
            LikeUp = itemView.findViewById(R.id.like_up_icon);
            LikeDown = itemView.findViewById(R.id.like_down_icon);
            Share = itemView.findViewById(R.id.share_icon);
            Comment= itemView.findViewById(R.id.comment_icon);



            // browsing sub_reddit
            sub_reddit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SubReddit.class);
                    v.getContext().startActivity(intent);
                }
            });


              //like button functionality
            LikeUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag++;
                    like_count.setText("" + flag);

                }
            });

            LikeDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag--;
                    like_count.setText("" + flag);
                }
            });


            // comment Activity
            Comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),SubReddit.class);
                    v.getContext().startActivity(intent);
                }
            });



            //share button functionality
            Share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Description of  Reddit App");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Reddit App");
                    v.getContext().startActivity(Intent.createChooser(sharingIntent, "Share using"));
                }
            });





        }
    }


}



 /* viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             *//*   Intent intent = new Intent(v.getContext(), SubReddit.class);  // check for making intent
                v.getContext().startActivity(intent);*//*

             subReddit(v);
            }
        });*/
       /* viewHolder.webView.getSettings().setJavaScriptEnabled(true);
        viewHolder.webView.setWebViewClient(new WebViewClient());
        viewHolder.webView.loadData("these data to be fetched from server", "text/html", "UTF-8");*/
// viewHolder.webView.loadUrl("https://www.tutorialspoint.com/android/android_webview_layout.htm");


/**
 * public  Filter getFilter()
 * {
 * return new Filter() {
 *
 * @Override protected FilterResults performFiltering(CharSequence constraint) {
 * String userInput = constraint.toString().toLowerCase();
 * if (userInput.equals(""))
 * {
 * filter_array_list= rv_list_data_pojo_main_array_list;
 * }
 * else
 * {
 * ArrayList<DataPojo> dataPojos_array_list = new ArrayList<DataPojo>();
 * for (DataPojo item: rv_list_data_pojo_main_array_list) {
 * if (item.get_main_heading().toLowerCase().contains(userInput)){
 * dataPojos_array_list.add(item);
 * }
 * <p>
 * }
 * filter_array_list =dataPojos_array_list;
 * }
 * // here we have to return filter result
 * FilterResults filterResults = new FilterResults();
 * //now send the array data to values of filter results
 * filterResults.values=filter_array_list;
 * <p>
 * return filterResults;
 * }
 * @Override protected void publishResults(CharSequence constraint, FilterResults results) {
 * filter_array_list = (List<DataPojo>) results.values;
 * notifyDataSetChanged();
 * }
 * <p>
 * <p>
 * };
 * }
 */

