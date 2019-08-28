package wilburhsu;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;
    }
}

public class Main {
    public static void swap(TreeNode node1,TreeNode node2){
        TreeNode tmp = node1;
        node1 = node2;
        node2 = tmp;
        System.out.println("node1:" + node1.val);
        System.out.println("node2:" + node2.val);
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        swap(a,b);

        System.out.println("a:" + a.val);
        System.out.println("b:" + b.val);
    }
}
