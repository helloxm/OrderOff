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


public class RegisterActivity extends Activity {
    private EditText mobileEt = null;
    private EditText cardidEt = null;
    private EditText nameEt = null;
    private EditText passwordEt1 = null;
    private EditText passwordEt2 = null;
    private static int ret_code = 0;
    private ProgressDialog pd;
    private String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        mobileEt = (EditText)this .findViewById(R.id.mobleEt);
        cardidEt = (EditText)this .findViewById(R.id.cardidEt);
        nameEt = (EditText)this .findViewById(R.id.nameEt);
        passwordEt1 = (EditText)this .findViewById(R.id.passwordEt1);
        passwordEt2 = (EditText)this .findViewById(R.id.passwordEt2);
    }
    public void onClickok1(View view) {
        startActivityForResult(new Intent(RegisterActivity.this, LoginActivity.class), 1);
    }

public void onClickok2(View view) {
    if (SumbitData()) {
        Intent intent = new Intent();
        intent.setClass(RegisterActivity.this, LoginActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);//将Bundle添加到Intent,也可以在Bundle中添加相应数据传递给下个页面,例如：bundle.putString("abc", "bbb");
        startActivityForResult(intent, 0);      // 跳转并要求返回值，0代表请求值(可以随便写)
    }
}
    public boolean  SumbitData()
    {
        final String strMobile = mobileEt.getText().toString();
        final String strCard = cardidEt.getText().toString();
        final String strName = nameEt.getText().toString();
        final String strPassword1 = passwordEt1.getText().toString();
        final String strPassword2 = passwordEt2.getText().toString();
        if (strMobile.isEmpty() || strMobile.length() < 11 || strCard.isEmpty()|| strName.isEmpty()
                ||strPassword1.isEmpty() ||strPassword2.isEmpty()||!strPassword1.equalsIgnoreCase(strPassword2))  {
            Toast.makeText(this, "联系方式错误！", Toast.LENGTH_SHORT).show();
            return false;
        }
        pd = ProgressDialog.show(RegisterActivity.this, "标题", "加载中，请稍后……");
        new Thread( new Runnable() {
            @Override
            public void run() {
                String TAG = "one";
                try {
                    // 先封装一个 JSON 对象，使用post发送数据，创建一盒HttpPost对象
                    JSONObject param = new JSONObject();
//                    PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), nameEt));
//                    out.print(param);
                    param.put("user_name", strName);
                    param.put("card_id", strCard);
                    param.put("password1", strPassword1);
                    param.put("password2", strPassword2);
                    param.put("mobile_number", strMobile);
                    HttpPost request = new HttpPost(Utils.strRegisterURL);
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
                Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                Log.e(TAG, " register failed!");
            } else {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivityForResult(intent, ret_code);
                Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                Log.e(TAG, " register successfully!");
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
