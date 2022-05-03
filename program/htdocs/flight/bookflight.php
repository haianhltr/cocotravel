<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $customer_id = $_POST['customer_id'];
 $flight_id = $_POST['flight_id'];
$date = date('Y-m-d H:i:s');

 

 
$Sql_Query = "INSERT INTO FlightSale (customer_id,SaleDate,flight_id) values
 ('$customer_id','$date','$flight_id')";



 if(mysqli_query($con,$Sql_Query))
{
 echo 'Flight Booked Successfully';
}
else
{
 echo 'Something went wrong';
 }
 
}
 mysqli_close($con);
?>