package com.meilin.bookingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

/**
 * Created by Administrator on 2015/3/11.
 */
public class StatusActivity extends Activity{

    @Override
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stutas);}

    public void onClickok1(View view) {
        startActivityForResult(new Intent(StatusActivity.this, EnterActivity.class), 1);
    }

    public void onClickok2(View view) {
        startActivityForResult(new Intent(StatusActivity.this, BookingActivity.class), 1);
    }

}
