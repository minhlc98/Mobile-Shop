<?php
    include_once "ConnectDatabase.php";
    $name=$_GET['name'];
    $sql="SELECT ID FROM `sanpham` WHERE TenSP like 'điện thoại %$name%'";
    $res=mysqli_query($connect,$sql);
    echo mysqli_num_rows($res);
    mysqli_close($connect);
?>