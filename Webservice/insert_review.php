<?php
    include "ConnectDatabase.php";
    $id_pro=isset($_POST['id_pro']) ? mysqli_real_escape_string($connect,$_POST['id_pro']) : "";
    $id_user=isset($_POST['id_user']) ? mysqli_real_escape_string($connect, $_POST['id_user']): "";
    $rating=isset($_POST['rating']) ? (int)$_POST['rating'] : "";
    $title=isset($_POST['title']) ? mysqli_real_escape_string($connect,$_POST['title']) : "";
    $content=isset($_POST['content']) ? mysqli_real_escape_string($connect,$_POST['content']) : "";
    date_default_timezone_set('Asia/Ho_Chi_Minh');
    $date=date('Y/m/d H:i:s',time());
    if ($id_user != ""){
        $query="insert into danhgia values($id_user, $id_pro, $rating,'$title','$content', '$date')";
        $check=mysqli_query($connect, $query);
        if ($check){
            $query_update="update sanpham 
                        set total_rating = total_rating + $rating, rating_count = rating_count + 1 
                        where ID=$id_pro";
            mysqli_query($connect, $query_update);
            echo "success";
        }
        else{
            echo "error";
        }
        mysqli_close($connect);
    }
    else{
        echo "empty";
    } 
?>