/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  This class will have all the server methods
 */
public class CentralServerAdapter {
    public static void main(String[] args){
        
        String operation = args[0];
        
        switch(operation){
            case "getFromDeployedTests" :
                System.out.println(getFromDeployedTests(args[1]));
                break;
            case "addToDeployedTests" :
                addToDeployedTests(args[1],args[2]);
                System.out.println("Success!");
                break;
            case "getUser" :
                System.out.println(getUser(args[1],args[2]));
                break;
            case "addUser" :
                addUser(args[1],args[2],args[3]);
                System.out.println("Success!");
                break;
            case "updateUser" :
                System.out.println(updateUser(args[1],args[2],args[3]));
                break;
            case "updateTest" :
                System.out.println(updateTest(args[1],args[2]));
                break;
            case "resetPassword" :
                System.out.println(resetPassword(args[1]));
                break;
            case "uploadLearningOutcomes" :
                System.out.println(updateLO(args[1]));
                break;
            case "getLearningOutcomes" :
                System.out.println(getLO());
                break;
        }
    }
    
    protected static String getLO(){   
        String qry = "SELECT byteStr FROM learningOutcomes";
        String objStr = Utils.execQuery(qry);
        return objStr;
    }
    
    protected static String updateLO(String loString){
        String qry = "UPDATE learningOutcomes SET byteStr='"+loString+"'";
        Utils.execNonQuery(qry);
        return "Success!";
    }
    
    protected static String updateTest(String testString, String pincode){
        String qry = "UPDATE deployedTests SET byteStr='"+testString+"' WHERE pincode='"+pincode+"'";
        Utils.execNonQuery(qry);
        return "Success!";
    }
    
    protected static void addUser(String username,String password,String userString){
        String qry = "INSERT INTO users(uname,password,byteStr) VALUES ('"+username+"','"+password+"','"+userString+"')";
        Utils.execNonQuery(qry);
    }
    
    protected static String getUser(String username, String password){
        String query = "SELECT id FROM admins WHERE uname='"+username+"' AND password='"+password+"'";
        String str = Utils.execQuery(query);
        if (str != null){
            return "admin";
        }
        else {
            String qry = "SELECT byteStr FROM users WHERE uname='"+username+"' AND password='"+password+"'";
            String objStr = Utils.execQuery(qry);
            return objStr;
        }
    }
    
    protected static void addToDeployedTests(String testString, String pincode){   
        String qry = "INSERT INTO deployedTests(pincode,byteStr) VALUES ('"+pincode+"','"+testString+"')";
        Utils.execNonQuery(qry);
    }
    
    protected static String getFromDeployedTests(String pincode){   
        String qry = "SELECT byteStr FROM deployedTests WHERE pincode='"+pincode+"'";
        String objStr = Utils.execQuery(qry);
        return objStr;
    }
    
    protected static String updateUser(String username, String password, String userString){
        String qry = "UPDATE users SET byteStr='" + userString + "' WHERE uname='"+username+"' AND password='"+password+"'";
        Utils.execNonQuery(qry);
        return "Success!";
    }
    
    protected static String resetPassword(String username){
        String qry = "SELECT byteStr FROM users WHERE uname='"+username+"'";
        String objStr = Utils.execQuery(qry);
        if(objStr != null){
            //objStr = objStr.substring(0,objStr.length()-1);
            User tempUser = (User)Utils.toObj(objStr);
            tempUser.Password = "csc190";
            
            String str = Utils.toStr(tempUser);
            try {
                str = URLDecoder.decode(str, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                System.out.println(ex);
            }
            
            String qry1 = "UPDATE users SET password='csc190', byteStr='" + str + "' WHERE uname='"+username+"'";
            Utils.execNonQuery(qry1);
            
            return "Success!";
        }
        else {
            return "No user with this username exists!";
        }
        
    }
}
