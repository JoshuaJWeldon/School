<?php
    $icsText = "";
    $icsTextErr = "";
    $isValid = false;
    
    $event = $location = $starttime = $endtime = $priority = $type = "";
    $eventErr = $locationErr = $timeErr = $priorityErr = $typeErr = "";

    function checkInputErrors(){

        global $_SERVER, $_POST, $icsText, $icsTextErr, $isValid;
        global $event, $location, $starttime, $endtime, $priority, $type;
        global $eventErr, $locationErr, $timeErr, $priorityErr, $typeErr;
       
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
                 
            //test event
            if (!empty($_POST["event"])) {
  
                $event = test_input($_POST["event"]);

                if(empty($event)){
                    $eventErr = "You must have an event name";
                }  
                
                if (!preg_match("/^[a-zA-Z0-9 ]*$/",$event)) {
                    $eventErr = "Only letters, digits, and spaces are allowed"; 
                }
                 	
                unset($_POST["event"]);
            }
            else{
                $eventErr = "You must have an event name";
            }

            //test location
            if (!empty($_POST["location"])) {
  
                $location = test_input($_POST["location"]); 
                
                if (!preg_match("/^[a-zA-Z0-9 ]*$/",$location)) {
                    $locationErr = "Only letters, digits, and spaces are allowed"; 
                }
                 	
                unset($_POST["location"]);
            }
            
            //test date and time
              
                     
            $_SERVER["REQUEST_METHOD"] = "";       
        }
    }
    
    function generateIcsText(){
        
        global $icsText, $icsTextErr, $isValid;
        global $event, $location, $starttime, $endtime, $priority, $type;
        
        if(!empty($event)):
        ?>
    
            <p> Event: <?php echo $event; ?> </p>
            <p> Location: <?php echo $location; ?> </p>
            <p> Time: <?php echo $starttime; ?> </p>
            <p> Time: <?php echo $endtime; ?> </p>
            <p> Priority: <?php echo $priority; ?> </p>
            <p> Type: <?php echo $type; ?> </p>
        
            <form>
                <input type="button" value="export" name="export">
            </form>
            
        <?php endif;
            
        //!TODO: Need way to add time zone in form. Also need to get time into correct format.
        //Date/Time format is: YYYYMMDDTHHMMSSZ  Not sure what the T or Z represent
        //Time must be in Greenwich Mean Time (GMT) which is 10 ahead of Hawaii
        
        $icsText = "BEGIN:VCALENDAR\nX-WR-TIMEZONE:" . $timezone . "\nBEGIN:VEVENT\nVERSION:2.0\nCLASS:"
        			. $priority . "\nLOCATION:" . $location . "\nPRIORITY:" . $priority
					. "\nSUMMARY:" . $event . "\nDTSTART:" . $starttime . "\nDTEND:" . $endtime .
					"\nEND:VEVENT\nEND:VCALENDAR";
        echo($icsText);
    }  
    
    function export(){
        
        //!TODO: export icstest
        
    }
    
    function test_input($data) {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }   
  
?>
    