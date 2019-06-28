package wilburhsu.CodingInterview;

public class _39_TreeDepth {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int TreeDepth(TreeNode root) {
        if(root == null)
            return 0;
        int leftDepth = TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);

        return (leftDepth > rightDepth) ? (leftDepth + 1) : (rightDepth + 1);
    }
}
