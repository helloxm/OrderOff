package com.meilin.bookingsystem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
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
 * Created by Administrator on 2015/3/11.
 */
public class QuerystatusActivity extends Activity{
public SQLiteDatabase m_db= null;
    public ListView textview = null;
    private static int ret_code = 0;
    private ProgressDialog pd;
//    private TextView order_date = null;
//    private TextView doctor_name = null;
//    private TextView status = null;
    private TextView card_id=null;
    private String TAG = "QuerystatusActivity";
    @Override
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stutas);

//        order_date = (TextView)this .findViewById(R.id.order_date);
//        doctor_name = (TextView)this .findViewById(R.id.doctor_name);
//        status = (TextView)this .findViewById(R.id.statusEt);
        card_id = (TextView)this .findViewById(R.id.card_id);
        SubmitData();
    }
    public boolean SubmitData() {
//        final String strTime = order_date.getText().toString();
//        final String strDoctor= doctor_name.getText().toString();
//        final String strStatus = status.getText().toString();
        final String strCardid = card_id.getText().toString();

    pd = ProgressDialog.show(QuerystatusActivity.this, "标题", "信息加载中，请稍后……");
    new Thread(new Runnable() {
        @Override
        public void run() {
            String TAG = "one";
            try {
                // 先封装一个 JSON 对象，使用post发送数据，创建一盒HttpPost对象
                JSONObject param = new JSONObject();
//                    PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), nameEt));
//                    out.print(param);
//                param.put("order_date", strTime);
//                param.put("doctor_name", strDoctor);
//                param.put("status", strStatus);
                param.put("card_id", strCardid);
                HttpPost request = new HttpPost(Utils.strQuerystatusURL);
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
                Log.e(TAG, "retrurn---:" + retSrc);
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
            Toast.makeText(QuerystatusActivity.this, "加载失败！", Toast.LENGTH_SHORT).show();
            Log.e(TAG, " load failed!");
        } else {
//            Intent intent = new Intent();
//            intent.setClass(QuerystatusActivity.this, QuerystatusActivity.class);
//            startActivityForResult(intent, cardid);
            showInfo();
            Toast.makeText(QuerystatusActivity.this, "加载成功！", Toast.LENGTH_SHORT).show();
            Log.e(TAG, " load successfully!");
        }
    }
};
    public void onClickok1(View view) {
        startActivityForResult(new Intent(QuerystatusActivity.this, LoginActivity.class), 1);
    }
    public void onClickok2(View view) {
        startActivityForResult(new Intent(QuerystatusActivity.this, OrderActivity.class), 1);
    }
    public void showInfo()
    {
        Cursor httpResponse = m_db.rawQuery("card_id,doctor_name,order_date,order_time,statusEt",null);
        String[] dataColumns = {"card_id","doctor_name","order_time","order_date","statusEt" } ;
        int[] viewIDs = new int[]{R.id.card_id,R.id.order_date,R.id.order_time,R.id.doctor_name,R.id.statusEt};
        SimpleCursorAdapter adapter
                = new SimpleCursorAdapter(
                textview.getContext(),                             // The Context for the ListView
                R.layout.activity_stutas,          // Points to the XML for a list item
                httpResponse,                           // The cursor to get items from
                dataColumns,
                viewIDs
        );
        textview.setAdapter(adapter);
    }
}
