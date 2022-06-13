<?php

if(isset($_POST["alimento"])){
	
	$alimento = $_POST["alimento"];
	$cantidad = $_POST["cantidad"];
	$kcal = $_POST["kcal"];
	$grasa = $_POST["grasa"];
	$proteina = $_POST["proteina"];
	$carbohidratos = $_POST["carbohidratos"];
	$servidor = "localhost";
	$usuario = "root";
	$password = "";
	$dbname = "almacen";
	
	$conexion = mysqli_connect($servidor, $usuario, $password, $dbname);
	
	if (!$conexion) {
		echo "Error en la conexiona MySQL: " . mysqli_connect_error();
		exit();
	}
	$sql = "INSERT INTO alimentos (alimento, cantidad, kcal, grasa, proteina, carbohidratos) VALUES ('".addslashes($alimento)."', '".addslashes($cantidad)."', '".addslashes($kcal)."', '".addslashes($grasa)."', '".addslashes($proteina)."', '".addslashes($carbohidratos)."')";
	if (mysqli_query($conexion, $sql)) {
		echo "Registro insertado correctamente.";
	} else {
		echo "Error: " . $sql . "<br>" . mysqli_error($conexion);
	}
}

?>