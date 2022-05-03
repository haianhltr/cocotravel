<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){


 
$con=mysqli_connect("localhost","root","","cocotravel");
 
 $country = $_POST['country'];
 
 $Sql_Query = "select * from restaurant where country = '$country'";
 


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