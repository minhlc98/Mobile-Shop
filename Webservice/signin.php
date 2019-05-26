<?php
	include_once "ConnectDatabase.php";
	class User{
		function User($id, $fullName, $acc, $pass, $addr, $email,$countryCode, $phone, $sex, $img){
			$this->id=$id;
			$this->fullName=$fullName;
			$this->acc=$acc;
			$this->pass=$pass;
			$this->addr=$addr;
			$this->email=$email;
			$this->countryCode=$countryCode;
			$this->phone=$phone;
			$this->sex=$sex;
			$this->img=$img;
		}
	}
	$acc=isset($_POST['acc']) ? mysqli_real_escape_string($connect, $_POST['acc']) : "";
	$pass=isset($_POST['pass']) ? mysqli_real_escape_string($connect,$_POST['pass']) : "";
	$query="Select * from nguoidung where taikhoan='$acc' and matkhau='$pass' ";
	$user=null;
	$res=mysqli_query($connect, $query);
	while($row=mysqli_fetch_assoc($res)){
		$user=new User($row['ID'],
						 $row['TenKH'], 
						 $row['taikhoan'],
						 $row['matkhau'], 
						 $row['diachi'], 
						 $row['email'], 
						 $row['maquocgia'],
						 $row['dienthoai'], 
						 ($row['gioitinh']==1) ? true : false, 
						 $row['hinh']
						);
	}
	echo json_encode($user);
	mysqli_close($connect);
?>