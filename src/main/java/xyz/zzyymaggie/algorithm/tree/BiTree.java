package xyz.zzyymaggie.algorithm.tree;

import java.util.Stack;

public class BiTree {

    public static void main(String[] args) {
        Node[] node = new Node[10];//以数组形式生成一棵完全二叉树
        for (int i = 0; i < 10; i++) {
            node[i] = new Node(i);
        }
        for (int i = 0; i < 10; i++) {
            if (i * 2 + 1 < 10)
                node[i].left = node[i * 2 + 1];
            if (i * 2 + 2 < 10)
                node[i].right = node[i * 2 + 2];
        }
        preOrderRe(node[0]);
        System.out.println("");
    }

    public static void preOrderRe(Node biTree) {//递归实现
        System.out.print(biTree.value + " ");
        Node leftTree = biTree.left;
        if (leftTree != null) {
            preOrderRe(leftTree);
        }
        Node rightTree = biTree.right;
        if (rightTree != null) {
            preOrderRe(rightTree);
        }
    }

    public static void preOrder(Node biTree) {//非递归实现
        Stack<Node> stack = new Stack<Node>();
        while (biTree != null || !stack.isEmpty()) {
            while (biTree != null) {
                System.out.print(biTree.value + " ");
                stack.push(biTree);
                biTree = biTree.left;
            }
            if (!stack.isEmpty()) {
                biTree = stack.pop();
                biTree = biTree.right;
            }
        }
    }
}
