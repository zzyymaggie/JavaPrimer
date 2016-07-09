/**
 * Copyright © zzyymaggie. All Rights Reserved.
 */
package xyz.zzyymaggie.java_primer.feature;

/**
 * 03/04/16 This is the base Class. It has an protected filed.
 * @author sozhang
 *
 */
public abstract class Agent {
    protected String conStr = "Hello Agent!";
    
    protected void display(){
        System.out.println(conStr);
    }
}
