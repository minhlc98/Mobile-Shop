<?php
	require "ConnectDatabase.php";
	class SanPham{
		function SanPham($id, $tensp, $gia, $hinh, $mota, $danhgia,$slDanhGia)
		{
			$this->ID=$id;
			$this->TenSP=$tensp;
			$this->Gia=$gia;
			$this->Hinh=$hinh;
			$this->MoTa=$mota;
			$this->DanhGia=$danhgia;
			$this->SLDanhGia=$slDanhGia;
		}
	}
	$Name=mysqli_real_escape_string($connect,$_GET['name']);
	$page=(int)$_GET['page'];
	$start=($page-1)*6;
	$query="Select * from sanpham where TenSP like'điện thoại %$Name%' order by ID desc limit $start,6";
	$arr=array();
	$res=mysqli_query($connect,$query);
	while($row=mysqli_fetch_assoc($res))
	{
	    $danhgia=0;
	    if ($row['rating_count'] !=0 ) $danhgia=round($row['total_rating']/$row['rating_count'],1);
		array_push($arr, new SanPham($row['ID'],$row['TenSP'],$row['Gia'],$row['HinhAnh'],$row['MoTa'], $danhgia, $row['rating_count']));
	}
	echo json_encode($arr);
?>