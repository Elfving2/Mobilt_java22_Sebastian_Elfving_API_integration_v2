<?php
global $conn;
require "conn.php";

$username = $_POST['username'];
$password = $_POST['password'];

$query = "INSERT INTO user(username, password) VALUES('$username', '$password')";

$result = $conn->query($query);
/*
if ($result) {
    print("SUCESS");
} else {
    print("Something went wrong");
 }
*/
