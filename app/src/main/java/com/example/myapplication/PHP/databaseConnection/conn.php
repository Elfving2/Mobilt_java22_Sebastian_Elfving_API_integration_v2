<?php

    $servername = "localhost:3308";
    $username = "root";
    $password = "";
    $database = "users";

    $conn = mysqli_connect($servername, $username, $password, $database);


    // Table name and column names
$tableName = "user";
$columns = array("id", "username", "password", "email");
$tableExists = false;
$result = $conn->query("SHOW TABLES LIKE '$tableName'");

if ($result !== false && $result->num_rows > 0) {
    $tableExists = true;
} else {
    $sql = "CREATE TABLE IF NOT EXISTS user (
        id INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(20) NOT NULL,
        password VARCHAR(20) NOT NULL,
        email VARCHAR(30) NOT NULL
    )";
    $conn->query($sql);
}
