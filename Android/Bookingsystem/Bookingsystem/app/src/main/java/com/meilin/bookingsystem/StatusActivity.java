package com.meilin.bookingsystem;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meilin on 2015/3/9.
 */



/**
 * Created by Administrator on 2015/1/27.
 */
public class StatusActivity extends Activity {
    private static int ret_code=0;
    private ProgressDialog pd;
    private String TAG = "StatusActivity";
    private ListView listView;
    private ArrayList<OrderStatusInfo> statuss=new ArrayList<>();
    private MyAdapter adapter;



    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        initData();

        listView = (ListView) this.findViewById(R.id.listView2);
        adapter = new MyAdapter(StatusActivity.this, statuss);
        listView.setAdapter(adapter);
    }
    private void initData(){
        statuss.add(new OrderStatusInfo( "textdoctor","2015320","0"));
        statuss.add(new OrderStatusInfo( "textdoctor","2015321","1"));
    }

}


//
//        cardEt = (TextView) this.findViewById(R.id.card_id);
//        doctorname = (TextView) this.findViewById(R.id.doctor_name);
//
//        ordertime = (TextView) this.findViewById(R.id.order_time);
//        status = (TextView) this.findViewById(R.id.status);
//
//
//
//
//
//
// private void initData(){
//        final String strCard = cardEt.getText().toString();
//        final String strdoctorname = doctorname.getText().toString();
//        final String strorderdate = orderdate.getText().toString();
//        final String strordertime = ordertime.getText().toString();
//        final String strstatus = status.getText().toString();
//
//    pd = ProgressDialog.show(StatusActivity.this, "标题", "加载中，请稍后……");
//        new Thread( new Runnable() {
//            @Override
//    public void run() {
//                String TAG = "one";
//
//
//                try {
//
//                    // 先封装一个 JSON 对象，使用post发送数据，创建一盒HttpPost对象
//        JSONArray person = new JSONArray();
//        JSONObject param = new JSONObject();
//                 PrintWriter out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), nameEt));
//                out.print(param)
//                param.put("card_id", strCard);
//           param.put("doctor_name", strdoctorname);
//               param.put("order_date", strorderdate);
//                 param.put("order_time", strordertime);
//                param.put("status", strstatus);
//
//
//                    HttpPost request = new HttpPost("http://http://192.168.2.100/");
//                    // 绑定到请求 Entry
//                    StringEntity se = new StringEntity(param.toString());
//                    request.setEntity(se);  //设置请求参数setEntity（）
//                    HttpParams connParams = new BasicHttpParams();
//                    HttpConnectionParams.setConnectionTimeout(connParams, 5 * 1000);
//                    HttpConnectionParams.setSoTimeout(connParams, 5 * 1000);
//                    HttpClient client = new DefaultHttpClient(connParams);
//                    Log.e(TAG, "SEND");
//                    Log.e(TAG, param.toString());
//                    HttpResponse httpResponse = client.execute(request);  // 发送请求，返回HttpResponse
//                    Log.e(TAG, "retrurn---:" + httpResponse.getStatusLine().getStatusCode());
//                    // 得到应答的字符串，这也是一个 JSON 格式保存的数据
//                    String retSrc = EntityUtils.toString(httpResponse.getEntity());
//                    // 生成 JSON 对象
//                    JSONObject result = new JSONObject(retSrc);
//                    Log.e(TAG, "return :111" + httpResponse.getStatusLine().getStatusCode());
//                    String strRetCode = result.get("ret_code").toString();
//                    Log.e(TAG, "return :" + strRetCode);
//                    ret_code = Integer.valueOf(strRetCode).intValue();
//                    handler.sendEmptyMessage(0);// 执行耗时的方法之后发送消给handler
//                } catch (UnsupportedEncodingException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (ClientProtocolException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (JSONException e) {
//
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//}).start();
//
//        }
//
//
//
//    Handler handler = new Handler() {
//@Override
//public void handleMessage(Message msg) {
//        pd.dismiss();// 关闭ProgressDialog
//        if (ret_code == 0)
//        {
//
//        } else
//
//        {
//        Toast.makeText(StatusActivity.this, "错误！", Toast.LENGTH_SHORT).show();
//        Log.e(TAG, "delfault");
//        }
//        }
//        };
//
////
////        //创建SimpleCursorAdapter适配器将数据绑定到item显示控件上

        //条目点击事件


//         Cursor c =m_db.rawQuery("select _id, doctorname,oederdate,ordertime,status from person",null);
//
//
//
//        String[] dataColumns = {"card_id","doctorname","orderdate","ordertime", "status"} ;
//        Log.e(TAG,"begin");
//
//        int[] viewIDs = new int[]{R.id.card_id,R.id.doctor_name, R.id.order_date, R.id.order_time, R.id.status};
//
//        // Creates the backing adapter for the ListView.
//        SimpleCursorAdapter adapter
//                = new SimpleCursorAdapter(
//                listview.getContext(),                             // The Context for the ListView
//                R.layout.activity_stutas,          // Points to the XML for a list item
//                c,                           // The cursor to get items from
//                dataColumns,
//                viewIDs
//        );
//
//        listview.setAdapter(adapter);
//    //条目点击事件
//    listview.setOnItemClickListener(new ItemClickListener());
//}
//
//private final class ItemClickListener implements AdapterView.OnItemClickListener {
//
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        ListView listView = (ListView) parent;
//        Cursor cursor = (Cursor) listView.getItemAtPosition(position);
//        String personid = String.valueOf(cursor.getInt(cursor.getColumnIndex("_id")));
//




//            public void onClickok1(View view) {
//        {
//            startActivityForResult(new Intent(StatusActivity.this, EnterActivity.class), 1);
//        }
//    }
//
//    public void onClickok2(View view) {
//        {
//            startActivityForResult(new Intent(StatusActivity.this, BookingActivity.class), 1);
//        }
//
//
//    }
//}