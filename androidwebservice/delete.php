<?php
require "dbcon.php";
$id = $_POST['idcuaSV'];
// $id = "36";
$query = "DELETE FROM student WHERE id = '$id'";
if (mysqli_query($connect,$query)){
	//thành công
	echo "success";

}
else{
	//lỗi
	echo "error";
}
?>