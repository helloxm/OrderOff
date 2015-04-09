package com.meilin.bookingsystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/**
* Created by Administrator on 2015/3/29.
*/
    public class MyAdapt extends BaseAdapter {
    private ArrayList<String> lists;
        private Context context;
        private ArrayList<OrderStatusInfo> orderStatusInfoArr;

        public MyAdapt(Context context,ArrayList<OrderStatusInfo> orderStatusInfoArr){
            this.context=context;
            this.orderStatusInfoArr=orderStatusInfoArr;
        }
        @Override
        public int getCount() {
            return orderStatusInfoArr.size();
        }
        @Override
        public Object getItem(int position) {
            return orderStatusInfoArr.get(position);
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
       @Override
        public View getView(int position, View view, ViewGroup parent) {

            ViewHolder holder=new ViewHolder();
            if(view==null){
                holder=new ViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.order_info_status_items, null);
               // holder.tvCardId=(TextView)view.findViewById(R.id.CardId);
                holder.tvDoctorName=(TextView)view.findViewById(R.id.DoctorName);
                holder.tvDate=(TextView)view.findViewById(R.id.Ordertime);
                holder.tvStatus=(TextView)view.findViewById(R.id.status);
                view.setTag(holder);
            }else{
                holder=(ViewHolder)view.getTag();
            }
          // holder.tvCardId.setText(orderStatusInfoArr.get(position).getCardId());
            holder.tvDoctorName.setText(orderStatusInfoArr.get(position).getDoctorName());
            holder.tvDate.setText(orderStatusInfoArr.get(position).getDate());
            holder.tvStatus.setText(orderStatusInfoArr.get(position).getStatus());
            return view;
        }
        private class ViewHolder{
            //TextView tvCardId;
            TextView tvDoctorName;
            TextView tvDate;
            TextView tvStatus;
        }

    }

