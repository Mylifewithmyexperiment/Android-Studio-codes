package com.nrxtechnologies.hoto.utilities;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nrxtechnologies.hoto.R;

import java.io.File;
import java.io.IOException;
import java.util.List;
public class Site_Details_Adapter extends RecyclerView.Adapter<Site_Details_Adapter.ProductViewHolder> {

    private Context mCtx;
    private List<SiteDetails> siteList;
    private ProgressDialog pDialog;
    public Site_Details_Adapter(Context mCtx, List<SiteDetails> siteList) {
        this.mCtx = mCtx;
        this.siteList = siteList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.site_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        SiteDetails site = siteList.get(position);
        holder.textViewTicketid.setText(site.getTicketId());
        holder.textViewPlaneddate.setText(site.getPlaned_Date());
        holder.textViewSitename.setText(site.getTicketId());
        holder.textWiewStatus.setText(site.getStatus());


    }

    @Override
    public int getItemCount() {

        return siteList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTicketid, textViewPlaneddate, textViewSitename,textWiewStatus;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewTicketid = itemView.findViewById(R.id.ticket_id);
            textViewPlaneddate = itemView.findViewById(R.id.date);
            textViewSitename = itemView.findViewById(R.id.site_id);
            textWiewStatus = itemView.findViewById(R.id.status);

        }

    }

        private void showpDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hidepDialog() {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }


    }
