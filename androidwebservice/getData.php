<?php
require "dbcon.php";
$query = "SELECT * FROM student";
$data = mysqli_query($connect,$query);

class SinhVien{
    function __construct($id,$hoten, $namsinh, $diachi){
    	$this->Id = $id;
        $this->HoTen = $hoten;
        $this->NamSinh = $namsinh;
        $this->DiaChi = $diachi;
    }
}
$mangSV = array();
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangSV,new SinhVien($row['id'],$row['hoten'],$row['namsinh'],$row['diachi']));
}
echo json_encode($mangSV);

?>