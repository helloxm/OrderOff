<?php 
include("connect.php");
include("test3.php");

$json_string = file_get_contents("php://input");

$json_string=stripslashes($json_string);

write_testinfo($json_string);

$users = json_decode($json_string, true);

//print_r($users);
//print_r(  $users->{'cardid'}   );

$temname=$users['user_name'];
$temmobile=$users['mobile_number'];
$tempass = $users['password'];
$temId = $users['card_id'];
$result = $db->query("select * from patientinfo where card_id='$temId' ");

$num=$result->num_rows;

if($num == '0'){   //echo "0";//不存在


$insert = $db->query("insert into patientinfo (user_name,card_id,password,mobile_number)
		 values  ('$temname','$temId','$tempass','$temmobile') ");

    if($insert ){
            	$data = json_encode( array( 'ret_code'=>'0')  );
  	            echo  $data ;     //注册成功
    }else{ 
            	$data = json_encode( array( 'ret_code'=>'1')  );
          	    echo  $data ;     //注册失败 
          }    
}else{         
				$data = json_encode( array( 'ret_code'=>'2')  );
          	    echo  $data ;    //已存在
      }     
?>