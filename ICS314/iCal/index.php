<?php include 'functions.php'; ?> 

<html>
    
<head>
    <title> iCal </title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
    
<body>
<div class="container">

    <?php checkInputErrors(); ?>
    <div class="header"><h1> iCal </h1></div>
    
    <form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>
        <div class="body">New Event:</div>
        <div class="body"><input type="text" name="" placeholder="Summary"> 
            <span class="error"> * <?php echo $summeryErr; ?></span></div>
            <div class="body"><input type="text" name="" placeholder="Location">
                <span class="error"> <?php echo $locationErr; ?></span></div>
        <div class="body">StartTime: 
            <input type="number" name="s_month" placeholder= "Month" min="1"    max="12"  >/
            <input type="number" name="s_day"   placeholder= "Day" min="1"    max="31"   >/
            <input type="number" name="s_year"  placeholder= "Year" min="2015" max="2017" > 
            <input type="number" name="s_hour"  placeholder= "Hour" min="1"    max="12"    >:
            <input type="number" name="s_min"   placeholder= "Min" min="0"    max="59"    >
            <input type="radio" name="s_midday" value="am" checked>am
            <input type="radio" name="s_midday" value="pm">pm 
                <span class="error"> * </span></div>
        <div class="body">EndTime: 
            <input type="number" name="e_month" placeholder= "Month" min="1"    max="12"    >/
            <input type="number" name="e_day"   placeholder= "Day" min="1"    max="31"   >/
            <input type="number" name="e_year"  placeholder= "Year" min="2015" max="2017" > 
            <input type="number" name="e_hour"  placeholder= "Hour" min="1"    max="12"    >:
            <input type="number" name="e_min"   placeholder= "Min" min="0"    max="59"   >
            <input type="radio" name="e_midday" value="am" checked>am
            <input type="radio" name="e_midday" value="pm">pm 
                <span class="error"> * <?php echo $timeErr; ?></span></div>
        <div class="body">Priority: <form>
    <input type="range" name="amountRange" min="1" max="9" value="0" oninput="this.form.amountInput.value=this.value" />
    <input type="number" name="amountInput" min="1" max="9" value="0" oninput="this.form.amountRange.value=this.value" /></form></div>
        <div class="body">Type: <input type="radio" name="class" value="PUBLIC" checked> public
              <input type="radio" name="class" value="PRIVATE"> private
              <input type="radio" name="class" value="CONFIDENTIAL"> confidential</div>
        <div class="body"><input type="submit" name="button" value="generate"></div>      
    </form>

    <?php generateIcsText(); ?>
    
    
    <?php //!TODO: exportIcsText(); ?>
           
           
           
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <div class="footer"><p> * Please use Safari or Chrome as your browser for best results </p></div>
</div>
</body>

</html>
