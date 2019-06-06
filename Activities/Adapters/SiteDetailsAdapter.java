package com.nrxtechnologies.hoto.Adapters;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nrxtechnologies.hoto.Activities.TicketActivity;
import com.nrxtechnologies.hoto.R;
import com.nrxtechnologies.hoto.utilities.SiteDetails;
import java.util.List;

public class SiteDetailsAdapter extends RecyclerView.Adapter<SiteDetailsAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<SiteDetails> notificationList;
    private ProgressDialog pDialog;
    public SiteDetailsAdapter(Context mCtx, List<SiteDetails> notificationList) {
        this.mCtx = mCtx;
        this.notificationList = notificationList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.site_layout, null);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final SiteDetails notification = notificationList.get(position);
        holder.textViewTicketId.setText(notification.getTicketId());
        holder.textViewPlanedDate.setText(notification.getPlaned_Date());
        holder.textViewSiteName.setText(notification.getSite_Name());
        holder.textViewStatus.setText(notification.getStatus());
        holder.textViewSiteTower.setText(notification.getTower_name());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        view.getContext());
                builder.setTitle(" HOTO ");
                builder.setMessage("Do you want to open "+notification.getSite_Name());
                builder.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast.makeText(view.getContext(),"Cancel to open "+notification.getSite_Name(),Toast.LENGTH_LONG).show();
                            }
                        });
                builder.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                Intent i = new Intent(mCtx, TicketActivity.class);
                                i.putExtra("tower",notification.getTower_name());
                                i.putExtra("ticketid",notification.getTicketId());
                                i.putExtra("hst",notification.getHst());
                                i.putExtra("cname",notification.getCn());
                                i.putExtra("state",notification.getState());
                                i.putExtra("circle",notification.getCicle());
                                i.putExtra("cmp",notification.getCmp());
                                i.putExtra("site",notification.getSite_Name());
                                i.putExtra("id",notification.getSiteid());
                                i.putExtra("sitetype",notification.getTypeofSite());
                                i.putExtra("address",notification.getSiteaddress());
                                i.putExtra("ticket",notification.getTicket());

                                mCtx.startActivity(i);
                            }
                        });
                builder.show();
            }
        });


    }

    @Override
    public int getItemCount() {

        return notificationList.size();
    }

     public void getFilter() {

        return ;
     }


     class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTicketId, textViewPlanedDate, textViewSiteName,textViewStatus,textViewSiteTower;
        LinearLayout cardView;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewTicketId = itemView.findViewById(R.id.ticket_id);
            textViewSiteTower = itemView.findViewById(R.id.site_tower);

            textViewPlanedDate = itemView.findViewById(R.id.date);
            textViewSiteName = itemView.findViewById(R.id.site_id);
            textViewStatus=itemView.findViewById(R.id.status);
            cardView=itemView.findViewById(R.id.linearLayout);
            pDialog = new ProgressDialog(mCtx);
            pDialog.setMessage("Please wait...");

            textViewStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });

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


}
