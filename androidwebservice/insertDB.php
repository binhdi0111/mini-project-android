<?php
require "dbcon.php";
$hoten = $_POST['hotenSV'];
$namsinh = $_POST['namsinhSV'] ;
$diachi = $_POST['diachiSV'];
$query = "INSERT INTO student VALUES(null,'$hoten','$namsinh','$diachi')";
if (mysqli_query($connect,$query)){
	//thành công
	echo "success";

}
else{
	//lỗi
	echo "error";
}
?>