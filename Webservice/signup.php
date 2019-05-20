<?php
	include_once "ConnectDatabase.php";
	$fullName=isset($_POST['fullName']) ? mysqli_real_escape_string($connect, $_POST['fullName']) : "";
	$acc=isset($_POST['acc']) ? mysqli_real_escape_string($connect, $_POST['acc']) : "";
	$pass=isset($_POST['pass']) ? mysqli_real_escape_string($connect, $_POST['pass']) : "";
	$addr=isset($_POST['addr']) ? mysqli_real_escape_string($connect, $_POST['addr']) : "";
	$email=isset($_POST['email']) ? mysqli_real_escape_string($connect, $_POST['email']) : "";
	$countryCode=isset($_POST['countryCode']) ? (int)$_POST['countryCode'] : "" ;
	$phone=isset($_POST['phone']) ? mysqli_real_escape_string($connect, $_POST['phone']) : "";
	$sex=isset($_POST['sex']) ? (int)$_POST['sex'] : "" ;
	
	if ($fullName != "" && $acc != "" && $pass != ""){
		$image="image-user/Blank-profile.png";
		$query="Insert into nguoidung values(null, '$fullName','$acc','$pass','$addr','$email',$countryCode,'$phone',$sex,'$image')";	
		$check=mysqli_query($connect,$query);
		if ($check){
			echo "success";
		}
		else{
			echo "fail";
		}
	}
	else{
		echo "empty";
	}
?>