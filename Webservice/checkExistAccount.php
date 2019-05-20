<?php
	include_once "ConnectDatabase.php";
	$account=isset($_GET['account']) ? mysqli_real_escape_string($connect,$_GET['account']) : "";
	if ($account=="") echo "empty";
	else{
		$query="Select * from nguoidung where taikhoan='$account'";
		$res=mysqli_query($connect,$query);
		if (mysqli_num_rows($res)>0) echo "exist";
		else echo "success";
		mysqli_close($connect);
	}
?>