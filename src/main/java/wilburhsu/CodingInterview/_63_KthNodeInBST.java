package wilburhsu.CodingInterview;


import java.util.Stack;

public class _63_KthNodeInBST {
    int count = 0;
    TreeNode answer = null;
    public TreeNode KthNode(TreeNode pRoot,int k){
        helper(pRoot,k);
        return null;
    }

    private void helper(TreeNode node,int k){
        if(node == null || count > k)
            return;
        helper(node.left,k);
        count++;
        if(count == k)
            answer = node;
        helper(node.right,k);
    }

    public TreeNode KthNodeInBST(TreeNode pRoot,int k){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = pRoot;
        int count = 0;
        while(cur != null || !stack.isEmpty()){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                TreeNode node = stack.pop();
                count++;
                if(count == k)
                    return node;
                cur = node.right;
            }
        }
        return null;
    }
}
