package com.nrxtechnologies.hoto.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.nrxtechnologies.hoto.Activities.ABCDDCDB_Activity;
import com.nrxtechnologies.hoto.Activities.AirConditionerActivity;
import com.nrxtechnologies.hoto.Activities.BatterySet_Activity;
import com.nrxtechnologies.hoto.Activities.DieselGenerator_Activity;
import com.nrxtechnologies.hoto.Activities.ElectricActivity;
import com.nrxtechnologies.hoto.Activities.External_Tenants_Personal_Detail_Activity;
import com.nrxtechnologies.hoto.Activities.GeneralSafety_Activity;
import com.nrxtechnologies.hoto.Activities.OutdoorCabinet_Activity;
import com.nrxtechnologies.hoto.Activities.PowerManagementSystem_Activity;
import com.nrxtechnologies.hoto.Activities.ServoStabilizer_Activity;
import com.nrxtechnologies.hoto.Activities.Shelter_Activity;
import com.nrxtechnologies.hoto.Activities.SiteDetailsActivity;
import com.nrxtechnologies.hoto.Activities.TicketActivity;
import com.nrxtechnologies.hoto.Activities.TowerDetailsActivity;
import com.nrxtechnologies.hoto.PowerPlant_Activity;
import com.nrxtechnologies.hoto.R;
import com.nrxtechnologies.hoto.utilities.Ticket_List;

public class Ticket_List_Adapter extends RecyclerView.Adapter<Ticket_List_Adapter.ViewHolder>{
private Ticket_List[] listdata;
public Ticket_List_Adapter(Ticket_List[] listdata) {
    this.listdata = listdata;


}
@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.ticketdetails_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
        }

@Override
public void onBindViewHolder(ViewHolder holder, final int position) {
final Ticket_List myListData = listdata[position];
        holder.textView.setText(listdata[position].getTitle());
    holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
           // final Intent intent;
                            switch (position){

                                case 0:
                                    Intent intent =new Intent(v.getContext(), TowerDetailsActivity.class);
                                    intent.putExtra("tower", TicketActivity.Tower);
                                    intent.putExtra("tid", TicketActivity.Transactionid);

                                    v.getContext().startActivity(intent);
                                    break;

                                case 1:
                                    Intent i1=new Intent(v.getContext(), ElectricActivity.class);
                                    v.getContext().startActivity(i1);
                                    break;

                                case 2:
                                    Intent i2=new Intent(v.getContext(), AirConditionerActivity.class);
                                    v.getContext().startActivity(i2);
                                    break;
                                case 3:
                                    Intent i3=new Intent(v.getContext(), PowerPlant_Activity.class);
                                    v.getContext().startActivity(i3);
                                    break;
                                case 4:
                                    Intent i4=new Intent(v.getContext(), DieselGenerator_Activity.class);
                                    v.getContext().startActivity(i4);
                                    break;
                                case 5:
                                    Intent i5=new Intent(v.getContext(), Shelter_Activity.class);
                                    v.getContext().startActivity(i5);
                                    break;
                                case 6:
                                    Intent i6=new Intent(v.getContext(), OutdoorCabinet_Activity.class);
                                    v.getContext().startActivity(i6);
                                    break;
                                case 7:
                                    Intent i7=new Intent(v.getContext(), BatterySet_Activity.class);
                                    v.getContext().startActivity(i7);
                                    break;
                                case 8:
                                    Intent i8=new Intent(v.getContext(), External_Tenants_Personal_Detail_Activity.class);
                                    v.getContext().startActivity(i8);
                                    break;
                                case 9:
                                    Intent i9=new Intent(v.getContext(), PowerManagementSystem_Activity.class);
                                    v.getContext().startActivity(i9);
                                    break;

                                case 10:
                                    Intent i10=new Intent(v.getContext(), GeneralSafety_Activity.class);
                                    v.getContext().startActivity(i10);
                                    break;

                                case 11:
                                    Intent i11=new Intent(v.getContext(), ABCDDCDB_Activity.class);
                                    v.getContext().startActivity(i11);
                                    break;

                                case 12:
                                    Intent i12=new Intent(v.getContext(), ServoStabilizer_Activity.class);
                                    v.getContext().startActivity(i12);
                                    break;

                            }
                             }
                                });
        }


@Override
public int getItemCount() {
        return listdata.length;
        }

public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public CardView relativeLayout;

    public ViewHolder(View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.item_title);
        relativeLayout = (CardView)itemView.findViewById(R.id.card_view);

    }
}
}

