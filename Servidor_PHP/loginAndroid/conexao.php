<?php

	$host = "localhost";
	$user = "seu_usuario";
        $pass = "sua_senha";
        $bd = "teste";

        $dbcon = new MySQLi("$host", "$user", "$pass", "$bd");

            if($dbcon->connect_error){
                echo "conexao_erro";
            }else{
                echo "conexao_ok";
            }


?>
