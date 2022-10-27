<?php
require "dbcon.php";
$id = $_POST['idSV'];
$hoten = $_POST['hotenSV'];
$namsinh = $_POST['namsinhSV'] ;
$diachi = $_POST['diachiSV'];

$query = "UPDATE student SET hoten = '$hoten',namsinh = '$namsinh',diachi = '$diachi' WHERE id = '$id'";
if (mysqli_query($connect,$query)){
	//thành công
	echo "success";

}
else{
	//lỗi
	echo "error";
}
?>