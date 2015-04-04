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
            <p>
                Select image to upload:
                <input type="file" name="icsFile" id="icsFile">
                <input type="submit" value="Upload" name="submit">
                <span class="error"> <?php echo $fileErr; ?> </span>
            </p>
        </form>
        
        <p>
            <br>
            List of uploaded icsFiles:<br>
            <?php uploadList(); ?>
            
            <br>
            FreeTime Files:<br>
            <?php generateFreeTime(); ?>
        </p>
    </div>
        
    <?php endText();?>
    
</body>
</html>