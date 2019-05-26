<?php
	require "ConnectDatabase.php";
	class KiemTra{
		function KiemTra($tenSP, $soluong, $gia)
		{
			$this->TenSP=$tenSP;
			$this->SoLuong=$soluong;
			$this->Gia=$gia;
		}
	}
	$ID_DonHang=$_POST['ID_DonHang'];
	$tenKH=$_POST['TenKH'];
	$arr=array();
	$query="select sp.TenSP, ct.SoLuong, sp.Gia from (select ID_DonHang from donhang where ID_DonHang=$ID_DonHang and TenKH='$tenKH') dh join (select ID_DonHang,ID_TenSanPham, SoLuong from chitietdonhang WHERE ID_DonHang=$ID_DonHang) ct on dh.ID_DonHang=ct.ID_DonHang join sanpham sp on ct.ID_TenSanPham=sp.ID";
	$res=mysqli_query($connect, $query);
	while($row=mysqli_fetch_assoc($res)){
		array_push($arr, new KiemTra($row['TenSP'],$row['SoLuong'],$row['Gia']));
	}
	echo json_encode($arr);
	mysqli_close($connect);
?>