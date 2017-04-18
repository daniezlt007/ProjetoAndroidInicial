<!DOCTYPE html>
<html>
<head>
    <title>Teste de BoostStrap</title>
    <!-- - define a viewport -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
    <!-- -adicionar CSS BootStrap  -->
    <link rel="stylesheet" href="css/bootstrap.min.css" media="screen">
<!-- -adicionar CSS Customizado  -->
    <link rel="stylesheet" href="css/estilo.css" media="screen">

</head>
<body>
    <h1>Olá Mundo</h1>
    <?php

        $a = 0;
        while($a < 10){
            echo $a * $a."<br>" ;
	$a++;	
        }

    ?>
    
</body>
</html>
