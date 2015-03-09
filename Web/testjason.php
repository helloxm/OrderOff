<?php 
!extension_loaded('curl') && die('The curl extension is not loaded.');

   function http_post_data_tostring($url, $data_string) {

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);
		curl_setopt($ch, CURLOPT_HTTPHEADER, array(
			'Content-Type: application/json; charset=utf-8',
			'Content-Length: ' . strlen($data_string))
		);
        ob_start();
        curl_exec($ch);
        $return_content = ob_get_contents();
        ob_end_clean();

        $return_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);       
        return array($return_code, $return_content);
    }

//$url  = "http://localhost/summit.php";
//$data = json_encode(array(array('id'=>'677', 'name'=>'ee'), array('id'=>'099', 'name'=>'ee')  )); 

$url  = "http://localhost/register.php";  //用户注册
$data = json_encode( array( 'user_name'=>'aaa', 'card_id'=>'45', 'mobile_number'=>'554','password'=>'123456')  );

//$url  = "http://localhost/login.php"; 
//$data = json_encode( array( 'card_id'=>'0000001','password'=>'123456') );//用户登陆
    
//$url  = "http://localhost/order.php";
//$data = json_encode( array( 'card_id'=>'1','dept_code'=>'2', 'doctor_name'=>'li',
	//	                     'order_time'=>'2015-02-05 ','adjust'=>'1') ); //提交预约

$url  = "http://localhost/querystatus.php";
$data = json_encode( array( 'card_id'=>'1') );  //查询预约状态
    
    
list($return_code, $return_content) = http_post_data_tostring($url, $data);

echo $return_content;
?>