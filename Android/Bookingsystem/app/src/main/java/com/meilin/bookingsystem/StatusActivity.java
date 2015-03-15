package com.meilin.bookingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

/**
 * Created by Administrator on 2015/3/11.
 */
public class StatusActivity extends Activity{
//    private EditText timeEt = null;
//    private EditText doctorEt = null;
    @Override
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stutas);
//        timeEt = (EditText) this.findViewById(R.id.timeEt);
//        doctorEt = (EditText) this.findViewById(R.id.doctorEt);
    }
//    @Override
//    public void onClick(View v) {
//        Intent intent = new Intent();
//        String text = editText.getText().toString();
//        intent.putExtra("message", text);
//        //通过Intent对象返回结果，setResult方法
//        setResult(2,intent);
//        finish();//结束当前Acitvity

    public void onClickok1(View view) {
//        Intent intent = new Intent();
//        String strtime = timeEt.getText().toString();
//        String strdoctor = doctorEt.getText().toString();
//        intent.putExtra("timeEt", strtime);
//        intent.putExtra("doctorEt", strdoctor);
//        //通过Intent对象返回结果，setResult方法
//        setResult(2,intent);
//        finish();//结束当前Acitvity
        startActivityForResult(new Intent(StatusActivity.this, EnterActivity.class), 1);
    }

    public void onClickok2(View view) {
        startActivityForResult(new Intent(StatusActivity.this, BookingActivity.class), 1);
    }

}
