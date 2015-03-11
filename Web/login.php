<?php 

$json_string = file_get_contents("php://input");
//print_r($json_string);

$json_string=stripslashes($json_string);


$users = json_decode($json_string, true);

$temId = $users['card_id'];
$tempasswd = $users['password'];
$result = $db->query("select * from patientinfo where card_id='$temId' and  password='$tempasswd'");
$num=$result->num_rows;

if(!$num){   						
          		$data = json_encode( array( 'ret_code'=>'1')  );
  	            echo  $data ;		        //µÇÂ½Ê§°Ü
}else{  
				$data = json_encode( array( 'ret_code'=>'0')  );
  	            echo  $data ;               //µÇÂ½³É¹¦
      }
?>