<?php
    include_once "ConnectDatabase.php";
    date_default_timezone_set('Asia/Ho_Chi_Minh');
    class Review{
        function Review($image, $name, $rating, $title, $content, $date){
            $this->image=$image;
            $this->name=$name;
            $this->rating=$rating;
            $this->title=$title;
            $this->content=$content;
            $this->date=$date;
        }
    }
    function get_time_ago( $time ){
        $time_stamp = strtotime($time);
        $time_difference = time() - $time_stamp;
        if( $time_difference < 1 ) { return '1 giây'; }
        $condition = array( 31104000 =>  'năm',
                    2592000          =>  'tháng',
                    86400           =>  'ngày',
                    3600             =>  'giờ',
                    60               =>  'phút',
                    1                =>  'giây'
        );

        foreach( $condition as $secs => $str )
        {
            $d = $time_difference / $secs;
            if( $d >= 1 )
            {
                $t = round( $d );
                return  $t . ' ' . $str. ' trước';
            }
        }
    }
    $id_pro=isset($_GET['id_pro']) ? (int)$_GET['id_pro'] : "";
    if ($id_pro != ""){
        $arr=array();
        $query="select u.hinh,u.TenKH, r.danh_gia, r.tieude, r.noidung, r.thoigian
                from nguoidung u join danhgia r on u.ID=r.ID_NguoiDung
                where ID_SanPham=$id_pro order by r.thoigian desc" ;
        $res=mysqli_query($connect, $query);
        while($row=mysqli_fetch_assoc($res)){
            array_push($arr, new Review(
                            $row["hinh"],
                            $row["TenKH"],
                            $row["danh_gia"],
                            $row["tieude"],
                            $row["noidung"],
                            get_time_ago($row["thoigian"])
                        ));
        }
        echo json_encode($arr);
    }



?>