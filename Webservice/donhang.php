<?php
	require "ConnectDatabase.php";
// 	echo "abc";
	$TenKH=$_POST['TenKH'];
	$SoDT=$_POST['SoDT'];
	$DiaChi=$_POST['DiaChi'];
	$Email=$_POST['Email'];
	$query="Insert into donhang values(null, '$TenKH', '$SoDT', '$DiaChi', '$Email')";
	if (mysqli_query($connect,$query))
	{
		echo $connect->insert_id;
	}
	else{
		echo "fail";
	}
?>