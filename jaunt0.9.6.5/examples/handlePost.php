<?php
error_reporting(E_ALL);
$method = $_SERVER['REQUEST_METHOD'];
$request = explode("/", substr(@$_SERVER['PATH_INFO'], 1));

switch ($method) {
  case 'POST':
    header('X-PHP-Response-Code: 200', true, 200);    
    break;
  default:
    break;
}
?>
<html>Hello World</html>
