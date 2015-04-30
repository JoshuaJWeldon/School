<?php 
    array_map('unlink', glob("uploads/*.ics")); 
    array_map('unlink', glob("downloads/*.ics"));
    
    header('Location: free.php');
    die();
?>