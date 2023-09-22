<?php
global $conn;
require 'conn.php';

$username = $_POST['username'];
$password = $_POST['password'];

$query = "SELECT * FROM user WHERE username = '$username' AND password = '$password'";

$result = $conn->query($query);

$object = new stdClass();
  
if ($result -> num_rows > 0) {
   // print("logged in");

    $row = $result -> fetch_array(MYSQLI_NUM);

    // Added property to the object
    $object -> id = $row[0];
    $object -> username = $row[1];
    $object -> password = $row[2];
    $object -> email = $row[3];

    $json_data = json_encode($object);
    header('Content-Type: application/json');
    echo $json_data;
} else {
    print("something went wrong");
}

$conn->close();