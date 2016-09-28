/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pcms.helper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *
 * @author wx.pan
 */
public class LogHelper {    
    static { 
        //try {
          //  String path = LogHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath();
           // path = java.net.URLDecoder.decode(path, "UTF-8");
           // PropertyConfigurator.configure(String.format("%s/%s", path, "config/log4j.properties"));
        //} catch (UnsupportedEncodingException ex) {           
          //  System.out.print(ex.getMessage());
       // }
    }
    
    public static Log getLog(Class clazz){    
          return LogFactory.getLog(clazz);
    }
}
