package wilburhsu.CodingInterview;

import java.io.Console;

public class _62_SerializeBinaryTree {
    String Serialize(TreeNode root) {
        StringBuilder builder = new StringBuilder();
        pre(root, builder);
        return builder.toString();
    }

    public void pre(TreeNode node, StringBuilder builder) {
        if(node == null) {
            builder.append("#,");
        }
        else {
            builder.append(node.val + ",");
            pre(node.left, builder);
            pre(node.right, builder);
        }
    }

    int index = -1;
    TreeNode Deserialize(String str) {
        String[] arr = str.split(",");
        TreeNode node = null;
        index++;
        if(!arr[index].equals("#")) {
            node = new TreeNode(Integer.valueOf(arr[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);

        }
        return node;
    }

    //前序遍历重建后的二叉树，验证反序列化结果
    public void PreOrderUnRecur(TreeNode head){
        if (head == null) return;
        System.out.print(head.val + " ");
        PreOrderUnRecur(head.left);
        PreOrderUnRecur(head.right);
    }

    public static void main(String[] args) {
        TreeNode A = new TreeNode(1);
        TreeNode B = new TreeNode(2);
        TreeNode C = new TreeNode(3);
        TreeNode D = new TreeNode(4);
        TreeNode E = new TreeNode(5);
        TreeNode F = new TreeNode(6);
        TreeNode G = new TreeNode(7);

        A.left = B;
        A.right = C;
        B.left = D;
        D.right = G;
        C.left = E;
        C.right = F;

        _62_SerializeBinaryTree serialize = new _62_SerializeBinaryTree();
        String res= serialize.Serialize(A);
        System.out.println("<------------>" + res);
        TreeNode head = serialize.Deserialize(res);
        serialize.PreOrderUnRecur(head);
    }
}
