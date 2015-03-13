<?php
    $icsText = "";
    $icsTextErr = "";
    $isValid = false;
    
    $summery = $location = $starttime = $endtime = $priority = $class = "";
    $summeryErr = $locationErr = $timeErr = $priorityErr = $classErr = "";

    function checkInputErrors(){

        global $_SERVER, $_POST, $icsText, $icsTextErr, $isValid;
        global $summery, $location, $starttime, $endtime, $priority, $class;
        global $summeryErr, $locationErr, $timeErr, $priorityErr, $classErr;
       
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
                 
            //test summery
            if (!empty($_POST["summery"])) {
  
                $summery = test_input($_POST["summery"]);

                if(empty($summery)){
                    $summeryErr = "You must have an event name";
                }  
                
                if (!preg_match("/^[a-zA-Z0-9 ]*$/",$summery)) {
                    $summery = "";
                    $summeryErr = "Only letters, digits, and spaces are allowed"; 
                }
                 	
                unset($_POST["summery"]);
            }
            else{
                $summeryErr = "You must have an event name";
            }

            //test location
            if (!empty($_POST["location"])) {
  
                $location = test_input($_POST["location"]); 
                
                if (!preg_match("/^[a-zA-Z0-9 ]*$/",$location)) {
                    $location = "";
                    $locationErr = "Only letters, digits, and spaces are allowed"; 
                }
                 	
                unset($_POST["location"]);
            }
            
            //test starttime
            if(!checkdate($_POST["s_month"], $_POST["s_day"], $_POST["s_year"]) && $_POST["s_year"] < 2015 && !checktime($_POST["s_hour"], $_POST["s_min"])){
                $timeErr = "<br> You must enter in a valid future date";
            }
            else{
                
                //year
                $starttime = $starttime . $_POST["s_year"];
                
                //month
                if($_POST["s_month"] < 10){
                    $starttime = $starttime . "0";
                }
                
                $starttime = $starttime . $_POST["s_month"]; 
                
                //day
                if($_POST["s_day"] < 10){
                    $starttime = $starttime . "0";
                }
                
                $starttime = $starttime . $_POST["s_day"] . "T";
                
                //hour
                if($_POST["s_midday"] == "pm"){
                    $_POST["s_hour"] += 12;
                }
                
                if($_POST["s_hour"] < 10){
                    $starttime = $starttime . "0";
                }
                
                $starttime = $starttime . $_POST["s_hour"];
                
                //min
                if($_POST["s_min"] < 10){
                    $starttime = $starttime . "0";
                }
                
                $starttime = $starttime . $_POST["s_min"] . "00Z";
            }
            
            //test endtime
            
            if(!checkdate($_POST["e_month"], $_POST["e_day"], $_POST["e_year"]) && $_POST["e_year"] < 2015){
                $timeErr = "<br> You must enter in a valid future date";
            }
            else{
                //year
                $endtime = $endtime . $_POST["e_year"];
                
                //month
                if($_POST["e_month"] < 10){
                    $endtime = $endtime . "0";
                }
                
                $endtime = $endtime . $_POST["e_month"]; 
                
                //day
                if($_POST["e_day"] < 10){
                    $endtime = $endtime . "0";
                }
                
                $endtime = $endtime . $_POST["e_day"] . "T";
                
                //hour
                if($_POST["e_midday"] == "pm"){
                    $_POST["e_hour"] += 12;
                }
                
                if($_POST["e_hour"] < 10){
                    $endtime = $endtime . "0";
                }
                
                $endtime = $endtime . $_POST["e_hour"];
                
                //min
                if($_POST["e_min"] < 10){
                    $endtime = $endtime . "0";
                }
                
                $endtime = $endtime . $_POST["e_min"] . "00Z";
            }
                       
            // save priority
            
            $priority = $_POST["priority"];
            
            // save class
            
            $class = $_POST["class"];
            
            //test
              
                     
            $_SERVER["REQUEST_METHOD"] = "";       
        }
    }
    
    function generateIcsText(){
        
        global $icsText, $icsTextErr, $isValid;
        global $summery, $location, $starttime, $endtime, $priority, $class;
        
        if(!empty($summery)): ?>
            
            <p> Summery: <?php echo $summery; ?> </p>
            
            <?php if(!empty($location)): ?>
        
                <p> Location: <?php echo $location; ?> </p>
        
            <?php endif; ?>
            
            <p> Time: <?php echo $starttime; ?> </p>
            <p> Time: <?php echo $endtime; ?> </p>
            <p> Priority: <?php echo $priority; ?> </p>
            <p> Class: <?php echo $class; ?> </p>
            
            <br>
            <br>
            <br>
            
            <?php
            
                $icsText = "BEGIN:VCALENDAR\nX-WR-TIMEZONE:" . $timezone . "\nBEGIN:VEVENT\nVERSION:2.0\nCLASS:"
        			. $priority . "\nLOCATION:" . $location . "\nPRIORITY:" . $priority
					. "\nSUMMARY:" . $event . "\nDTSTART:" . $starttime . "\nDTEND:" . $endtime .
					"\nEND:VEVENT\nEND:VCALENDAR";
        
                echo $icsText;
            
            ?>
            
            <form>
                <input type="button" value="export" name="export">
            </form>
        
        <?php endif;
        
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
    
    function checktime($hour, $min) {
        if ($hour < 1 || $hour > 12 || !is_numeric($hour)) {
            return false;
        }
        if ($min < 0 || $min > 59 || !is_numeric($min)) {
            return false;
        }
 
        return true;
    }
    
  
?>
    