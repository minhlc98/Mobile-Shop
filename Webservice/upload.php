<?php
	//echo "abc"; 
	 $File_path="image-user/".$_FILES['upload_file']['name'];
	 if (move_uploaded_file($_FILES['upload_file']['tmp_name'], $File_path)){
	 	echo $File_path;
	 }	
	 else{
	 	echo "Failed";
	 }
?>