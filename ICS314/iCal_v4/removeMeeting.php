<?php 
    array_map('unlink', glob("MeetingFiles/*.ics")); 
    
    header('Location: meetings.php');
    die();
?>