<?php 
include("connect.php");

$json_string = file_get_contents("php://input");
//print_r($json_string);

$json_string=stripslashes($json_string);
$users = json_decode($json_string, true);

$temId = $users['card_id'];
$temdept_code = $users['dept_code'];
$temdoctor_code = $users['doctor_code'];
$temdoctor_name = $users['doctor_name'];
$temorder_date = $users['order_date'];
$temorder_time = $users['order_time'];
$temadjust = $users['adjust'];

$result = $db->query("select * from patientinfo where card_id='$temId' ");
$num=$result->num_rows;
//echo $num;

if(!$num){                        //不存在   	
     			$data = json_encode( array( 'ret_code'=>'2')  );
  	            echo  $data ;    //预约失败
}else{                         //已存在
     $insert = $db->query("insert into orderinfo (card_id,dept_code,doctor_code,doctor_name,order_date, order_time,adjust)
        	 values ('$temId','$temdept_code', '$temdoctor_code','$temdoctor_name','$temorder_date', '$temorder_time','$temadjust') " );
      if($insert){ 
      	              $data = json_encode( array( 'ret_code'=>'0')  );
  	                  echo  $data ;          //预约成功
        }else{   
       	              $data = json_encode( array( 'ret_code'=>'1')  );
  	                  echo  $data ;          //预约失败
              }
      }

?>