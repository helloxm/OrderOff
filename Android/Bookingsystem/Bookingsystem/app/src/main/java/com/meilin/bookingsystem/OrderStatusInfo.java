package com.meilin.bookingsystem;

/**
 * Created by meilin on 2015/3/28.
 */
public class OrderStatusInfo {
//    public String strcardEt;
//    public String strdoctorname;
//    public String strordertime;
//    public String strstatus;
//    private String card_id;
    private String doctor_name;
    private String order_time;
    private String status;

    public OrderStatusInfo( String doctor_name, String order_time, String status) {

        this.doctor_name = doctor_name;
        this.order_time= order_time;
        this.status = status;
    }

//    public String getId() {
//        return card_id;
//    }



    public String getdoctorname() {
        return doctor_name;
    }


    public String getDate() {
        return order_time;
    }


    public String getstatus() {
        return status;
    }


}

