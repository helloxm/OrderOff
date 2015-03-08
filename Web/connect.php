<?php 
$username="root";
$userpass="123456";
$dbhost="localhost";
$dbdatabase="offorder";

$db=new mysqli($dbhost,$username,$userpass,$dbdatabase);
if(mysqli_connect_error()){
	echo 'Could not connect to database.';
	exit;
}
mysqli_query($db,"SET NAMES 'utf8'");



?>