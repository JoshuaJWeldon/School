<?php include 'functions.php'; ?> 

<<<<<<< HEAD
<html>
    
<head>
    <title> iCal </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    
<body>
<div class="container">
       
    <?php checkInputErrors(); ?>
    <div class="header"> 
        <h1> iCal </h1>
    </div>
    <div class="body">
        <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
            <div class="bodypart">New Event:</div>
            <div class="bodypart">
                <input type="text" name="" placeholder="Summary"> 
                	<span class="error"> * <?php echo $summeryErr; ?></span>
            </div> 
            <div class="bodypart">
                <input type="text" name="" placeholder="Location">
                	<span class="error"> <?php echo $locationErr; ?></span>
            </div>
            <div class="bodypart">
                StartTime: 
                <input type="number" name="s_month" placeholder= "Month" min="1"    max="12"  >/
                <input type="number" name="s_day"   placeholder= "Day" min="1"    max="31"   >/
                <input type="number" name="s_year"  placeholder= "Year" min="2015" max="2017" > 
            	<input type="number" name="s_hour"  placeholder= "Hour" min="1"    max="12"    >:
            	<input type="number" name="s_min"   placeholder= "Min" min="0"    max="59"    >
            	<input type="radio" name="s_midday" value="am" checked>am
            	<input type="radio" name="s_midday" value="pm">pm 
                    <span class="error"> * </span>
            </div>
            <div class="bodypart">
            	EndTime: 
                <input type="number" name="e_month" placeholder= "Month" min="1"    max="12"    >/
           		<input type="number" name="e_day"   placeholder= "Day" min="1"    max="31"   >/
            	<input type="number" name="e_year"  placeholder= "Year" min="2015" max="2017" > 
            	<input type="number" name="e_hour"  placeholder= "Hour" min="1"    max="12"    >:
            	<input type="number" name="e_min"   placeholder= "Min" min="0"    max="59"   >
            	<input type="radio" name="e_midday" value="am" checked>am
            	<input type="radio" name="e_midday" value="pm">pm 
                    <span class="error"> * <?php echo $timeErr; ?></span>
            </div>
            <div class="bodypart">
            	Priority: 
            	<form>
    				<input type="range" name="priority" min="1" max="9" value="0" oninput="this.form.display.value=this.value" />
    				<input type="number" name="display" min="1" max="9" value="0" oninput="this.form.priority.value=this.value" />
    			</form>
    		</div>
            <div class="bodypart">
            	Type: <input type="radio" name="class" value="PUBLIC" checked> public
            	<input type="radio" name="class" value="PRIVATE"> private
            	<input type="radio" name="class" value="CONFIDENTIAL"> confidential
            </div> 
        
            <div class="bodypart">
            	<input type="submit" name="button" value="generate">  
            </div>               
        </form>
=======
<!DOCTYPE html>
<html lang="en">
  <head>
    <?php headerText()?>
    <title> iCal | Home </title>
  </head>
>>>>>>> origin/master

  <body>

    <?php navBar(); ?>
    
    <div class="container">
        <h1> Home </h1>
        <h3>Create: <small> Used to create iCal files.</small></h3>
        <h3>FreeTime: <small> Used to find free time from a list of iCal Files.</small></h3>
    </div>

  
  
    <?php endText(); ?>
  </body>
</html>

