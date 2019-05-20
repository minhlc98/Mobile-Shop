<?php
    include_once "ConnectDatabase.php";
    $id=isset($_POST['id']) ? $_POST['id'] : "";
    $new_pass=isset($_POST['newPass']) ? $_POST['newPass'] : "";
    if ($id!=""){
        $query_update="Update nguoidung set matkhau='$new_pass' where id=$id";
        $res_update=mysqli_query($connect,$query_update);
        if ($res_update) echo "success";
        else echo "fail";
    }
    else{
        echo "Empty";
    }


?>