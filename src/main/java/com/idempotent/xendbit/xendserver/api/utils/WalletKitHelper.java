/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idempotent.xendbit.xendserver.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author aardvocate
 */
public class WalletKitHelper {
    public static Properties p = null;
    
    static {
        try {
            if (p == null) {
                p = new Properties();
                p.load(new FileInputStream(new File("/etc/xendbit/xendbit.properties")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}
