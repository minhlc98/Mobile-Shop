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
	$arr=array();
	$query="Select * from sanpham order by ID DESC Limit 10";
	$res=mysqli_query($connect, $query);
	while($row=mysqli_fetch_array($res))
	{
	    $danhgia=0;
	    if ($row['rating_count'] != 0) $danhgia=round($row['total_rating'] / $row['rating_count'],1);
		array_push($arr, new SanPham(
						$row["ID"],
						$row["TenSP"],
						$row["Gia"],
						$row["HinhAnh"],
						$row["MoTa"],
						$danhgia,
						$row['rating_count']
					));
	}
	echo json_encode($arr);
?>
