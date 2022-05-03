<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $customer_id = $_POST['customer_id'];
 $hotel_id = $_POST['hotel_id'];
 $checkin_date = $_POST['checkin_date'];
$date = date('Y-m-d H:i:s');

 

 
$Sql_Query = "INSERT INTO HotelSale (customer_id,SaleDate,hotel_id,checkinDate) values
 ('$customer_id','$date','$hotel_id', '$checkin_date')";



 if(mysqli_query($con,$Sql_Query))
{
 echo 'Hotel Booked Successfully';
}
else
{
 echo 'Something went wrong';
 }
 
}
 mysqli_close($con);
?>