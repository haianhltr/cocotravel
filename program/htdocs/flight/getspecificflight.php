<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

 include 'DatabaseConfig.php';
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $id = $_POST['id'];
 
 $Sql_Query = "select * from flight where flight_id = '$id'";
 


if ($result = mysqli_query($con, $Sql_Query))
{

 $resultArray = array();
 $tempArray = array();


while($row = $result->fetch_object())
 {
 // Add each result into the results array
 $tempArray = $row;
     array_push($resultArray, $tempArray);
 }
  echo json_encode($resultArray);

}





 
 }else{
 echo "Check Again";
 }
mysqli_close($con);

?>