<?php
        $op = $_POST["op"];
        $cmd;

        if((strcmp($op,"addUser")) == 0){
                $uname = $_POST["uname"];
                $password = $_POST["password"];
                $str = $_POST["str"];
                $cmd = "java -classpath /var/www/cgi-bin:/var/www/cgi-bin/mysql-connector-java-5.1.23-bin.jar smarttest.CentralServerAdapter $op $uname $password $str";
        }
        elseif ((strcmp($op,"getUser")) == 0)) {
                $uname = $_POST["uname"];
                $password = $_POST["password"];
                $cmd = "java -classpath /var/www/cgi-bin:/var/www/cgi-bin/mysql-connector-java-5.1.23-bin.jar smarttest.CentralServerAdapter $op $uname $password";
        }
        elseif ((strcmp($op,"getFromDeployedTests")) == 0)) {
                $pincode = $_POST["pincode"];
                $cmd = "java -classpath /var/www/cgi-bin:/var/www/cgi-bin/mysql-connector-java-5.1.23-bin.jar smarttest.CentralServerAdapter $op $pincode";
        }
        elseif ((strcmp($op,"addToDeployedTests")) == 0)) {
                $pincode = $_POST["pincode"];
                $str = $_POST["str"];
                $cmd = "java -classpath /var/www/cgi-bin:/var/www/cgi-bin/mysql-connector-java-5.1.23-bin.jar smarttest.CentralServerAdapter $op $str $pincode";
        }

        $str = shell_exec($cmd);
        print($str);
?>