<?php include 'functions.php'; ?> 

<!DOCTYPE html>
<html>
    
<head>
    <?php headerText()?>
    <title> iCal | FreeTime </title>

</head>
    
<body>
       
    <?php navBar(); upload();?>
    
    <div class="container">
     
        <h1> FreeTime </h1>
        
        <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>" enctype="multipart/form-data">
            <h3>Select image to upload:</h3>
            <p>
                <input type="file" name="icsFile" id="icsFile">
                <input type="submit" value="Upload" name="submit">
                <span class="error"> <?php echo $fileErr; ?> </span>
            </p>
        </form>
        
        <h3> Uploaded iCal file(s): </h3>
        <p>
        <?php 
            uploadList(); 
            if($uploadCount == 0){ 
                ?>
                You have not imported any files yet.
                <?php
            } 
            else {
                ?>
                <br>
                <a href="remove.php"> &#128293; remove </a> all files</p>
                <br>
                <h3>FreeTime File:</h3>
                <p>
                <?php generateFreeTime();
            }?>
        </p>
    </div>
        
    <?php endText();?>
    
</body>
</html>