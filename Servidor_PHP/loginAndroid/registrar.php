<?php

	include_once 'conexao.php';
	
	$nome = $_POST['nome'];
	$email = $_POST['email'];
	$senha = $_POST['senha'];

	$sql = $dbcon->query("select * from usuario where email='$email'");
	
	if(mysqli_num_rows($sql) > 0){
		echo "Email_erro";
	}else{
		$sql1 = $dbcon->query("insert into usuario (nome,email,senha) values ('$nome','$email','$senha')");
		if($sql1){
			echo "registro_ok";
		}else{
	              	echo "registro_erro"; 		
		}
	}

?>
