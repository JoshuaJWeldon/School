<?php include 'functions.php'; ?> 

<html>
    
<head>
    
    <?php headerText()?>
    <title> iCal | Create </title>

</head>
    
<body>
       
    <?php navBar(); checkInputErrors(); ?>
    
    <div class="container">
     
        <h1> Create </h1>
   

        <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
            New Event:
            <input type="text" name="summery"> 
                <span class="error"> * <?php echo $summeryErr; ?></span>
            <br> 
            Location:
                <input type="text" name="location">
                    <span class="error"> <?php echo $locationErr; ?></span>
                <br>
            StartTime: 
                <input type="number" name="s_month" min="1"    max="12"   value="1" >/
                <input type="number" name="s_day"   min="1"    max="31"   value="21">/
                <input type="number" name="s_year"  min="2015" max="2017" value="2015"> 
                <br>
                <input type="number" name="s_hour"  min="1"    max="12"   value="2" >:
                <input type="number" name="s_min"   min="0"    max="59"   value="30" >
                <input type="radio" name="s_midday" value="am" checked>am
                <input type="radio" name="s_midday" value="pm">pm 
                    <span class="error"> * </span>
                <br>
            EndTime: 
                <input type="number" name="e_month" min="1"    max="12"   value="1" >/
                <input type="number" name="e_day"   min="1"    max="31"   value="21">/
                <input type="number" name="e_year"  min="2015" max="2017" value="2015"> 
                <br>
                <input type="number" name="e_hour"  min="1"    max="12"   value="3" >:
                <input type="number" name="e_min"   min="0"    max="59"   value="30" >
                <input type="radio" name="e_midday" value="am" checked>am
                <input type="radio" name="e_midday" value="pm">pm 
                    <span class="error"> * <?php echo $timeErr; ?></span>
                <br>
            Priority:                    
                <input type="number" name="priority" min="0" max="9" value="0" >
                <br>
            Type: 
                <input type="radio" name="class" value="PUBLIC" checked> public
                <input type="radio" name="class" value="PRIVATE">        private
                <input type="radio" name="class" value="CONFIDENTIAL">   confidential
                <br> 
        
            <input type="submit" name="button" value="generate">            
        </form>

        <?php generateIcsText();?>
        
    </div>
        
    <?php endText();?>
           
        
  
</body>

</html>
