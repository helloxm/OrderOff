package com.meilin.bookingsystem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

/**
 * Created by meilin on 2015/3/8.
 */
public class OrderActivity extends Activity {

    private EditText classEt= null;
    private EditText doctorEt = null;
    private EditText timedayEt= null;
    private EditText timeEt = null;
    private static int ret_code = 0;
    private ProgressDialog pd;
    private String TAG = "EnterActivity";
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_booking);

        classEt = (EditText)this .findViewById(R.id.classEt_edit);
        doctorEt = (EditText)this .findViewById(R.id.doctorEt_edit);
        timedayEt = (EditText)this .findViewById(R.id.timedayEt_edit);
        timeEt = (EditText)this .findViewById(R.id.timeEt_edit);

        spinner1=(Spinner) findViewById(R.id.Spinner02);
        spinner2=(Spinner) findViewById(R.id.Spinner03);
        spinner3=(Spinner) findViewById(R.id.Spinner04);
        spinner4=(Spinner) findViewById(R.id.Spinner05);
        final String arr1[]=new String[]{
              "妇产科","儿科","内科",
              };
        final String arr2[]=new String[]{
               "001","002","003",
        };
        final String arr3[]=new String[]{
                "2015-03-18","2015-03-19","2015-03-20",
        };
        final String arr4[]=new String[]{
                "10:00am","11:00am","12:00am",
        };
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr1);
        spinner1.setAdapter(arrayAdapter1);
//        Toast.makeText(getApplicationContext(), "main Thread"+spinner1.getItemIdAtPosition(spinner1.getSelectedItemPosition()), Toast.LENGTH_LONG).show();
        //注册事件
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner=(Spinner) parent;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr2);
        spinner2.setAdapter(arrayAdapter2);
       // Toast.makeText(getApplicationContext(), "main Thread"+spinner2.getItemIdAtPosition(spinner2.getSelectedItemPosition()), Toast.LENGTH_LONG).show();
        //注册事件
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner=(Spinner) parent;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr3);
        spinner3.setAdapter(arrayAdapter3);
       // Toast.makeText(getApplicationContext(), "main Thread"+spinner3.getItemIdAtPosition(spinner3.getSelectedItemPosition()), Toast.LENGTH_LONG).show();
        //注册事件
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner=(Spinner) parent;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arr4);
        spinner4.setAdapter(arrayAdapter4);
      //  Toast.makeText(getApplicationContext(), "main Thread"+spinner4.getItemIdAtPosition(spinner4.getSelectedItemPosition()), Toast.LENGTH_LONG).show();
        //注册事件
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner=(Spinner) parent;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }
    public void onClickok1(View view) {

            startActivityForResult(new Intent(OrderActivity.this, QuerystatusActivity.class), 1);
        }


    public void onClickok2(View view) {
      SumbitData();
    }

    public boolean  SumbitData()
    {
        final String strclass = classEt.getText().toString();
        final String strdoctor = doctorEt.getText().toString();
        final String strtime = timeEt.getText().toString();
        final String strtimeday = timedayEt.getText().toString();
        if (strclass.isEmpty() || strdoctor.isEmpty()|| strtime.isEmpty() || strtimeday.isEmpty())  {

            Toast.makeText(this, "错误！", Toast.LENGTH_SHORT).show();
            return false;
        }
        pd = ProgressDialog.show(OrderActivity.this, "标题", "加载中，请稍后……");
        new Thread( new Runnable() {
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
                    HttpPost request = new HttpPost(Utils.strOrderURL);
                    // 绑定到请求 Entry
                    StringEntity se = new StringEntity(param.toString());
                    request.setEntity(se);  //设置请求参数setEntity（）
                   //延时
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

                    ret_code = Integer.valueOf(strRetCode).intValue();
                    handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
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
    private Handler handler =new Handler() {
        @Override
        public void handleMessage(Message msg) {// handler接收到消息后就会执行此方法
            super.handleMessage(msg);
            pd.dismiss();// 关闭ProgressDialog
            if(ret_code!=0)
            {
                Toast.makeText(OrderActivity.this, "预约失败！", Toast.LENGTH_SHORT).show();
                Log.e(TAG, " order failed!");
            } else {
                Intent intent = new Intent();
                intent.setClass(OrderActivity.this, QuerystatusActivity.class);
                startActivityForResult(intent, ret_code);
                Toast.makeText(OrderActivity.this, "预约成功！", Toast.LENGTH_SHORT).show();
                Log.e(TAG, " order successfully!");
            }
        }
    };
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

