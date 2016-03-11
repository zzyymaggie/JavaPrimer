/**
 * Copyright © zzyymaggie. All Rights Reserved.
 */
package xyz.zzyymaggie.java_primer.tree;

public class Tree {
 
   public static void main(String[] args) {
       Node node1 = new Node(1, "node01", 0, 10); // when parentId = 0, it is a root, it should be only one root.
       Node node2 = new Node(2, "node02", 1);
       Node node3 = new Node(3, "node03", 1);
       Node node4 = new Node(4, "node04", 2);
       Node node5 = new Node(5, "node05", 4);
       node1.addChild(node2);
       node1.addChild(node3);
       node2.addChild(node4);
       node4.addChild(node5);
       System.out.println(node1.toString());
       }
}
