package xyz.zzyymaggie.algorithm.tree;

public class FullBiTree {

    public static boolean isFull(Node head) {
        ReturnData allInfo = process(head, 0);
        return ((1 << allInfo.level) - 1 == allInfo.nums);
    }

    public static class ReturnData {
        public int level;
        public int nums;

        public ReturnData(int l, int n) {
            level = l;
            nums = n;
        }
    }

    public static ReturnData process(Node head, int level) {
        if (head == null) {
            return new ReturnData(level, 0);
        }
        ReturnData leftInfo = process(head.left, level + 1);
        ReturnData rightInfo = process(head.right, level + 1);
        int nums = leftInfo.nums + rightInfo.nums + 1;
        int deep = Math.max(leftInfo.level, rightInfo.level);
        return new ReturnData(deep, nums);
    }

    public static class ReturnType {
        public boolean isBalanced;
        public int height;

        public ReturnType(boolean isB, int hei) {
            isBalanced = isB;
            height = hei;
        }
    }

    public static void main(String[] args) {
        Node node = new Node(1);
        Node left = new Node(2);
        Node right = new Node(3);
        node.left = left;
        node.right = right;
        Node t = new Node(4);
        left.left = t;
        System.out.println(isFull(node));
    }

}
