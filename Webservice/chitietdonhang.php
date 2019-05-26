<?php
	require "ConnectDatabase.php";
	$json=$_POST['json'];
	$data=json_decode($json,true);
	foreach ($data as $value) {
		$id_donhang=mysqli_real_escape_string($connect,$value['ID_DonHang']);
		$id_sanpham=mysqli_real_escape_string($connect,$value['ID_SanPham']);
		$gia=$value['Gia'];
		$soluong=$value['SoLuong'];
		$query="Insert into chitietdonhang values(null, $id_donhang,$id_sanpham, $gia, $soluong)";
		$check=mysqli_query($connect, $query);
	}
	if ($check){
		echo "Success";
	}
	else{ 
		echo "Fail";
	}
	mysqli_close($connect);
?>