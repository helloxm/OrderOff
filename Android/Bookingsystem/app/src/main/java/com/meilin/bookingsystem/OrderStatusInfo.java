package com.meilin.bookingsystem;

/**
* Created by Administrator on 2015/3/28.
*/
public class OrderStatusInfo {
    //private String strCardId;
    private String strDoctorName;
    private String strDate;
    private String strStatus;

    public  OrderStatusInfo(String strDoctorName,String strDate,String strStatus)
    {
       // this.strCardId=strCardId;
        this.strDoctorName=strDoctorName;
        this.strDate=strDate;
        this.strStatus=strStatus;
    }
//    public String getCardId(){
//        return strCardId;
//    }
    public String getDoctorName(){
        return strDoctorName;
    }
    public String getDate(){
        return strDate;
    }
    public String getStatus(){
        return strStatus;
    }
}
