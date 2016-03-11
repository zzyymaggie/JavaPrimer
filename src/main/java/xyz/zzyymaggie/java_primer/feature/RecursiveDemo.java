/**
 * Copyright © zzyymaggie. All Rights Reserved.
 */
package xyz.zzyymaggie.java_primer.feature;

public class RecursiveDemo {

    public String aaa(String abc){
        if(abc == null || abc.length() < 1){
            return "";
        }
        return abc.charAt(abc.length() - 1)  + aaa(abc.substring(1)) + abc.charAt(0);
    }
    
   public static void main(String[] args){
        RecursiveDemo demo = new RecursiveDemo();
        System.out.println(demo.aaa("abcd"));
    }
}
