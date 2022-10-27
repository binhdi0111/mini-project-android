<?php
$severname ="localhost";
$username = "root";
$password = "anhbinh987";
$database = "sinhvien";

$connect = mysqli_connect($severname,$username,$password,$database);

mysqli_query($connect,"SET NAMES 'utf8'");
?>