package com.meilin.bookingsystem;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

/**
 * Created by meilin on 2015/3/8.
 */
public class BookingActivity extends Activity {


    private EditText classEt = null;
    private EditText doctorEt = null;
    private EditText timedayEt = null;
    private EditText timeEt = null;
    private String TAG = "EnterActivity";
    private Spinner spinner1;
    private Spinner spinner2;
    private Button dateBtn = null;
    private Button timeBtn = null;
    private EditText et=null;
    private final static int DATE_DIALOG = 0;
    private final static int TIME_DIALOG = 1;
    private Calendar c = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_booking);

        classEt = (EditText) this.findViewById(R.id.classEt_input);
        doctorEt = (EditText) this.findViewById(R.id.doctorEt_input);


        spinner1 = (Spinner) findViewById(R.id.Spinner01);
        spinner2 = (Spinner) findViewById(R.id.Spinner02);
        et = (EditText) findViewById(R.id.et);
        dateBtn = (Button) findViewById(R.id.dateBtn);
        timeBtn = (Button) findViewById(R.id.timeBtn);



        final String arr1[] = new String[]{"外科", "姚", "段", "彭", "其他"

        };


        final String arr2[] = new String[]{"李", "姚", "段", "彭", "其他"

        };

        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr1);


        spinner1.setAdapter(arrayAdapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> cardEt, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) cardEt;
                Toast.makeText(getApplicationContext(), "xxxx" + spinner.getItemAtPosition(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> classEt) {
                Toast.makeText(getApplicationContext(), "没有改变的处理", Toast.LENGTH_LONG).show();
            }

        });

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr2);


        spinner2.setAdapter(arrayAdapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> cardEt, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) cardEt;
                Toast.makeText(getApplicationContext(), "xxxx" + spinner.getItemAtPosition(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> classEt) {
                Toast.makeText(getApplicationContext(), "没有改变的处理", Toast.LENGTH_LONG).show();
            }

        });
        dateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });
        timeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(TIME_DIALOG);
            }
        });


    }
    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        switch (id) {
            case DATE_DIALOG:
                c = Calendar.getInstance();
                dialog = new DatePickerDialog(
                        this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker dp, int year,int month, int dayOfMonth) {
                                et.setText("您选择了：" + year + "年" + (month+1) + "月" + dayOfMonth + "日");
                            }
                        },
                        c.get(Calendar.YEAR), // 传入年份
                        c.get(Calendar.MONTH), // 传入月份
                        c.get(Calendar.DAY_OF_MONTH) // 传入天数
                );
                break;
            case TIME_DIALOG:
                c=Calendar.getInstance();
                dialog=new TimePickerDialog(
                        this,
                        new TimePickerDialog.OnTimeSetListener(){
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                et.setText("您选择了："+hourOfDay+"时"+minute+"分");
                            }
                        },
                        c.get(Calendar.HOUR_OF_DAY),
                        c.get(Calendar.MINUTE),
                        false
                );
                break;
        }
        return dialog;
    }








    public void onClickok1(View view) {
            {
                startActivityForResult(new Intent(BookingActivity.this, StatusActivity.class), 1);
            }
        }

        public void onClickok2(View view) {
            {
                startActivityForResult(new Intent(BookingActivity.this, StatusActivity.class), 1);
            }
        }

        public boolean SumbitData() {
            final String strclass = classEt.getText().toString();
            final String strdoctor = doctorEt.getText().toString();
            final String strtime = timeEt.getText().toString();
            final String strtimeday = timedayEt.getText().toString();

            if (strclass.isEmpty() || strdoctor.isEmpty() || strtime.isEmpty()) {

                Toast.makeText(this, "错误！", Toast.LENGTH_SHORT).show();
                return false;
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String TAG = "one";
                    try {

                        // 先封装一个 JSON 对象，使用post发送数据，创建一盒HttpPost对象
                        JSONObject param = new JSONObject();
//                    PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), nameEt));
//                    out.print(param);

                        param.put("class", strclass);
                        param.put("doctor", strdoctor);
                        param.put("time", strtime);
                        param.put("timeday", strtimeday);

                        HttpPost request = new HttpPost("http://192.168.2.100/");

                        // 绑定到请求 Entry
                        StringEntity se = new StringEntity(param.toString());
                        request.setEntity(se);  //设置请求参数setEntity（）


                        HttpParams connParams = new BasicHttpParams();
                        HttpConnectionParams.setConnectionTimeout(connParams, 5 * 1000);
                        HttpConnectionParams.setSoTimeout(connParams, 5 * 1000);
                        HttpClient client = new DefaultHttpClient(connParams);
                        Log.e(TAG, "SEND");
                        Log.e(TAG, param.toString());
                        HttpResponse httpResponse = client.execute(request);  // 发送请求，返回HttpResponse
                        Log.e(TAG, "retrurn---:" + httpResponse.getStatusLine().getStatusCode());
                        // 得到应答的字符串，这也是一个 JSON 格式保存的数据
                        String retSrc = EntityUtils.toString(httpResponse.getEntity());
                        // 生成 JSON 对象
                        JSONObject result = new JSONObject(retSrc);


                        Log.e(TAG, "return :111" + httpResponse.getStatusLine().getStatusCode());
                        String strRetCode = result.get("ret_code").toString();

                        Log.e(TAG, "return :" + strRetCode);
                        int iRetCode = Integer.valueOf(strRetCode).intValue();
                        if (iRetCode == 0) {
                            Log.e(TAG, "successful");
                        } else {
                            Log.e(TAG, "delfault");
                        }

                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (ClientProtocolException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (JSONException e) {

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }).start();

            return true;
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }


