<?php include 'functions.php'; ?> 

<!DOCTYPE html>
<html>
    
<head>
    <?php headerText()?>
    <title> iCal | Meetings </title>

</head>
    
<body>
       
    <?php navBar(); uploadPersonFile();?>
    
    <div class="container">
     
        <h1> Meetings 
            <br><small> Generates free times between two schedules.</small>
        </h1>
        
        <div class="row">
            <div class="col-md-6">
                <h2><span class="personA"> Person A </span></h2>
        
                <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" enctype="multipart/form-data">
                    <h3>Select iCal file to upload:</h3><br>
                    <p>
                        <input type="file" name="personAFile" id="personAFile"><br>
                        <input type="submit" value="Upload" name="submit">
                        <span class="error"> <?php echo $fileErrA; ?> </span>
                    </p>
                </form>
                <br><br><br><br>
            </div>
    
            <div class="col-md-6">
                <h2> <span class="personB">Person B</span></h2>
                <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" enctype="multipart/form-data">
                    <h3>Select iCal file to upload:</h3><br>
                    <p>
                        <input type="file" name="personBFile" id="personBFile"><br>
                        <input type="submit" value="Upload" name="submit">
                        <span class="error"> <?php echo $fileErrB; ?> </span>
                    </p>
                </form>
                <br><br><br><br>
            </div> 
        </div>
        
        <br>
        <h3> Uploaded iCal file(s): </h3>
        <p>
            <?php 
            uploadMeetingList(); 
            if($uploadCount == 0){ 
                ?>
                You have not imported any files yet.
                <?php
            } 
            ?>
        </p>
        
        <?php
        if($uploadCount != 0){
            ?>
            <br>
            <a href="removeMeeting.php"> &#128293; remove </a> all files</p>
            <br>
            <h3>FreeTime File:</h3>
            <p>
            <?php 
            generateMeetingTime();
        }
        ?>
        
    </div>
    <?php endText();?>
    
</body>
</html>