<?php

include_once 'conexao.php';

$email=$_POST['email'];
$senha=$_POST['senha'];

//$email=$_GET['email'];
//$senha=$_GET['senha'];

$sql = $dbcon->query("select * from usuario where email='$email' and senha='$senha'");

if(mysqli_num_rows($sql) > 0){
	echo "login_ok";
}else{
	echo "login_erro";
}

?>
