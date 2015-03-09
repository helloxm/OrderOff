<?php 

function write_testinfo($contents)
{
	$fp=fopen("test.txt","w");
	if(!$fp)
	{
	    echo'open failed exit!';
	    exit;
	}
	
	fwrite($fp,$contents);
	fclose($fp);
}
?>