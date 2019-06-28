package wilburhsu.CodingInterview;

import java.util.ArrayList;
/**
 public class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
     this.val = val;
     }
 }
 */
public class _25_PathInTree {

    ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root == null)
            return list;
        find(root,target);
        return list;
    }

    public void find(TreeNode node,int target){
        path.add(node.val);
        if(node.val == target && node.left == null && node.right == null)
            list.add(new ArrayList(path));
        if(node.left != null)
            find(node.left,target-node.val);
        if(node.right != null)
            find(node.right,target-node.val);
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        TreeNode node10 = new TreeNode(10);
        TreeNode node5 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node12 = new TreeNode(12);

        node10.left = node5;
        node10.right = node12;
        node5.left = node4;
        node5.right = node7;
        node4.left = null;
        node4.right = null;
        node7.left = null;
        node7.right = null;
        node12.left = null;
        node12.right = null;

        _25_PathInTree pathInTree = new _25_PathInTree();
        System.out.println(pathInTree.FindPath(node10,22));
    }
}


