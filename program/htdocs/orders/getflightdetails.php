<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){


 
$con=mysqli_connect("localhost","root","","cocotravel");
 
 $customer_id = $_POST['customer_id'];
 
 $Sql_Query = "select u.user_email,u.first_name,u.last_name

, s.SaleDate ,f.flight_id, f.departure,f.destination,f.date from usertable u join FlightSale s on u.customer_id = s.customer_id join flight f  on f.flight_id = s.flight_id where u.customer_id = '$customer_id'";



 


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