<?php
    include_once "ConnectDatabase.php";
    class QuangCao{
        function QuangCao($url){
            $this->url=$url;
        }
    }
    $arr=array();
	$query="Select URL_QuangCao from quangcao";
	$res=mysqli_query($connect, $query);
	while($row=mysqli_fetch_assoc($res))
	{
		array_push($arr, new QuangCao($row['URL_QuangCao']));
	}
	echo json_encode($arr);
	mysqli_close($connect);
?>