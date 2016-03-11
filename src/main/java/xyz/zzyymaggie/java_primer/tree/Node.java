/**
 * Copyright © zzyymaggie. All Rights Reserved.
 */
package xyz.zzyymaggie.java_primer.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node{
    private int id;
    private String name;
    private int parentId;
    private List<Node> children;
    private Node parentNode;
    private int sales;
    
    public Node(){
        this.children = new ArrayList<Node>();
    }
    
    public Node(int id, String name, int parentId, int sales){
        this();
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.sales = sales;
    }
    
    public Node(int id, String name, int parentId){
        this(id, name, parentId, 1);
    }
    
    public void addChild(Node node){
        this.children.add(node);
        node.setParentNode(this);
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder("");
        int level = getLevel();
        for (int i=0; i<level; i++)  {
            str.append("    ");
        }
        if (level > 0)  str.append("|-- ");
        str.append("level: ").append(level)
        .append("--ID:").append(id)
        .append("--name-").append(name)
        .append("--value:").append(sales)
        .append("\n");
        if(children != null){
            Iterator<Node> itr = children.iterator();
            while(itr.hasNext()){
                Node node = (Node)itr.next();
                str.append(node.toString());
            }
        }
        return str.toString();
    }
    
    public int getLevel() {
        int level = 0;
        if(this.parentId == 0)
            return 0;
        Node node = this.parentNode;
        while(node != null){
            level++;
            node = node.getParentNode();
        }
        return level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public int getParentId(){
        return parentId;
    }
    
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}