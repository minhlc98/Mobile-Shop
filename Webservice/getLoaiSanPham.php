<?php
	require "ConnectDatabase.php";
	class LoaiSP{
		function LoaiSP($id, $tenSP, $hinh)
		{
			$this->ID=$id;
			$this->TenSP=$tenSP;
			$this->Hinh=$hinh;
		}
	}
	$arr=array();
	$query="Select * from loaisanpham";
	$res=mysqli_query($connect, $query);
	while($row=mysqli_fetch_array($res)){
		array_push($arr, new LoaiSP($row["ID_LoaiSP"], $row["TenSanPham"], $row["HinhAnh"]));
	}
	mysqli_close($connect);
	echo json_encode($arr);
?>