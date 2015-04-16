<?php
    $icsText = "";
    $icsTextErr = "";
    $isValid = false;
    
    $summery = $location = $starttime = $endtime = $priority = $class = "";
    $summeryErr = $locationErr = $timeErr = $priorityErr = $classErr = "";
    
    $fileErr = $fileErrA = $fileErrB = $fileOut = "";
    
    $uploadCount = 0;
    
    
    function headerText(){
        ?>
        <link rel='shortcut icon' type='image/x-icon' href="favicon.ico">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
        <?php
    }
    
    function uploadPersonFile(){
        global $_FILES, $_POST, $_SERVER;
        global $fileErrA, $fileErrB;
        
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            
            if(isset($_FILES["personAFile"])){
                 $target_dir    = "MeetingFiles/";
                 $target_file   = $target_dir . "PersonA_" . basename($_FILES["personAFile"]["name"]);
                 $uploadOk      = 1;
                 $imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);

                // Check if file already exists
                if(basename($_FILES["personAFile"]["name"]) == ""){
                    $fileErrA = "Sorry, must choose a file.";
                    $uploadOk = 0;
                }
            
                else if (file_exists($target_file)) {
                    $fileErrA = "Sorry, file already exists.";
                    $uploadOk = 0;
                }
            
                // Allow certain file formats
                else if($imageFileType != "ics") {
                    $fileErrA = "Sorry, only ICS files are allowed.";
                    $uploadOk = 0;
                }
            
                // Check file size
                else if ($_FILES["personAFile"]["size"] > 500000) {
                    $fileErrA = "Sorry, your file is too large.";
                    $uploadOk = 0;
                }

                // Check if $uploadOk is set to 0 by an error
                if ($uploadOk == 1) {
                    if (!move_uploaded_file($_FILES["personAFile"]["tmp_name"], $target_file)) {
                        $fileErrA = "Sorry, there was an error uploading your file.";
                    } 
                }  
            }
            if(isset($_FILES["personBFile"])){
                 $target_dir    = "MeetingFiles/";
                 $target_file   = $target_dir . "PersonB_" . basename($_FILES["personBFile"]["name"]);
                 $uploadOk      = 1;
                 $imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);

                // Check if file already exists
                if(basename($_FILES["personBFile"]["name"]) == ""){
                    $fileErrB = "Sorry, must choose a file.";
                    $uploadOk = 0;
                }
            
                else if (file_exists($target_file)) {
                    $fileErrB = "Sorry, file already exists.";
                    $uploadOk = 0;
                }
            
                // Allow certain file formats
                else if($imageFileType != "ics") {
                    $fileErrB = "Sorry, only ICS files are allowed.";
                    $uploadOk = 0;
                }
            
                // Check file size
                else if ($_FILES["personBFile"]["size"] > 500000) {
                    $fileErrB = "Sorry, your file is too large.";
                    $uploadOk = 0;
                }

                // Check if $uploadOk is set to 0 by an error
                if ($uploadOk == 1) {
                    if (!move_uploaded_file($_FILES["personBFile"]["tmp_name"], $target_file)) {
                        $fileErrB = "Sorry, there was an error uploading your file.";
                    } 
                }  
            }
        }
    }
    
    
    function upload(){
        
        global $_FILES, $_POST, $_SERVER;
        global $fileErr;
        
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            
            $target_dir    = "uploads/";
            $target_file   = $target_dir . basename($_FILES["icsFile"]["name"]);
            $uploadOk      = 1;
            $imageFileType = pathinfo($target_file,PATHINFO_EXTENSION);

            // Check if file already exists
            if(basename($_FILES["icsFile"]["name"]) == ""){
                $fileErr = "Sorry, must choose a file.";
                $uploadOk = 0;
            }
            
            else if (file_exists($target_file)) {
                $fileErr = "Sorry, file already exists.";
                $uploadOk = 0;
            }
            
            // Allow certain file formats
            else if($imageFileType != "ics") {
                $fileErr = "Sorry, only ICS files are allowed.";
                $uploadOk = 0;
            }
            
            // Check file size
            else if ($_FILES["icsFile"]["size"] > 500000) {
                $fileErr = "Sorry, your file is too large.";
                $uploadOk = 0;
            }

            // Check if $uploadOk is set to 0 by an error
            if ($uploadOk == 1) {
                
                if (!move_uploaded_file($_FILES["icsFile"]["tmp_name"], $target_file)) {
                    $fileErr = "Sorry, there was an error uploading your file.";
                } 
            }  
        }
    }
    
    function uploadMeetingList(){
        
        global $uploadCount;
        
        if ($handle = opendir('MeetingFiles/')) {

            while (false !== ($entry = readdir($handle))) {

                if ($entry != "." && $entry != ".." && $entry != ".DS_Store") {
                    
                    ?> <a href="uploads/<?php echo $entry; ?>"> <?php echo $entry; ?> </a> <br> <?php
                        
                    $uploadCount++;
                }
            }

            closedir($handle);
        }
    }
    
    function uploadList(){
        
        global $uploadCount;
        
        if ($handle = opendir('uploads/')) {

            while (false !== ($entry = readdir($handle))) {

                if ($entry != "." && $entry != ".." && $entry != ".DS_Store") {
                    
                    ?> <a href="uploads/<?php echo $entry; ?>"> <?php echo $entry; ?> </a> <br> <?php
                        
                    $uploadCount++;
                }
            }

            closedir($handle);
        }
    }
    
    function generateFreeTime(){
        
        $output = "";
        
        array_map('unlink', glob("downloads/*.ics"));
        
        $output = exec("java -cp bin/ FreeTime uploads/ downloads/file.ics");
        
        echo $output;
       
        if ($handle = opendir('downloads/')) {

            while (false !== ($entry = readdir($handle))) {

                if ($entry != "." && $entry != ".." && $entry != ".DS_Store") {
                    
                    ?> <a href="downloads/<?php echo $entry; ?>"> click here </a> <br> <?php
                
                }
            }
                
            closedir($handle);
        }
        
    }
    
    function generateMeetingTime(){
        $output = "";
        
        array_map('unlink', glob("downloads/*.ics"));
        
        $output = exec("java -cp bin/ FreeTime MeetingFiles/ downloads/file.ics");
        
        $output = $output . exec("java -cp bin/ RenameSummery");
        
        echo $output;
        
        array_map('unlink', glob("downloads/file.ics"));
       
        if ($handle = opendir('downloads/')) {

            while (false !== ($entry = readdir($handle))) {

                if ($entry != "." && $entry != ".." && $entry != ".DS_Store") {
                    
                    ?> <a href="downloads/<?php echo $entry; ?>"> click here </a> <br> <?php
                
                }
            }
                
            closedir($handle);
        }
    }
    
    function endText(){
        ?>
        <br><br><br>
        <footer class="footer">
            <div class="container">
                <p class="text-muted">Please use Safari or Chrome as your browser for best results.</p>
            </div>
        </footer>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        
        <?php
    }
    
    function navBar(){
        
        ?>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.php"> iCal </a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="index.php">Home</a></li>
                        <li><a href="create.php">P1:Create</a></li>
                        <li><a href="free.php">P2:FreeTime</a></li>
                        <li><a href="meetings.php">P3:Meetings</a></li>
                        <!--<li><a href="test.php">TEST</a></li>-->
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </nav>

        <br>
        <br>
        <br> 
        <?php
    }

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
                    $summery = "";
                    $locationErr = "Only letters, digits, and spaces are allowed"; 
                }
                 	
                unset($_POST["location"]);
            }
            
            //test starttime
            if(!checkdate($_POST["s_month"], $_POST["s_day"], $_POST["s_year"]) || $_POST["s_year"] < 2015 || !checktime($_POST["s_hour"], $_POST["s_min"])){
                $timeErr = "<br> You must enter in a valid future date";
                $summery = "";
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
                if($_POST["s_midday"] == "am"){
                    if($_POST["s_hour"] == 12)
                        $_POST["s_hour"] = 0;
                }
                
                if($_POST["s_midday"] == "pm"){
                    if($_POST["s_hour"] != 12)
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
                
                $starttime = $starttime . $_POST["s_min"] . "00";
            }
            
            //test endtime
            
            if(!checkdate($_POST["e_month"], $_POST["e_day"], $_POST["e_year"]) || $_POST["e_year"] < 2015 || !checktime($_POST["e_hour"], $_POST["e_min"])){
                $timeErr = "<br> You must enter in a valid future date";
                $summery = "";
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
                if($_POST["e_midday"] == "am"){
                    if($_POST["e_hour"] == 12)
                        $_POST["e_hour"] = 0;
                }
                
                if($_POST["e_midday"] == "pm"){
                    if($_POST["e_hour"] != 12)
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
                
                $endtime = $endtime . $_POST["e_min"] . "00";
            }
                       
            // test priority
            if (!empty($_POST["priority"])) {
  
                $priority = $_POST["priority"];

                if(empty($priority)){
                    $priority = 0;
                }  
                
                if (!is_numeric($priority)) {
                    $priority = 0;
                }
                 	
                unset($_POST["priority"]);
            }
            else{
                $priority = 0;
            }
            
            // save class
            
            $class = $_POST["class"];
            
            //test
              
                     
            $_SERVER["REQUEST_METHOD"] = "";       
        }
    }
    
    function generateIcsText(){
        
        global $icsText, $icsTextErr, $isValid;
        global $summery, $location, $starttime, $endtime, $priority, $class;
        
        
        if(!empty($summery)){ ?>
            
            
            <h3> Event: <small> <?php echo $summery; ?> </small> </h3>
            <p>ICS FILE: <a href="file.ics"> click here </a></p>
            
            <?php
                
                $icsText = "BEGIN:VCALENDAR\n" .
                           "VERSION:2.0\n" .
                           "TZID:Pacific/Honolulu\n" .
                           "BEGIN:VEVENT\n" .
                           "DTSTART:$starttime\n" .
                           "DTEND:$endtime\n" .
                           "CLASS:$class\n";
            
            if(!empty($location)){
                $icsText = $icsText . "LOCATION:$location\n";
            }
            
            $icsText = $icsText . "SUMMARY:$summery\n" .
                                  "PRIORITY:$priority\n" .
                                  "END:VEVENT\n" .
                                  "END:VCALENDAR\n";
                
            $file = fopen("file.ics", "w");
            fwrite($file, $icsText);
            fclose($file);
            
        }
        
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
    