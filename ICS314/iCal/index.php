<?php include 'functions.php'; ?> 

<html>
    
<head>
    <title> iCal </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    
<body>
        
    <?php checkInputErrors(); ?>
    <h1>iCal</h1>
    
    <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
        Event:
        <input type="text" name="title">
        <br> 
        Location:
        <input type="text" name="location">
        <br>
        Time: <input type="datetime-local" name="starttime"> to
              <input type="datetime-local" name="endtime">
        <br>
        Priority: <input type="radio" name="priority" value="low" checked> low
                  <input type="radio" name="priority" value="medium"> medium
                  <input type="radio" name="priority" value="high"> high
        <br>
        Type: <input type="radio" name="type" value="public" checked> public
              <input type="radio" name="type" value="private"> private
              <input type="radio" name="type" value="confidential"> confidential
        <br> 
        <input type="submit" name="button" value="generate">            
    </form>
    
    <?php generateIcsText(); ?>
           
</body>

</html>
