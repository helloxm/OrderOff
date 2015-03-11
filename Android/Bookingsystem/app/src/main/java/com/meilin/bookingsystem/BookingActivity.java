package com.meilin.bookingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
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
public class BookingActivity extends Activity {


    private EditText classEt= null;
    private EditText doctorEt = null;
    private EditText timedayEt= null;
    private EditText timeEt = null;
    private String TAG = "EnterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_booking);

        classEt = (EditText)this .findViewById(R.id.classEt);
        doctorEt = (EditText)this .findViewById(R.id.doctorEt);
        timedayEt = (EditText)this .findViewById(R.id.timedayEt);
        timeEt = (EditText)this .findViewById(R.id.timeEt);
    }
    public void onClickok1(View view) {

            startActivityForResult(new Intent(BookingActivity.this, StatusActivity.class), 1);
        }


    public void onClickok2(View view) {
        if (SumbitData()) {
            startActivityForResult(new Intent(BookingActivity.this, StatusActivity.class), 1);
        }
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

                    HttpPost request = new HttpPost("http://192.168.2.106/appoint.php");

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

