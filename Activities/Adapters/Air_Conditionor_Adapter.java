package com.nrxtechnologies.hoto.Adapters;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nrxtechnologies.hoto.Activities.TicketActivity;
import com.nrxtechnologies.hoto.R;
import com.nrxtechnologies.hoto.utilities.SiteDetails;

import java.util.List;

public class Air_Conditionor_Adapter extends RecyclerView.Adapter<Air_Conditionor_Adapter.ProductViewHolder> {

   private Context mCtx;
   private List<SiteDetails> notificationList;
   private ProgressDialog pDialog;
   public Air_Conditionor_Adapter(Context mCtx, List<SiteDetails> notificationList) {
       this.mCtx = mCtx;
       this.notificationList = notificationList;
   }


   @Override
   public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       LayoutInflater inflater = LayoutInflater.from(mCtx);
       View view = inflater.inflate(R.layout.ac_layout, null);
       return new ProductViewHolder(view);
   }

   @Override
   public void onBindViewHolder(ProductViewHolder holder, int position) {
       final SiteDetails notification = notificationList.get(position);

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


   class ProductViewHolder extends RecyclerView.ViewHolder {

       TextView textViewTicketId, textViewPlanedDate, textViewSiteName,textViewStatus;
       CardView cardView;
Spinner spinner;
       public ProductViewHolder(View itemView) {
           super(itemView);
           spinner=itemView.findViewById(R.id.ac_name_spinner);
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
