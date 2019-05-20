<?php
    include_once "ConnectDatabase.php";
    $oldImage=isset($_POST['oldImage']) ? mysqli_real_escape_string($connect,$_POST['oldImage']) : "";
    $newImage=isset($_POST['newImage']) ? mysqli_real_escape_string($connect,$_POST['newImage']) : "";
    $name=isset($_POST['name']) ? mysqli_real_escape_string($connect,$_POST['name']) : "";
    $address=isset($_POST['address']) ? mysqli_real_escape_string($connect,$_POST['address']) : "";
    $email=isset($_POST['email']) ? mysqli_real_escape_string($connect,$_POST['email']) : "";
    $phone=isset($_POST['phone']) ? mysqli_real_escape_string($connect,$_POST['phone']) : "";
    $countryCode=isset($_POST['countryCode']) ? (int)$_POST['countryCode'] : "";
    $sex=isset($_POST['sex']) ? (int)$_POST['sex'] : "";
    $ID=isset($_POST['ID']) ? (int)$_POST['ID'] : "";
    if ($oldImage!="" && $newImage!=""){
        $sql="update nguoidung 
                set hinh='$newImage', TenKH='$name', diachi='$address', email='$email', dienthoai='$phone', maquocgia=$countryCode, gioitinh=$sex
                where ID=$ID";
        $check=mysqli_query($connect, $sql);
        if ($check){
            if ($oldImage != "image-user/Blank-profile.png") unlink($oldImage);
            echo "success";
        }
        else{
            echo "fail";
        }
    }
?>