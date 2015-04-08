package com.meilin.bookingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<OrderStatusInfo> statuss;




    public MyAdapter(Context context, ArrayList<OrderStatusInfo> boyFriends){
        this.context=context;
        this.statuss=boyFriends;
    }

    @Override
    public int getCount() {
        return statuss.size();
    }

    @Override
    public Object getItem(int position) {
        return statuss.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder=null;
        if(view==null){
            holder=new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.activity_stutas, null);
//            holder.cardEt=(TextView)view.findViewById(R.id.card_id);
            holder.doctorname=(TextView)view.findViewById(R.id.doctor_name);
            holder.ordertime=(TextView)view.findViewById(R.id.order_time);
            holder.status=(TextView)view.findViewById(R.id.status);

            view.setTag(holder);
        }else{
            holder=(ViewHolder)view.getTag();
        }

//        holder.cardEt.setText(statuss.get(position).getId());
        holder.doctorname.setText(statuss.get(position).getdoctorname());
        holder.ordertime.setText(statuss.get(position).getDate());
        holder.status.setText(statuss.get(position).getstatus());


        return view;
    }

    private class ViewHolder{
//        TextView cardEt;
        TextView doctorname;
        TextView ordertime;
        TextView status;
    }

}





