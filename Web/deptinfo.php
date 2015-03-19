<?php 
include("connect.php");


$result=$db->query("SELECT dept_name, dept_code FROM deptinfo");

$arr =  array();
while($row = $result->fetch_assoc()){
    $arr[] =  $row; 
}
//$row = $result->fetch_assoc();
//$data = json_encode( $row ,JSON_UNESCAPED_UNICODE);
$data = json_encode( $arr ,JSON_UNESCAPED_UNICODE);
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