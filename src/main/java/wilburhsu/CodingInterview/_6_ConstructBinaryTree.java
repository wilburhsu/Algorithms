package CodingInterview;

public class _6_ConstructBinaryTree {
    private class TreeNode{
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int val){
            this.val = val ;
        }
    }

    public static void main(String[] args) {
        _6_ConstructBinaryTree construct = new _6_ConstructBinaryTree();
        // 二叉树的先序序列
        int[] preOrder = { 1, 2, 3, 4, 5, 6, 7};
        // 二叉树的中序序列
        int[] inOrder = { 3, 2, 4, 1, 6, 5, 7};
        TreeNode root = construct.reConstruct(preOrder, inOrder);
        construct.printPostOrder(root); // 后序打印二叉树
    }

    public TreeNode reConstruct(int[] preOrder,int[] inOrder){
        if(preOrder == null || inOrder == null)
            return null;

        if (preOrder.length == 0 || preOrder.length == 0){
            return null;
        }

        if(preOrder.length != inOrder.length)
            return null;

        TreeNode root = new TreeNode(preOrder[0]);//前序遍历的第一个值是根结点
        root.left = null;
        root.right = null;

        //左子树结点数
        int leftNum = 0;
        for (int i = 0;i < inOrder.length;i++){
            if(root.val == inOrder[i])
                break;
            else
                leftNum++;
        }

        //右子树结点数
        int rightNum = preOrder.length-1-leftNum;

        //递归构建左子树
        if(leftNum > 0){
            int[] leftPreOrder = new int[leftNum];
            int[] leftInOrder = new int[leftNum];
            for(int i = 0;i<leftNum;i++){
                leftPreOrder[i] = preOrder[i+1];
                leftInOrder[i] = inOrder[i];
            }
            TreeNode leftRoot = reConstruct(leftPreOrder,leftInOrder);
            root.left = leftRoot;
        }

        //递归构建右子树
        if(rightNum > 0){
            int[] rightPreOrder = new int[rightNum];
            int[] rightInOrder = new int[rightNum];
            for(int i = 0;i<rightNum;i++){
                rightPreOrder[i] = preOrder[leftNum + 1 + i];
                rightInOrder[i] = inOrder[leftNum + 1 + i];
            }
            TreeNode rightRoot = reConstruct(rightPreOrder,rightInOrder);
            root.right = rightRoot;
        }
        return root;
    }

    public void printPostOrder(TreeNode root){
        if(root != null){
            printPostOrder(root.left);
            printPostOrder(root.right);
            System.out.println(root.val);
        }
    }

}
