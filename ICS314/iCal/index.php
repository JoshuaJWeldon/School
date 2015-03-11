<?php include 'functions.php'; ?> 

<html>
    
<head>
    <title> iCal </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    
<body>
        
    <?php checkInputErrors(); ?>
    <h1> iCal </h1>
    
    <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
        New Event:
        <input type="text" name="event"> 
            <span class="error"> * <?php echo $eventErr; ?></span>
        <br> 
        Location:
            <input type="text" name="location">
                <span class="error"> <?php echo $locationErr; ?></span>
        <br>
        StartTime: 
            <input type="number" value="s_month" min="1" max="12" placeholder="1" >/
            <input type="number" value="s_day" min="1" max="31" placeholder="1">/
            <input type="number" value="s_year" min="2015" max="2017" placeholder="2015"> 
            <input type="number" value="s_hour" min="1" max="12" placeholder="1" >:
            <input type="number" value="s_month" min="0" max="59" placeholder="30" >
            <input type="radio" name="s_midday" value="public" checked>am
            <input type="radio" name="s_type" value="private">pm 
                <span class="error"> * <?php echo $timeErr; ?></span>
        <br>
        EndTime: 
            <input type="number" value="e_month" min="1" max="12" placeholder="1" >/
            <input type="number" value="e_day" min="1" max="31" placeholder="1">/
            <input type="number" value="e_year" min="2015" max="2017" placeholder="2015"> 
            <input type="number" value="e_hour" min="1" max="12" placeholder="1" >:
            <input type="number" value="e_month" min="0" max="59" placeholder="30" >
            <input type="radio" name="e_midday" value="public" checked>am
            <input type="radio" name="e_type" value="private">pm 
                <span class="error"> * <?php echo $timeErr; ?></span>
        <br>
        Priority: 1 <input type="range" min="1" max="9"> 9
        <br>
        Type: <input type="radio" name="class" value="public" checked> public
              <input type="radio" name="class" value="private"> private
              <input type="radio" name="class" value="confidential"> confidential
        <br> 
        <input type="submit" name="button" value="generate">            
    </form>

    <p><?php generateIcsText(); ?></p>
           
</body>

</html>
