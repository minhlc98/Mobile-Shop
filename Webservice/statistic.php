<?php
    include "ConnectDatabase.php";
    class Statistic{
        function Statistic(){
            $this->star=0;
            $this->statistic=0;
        }
        // function Statistic($star,$rate){
        //     $this->star=$star;
        //     $this->statistic=$rate;
        // }
    }
    $id_pro=isset($_GET['id_pro']) ? $_GET['id_pro'] : "";
    if ($id_pro != ""){
        $query="Select danh_gia,count(*) as rate  from danhgia where ID_SanPham = $id_pro group by danh_gia";
        $default=
        $arr=array(new Statistic(),new Statistic(),new Statistic(),new Statistic(),new Statistic());
        $res=mysqli_query($connect, $query);
        while($row=mysqli_fetch_assoc($res))
    	{
    	    //star tương ứng với index trong mảng
    	    //star=3 ứng với $arr[2]
    	    $i=$row['danh_gia']-1;
    	    $arr[$i]->star=$i+1;
    	    $arr[$i]->statistic=$row['rate'];
    		//array_push($arr,new Statistic($row['danh_gia'], $row['rate']));
        }
      
        echo json_encode($arr);
    }
?>