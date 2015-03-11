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
                    $eventErr = "Only letters and white space allowed"; 
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
                    $locationErr = "Only letters and white space allowed"; 
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
        
        if(true):?>
        
            <p> <?php echo $event; ?> </p>
            <p> <?php echo $location; ?> </p>
            <p> <?php echo $starttime; ?> </p>
            <p> <?php echo $endtime; ?> </p>
            <p> <?php echo $priority; ?> </p>
            <p> <?php echo $type; ?> </p>
        
        <?php endif;
        
    }  
    
    function test_input($data) {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }   
  
?>
    