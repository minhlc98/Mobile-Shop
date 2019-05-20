<?php
	require "ConnectDatabase.php";
	class SanPham{
		function SanPham($id, $tensp, $gia, $hinh, $mota, $danhgia, $slDanhGia)
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
	$ID_LoaiSP=(int)$_GET['id'];
	$page=(int)$_GET['page'];
	$space=6;
	$start=($page-1) * $space;
	$arr=array();
	$query="Select * from sanpham where ID_LoaiSP='$ID_LoaiSP' order by ID desc limit $start, $space";
	$res=mysqli_query($connect, $query);
	while($row=mysqli_fetch_assoc($res))
	{
	    $danhGia=0;
	    if ($row['rating_count'] != 0) $danhGia=round($row['total_rating'] / $row['rating_count'],1);
		array_push($arr, new SanPham(
						$row["ID"],
						$row["TenSP"],
						$row["Gia"],
						$row["HinhAnh"],
						$row["MoTa"],
						$danhGia,
						$row['rating_count']
					));
	}
	echo json_encode($arr);
?>