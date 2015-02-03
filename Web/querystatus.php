<?php 
include("connect.php");

$json_string = file_get_contents("php://input");
$json_string=stripslashes($json_string);

$users = json_decode($json_string, true);
$temId = $users['card_id'];

$result=$db->query("SELECT card_id,order_time,status FROM orderinfo where card_id='$temId'");

$row = $result->fetch_assoc();
$data = json_encode( $row  );
print_r( $data);

//$resultarr = array();
//while($row = $result->fetch_array()){
//$resultarr[] = $row ; }
//$data = json_encode( $resultarr );

//var_dump( $resultarr) ;
//  echo $data;

//$str=preg_replace("#\\\u([0-9a-f]{4})#ie", "iconv('UCS-2BE', 'UTF-8', pack('H4', '\\1'))", $data);
       //中文编码变成json
 //echo $str;
?>