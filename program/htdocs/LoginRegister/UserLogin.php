<?php

 if($_SERVER['REQUEST_METHOD']=='POST'){

 include 'DatabaseConfig.php';
 
 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);
 
 $email = $_POST['email'];
 $password = $_POST['password'];
 
 $Sql_Query = "select * from usertable where user_email = '$email' and user_password = '$password' ";
 
 $check = mysqli_fetch_array(mysqli_query($con,$Sql_Query));
 
 if(isset($check)){
 
$Sql_Query2 = "select customer_id from usertable where user_email = '$email'";

if ($result = mysqli_query($con, $Sql_Query2))
{




while($row = $result->fetch_assoc())
 {
 // Add each result into the results array
   echo "Data Matched-".$row["customer_id"];
 }
 

}


 }
 else{
 echo "Invalid Username or Password Please Try Again-0";
 }
 
 }else{
 echo "Check Again-0";
 }
mysqli_close($con);

?>