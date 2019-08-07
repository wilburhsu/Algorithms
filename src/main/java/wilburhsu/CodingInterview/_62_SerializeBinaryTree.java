package wilburhsu.CodingInterview;

import java.util.LinkedList;
import java.util.Queue;

public class _62_SerializeBinaryTree {
    String Serialize(TreeNode root) {
        StringBuffer buffer = new StringBuffer();
        serialize(root, buffer);
        return buffer.toString();
    }

    public void serialize(TreeNode node, StringBuffer buffer) {
        if(node == null) {
            buffer.append("#,");
        }
        else {
            buffer.append(node.val + ",");
            serialize(node.left, buffer);
            serialize(node.right, buffer);
        }
    }

    TreeNode Deserialize(String str) {
        String [] res = str.split(",");
        Queue<String> queue = new LinkedList<>();
        for(int i = 0; i < res.length; i++){
            queue.offer(res[i]);
        }
        return deserialize(queue);
    }

    TreeNode deserialize(Queue<String> queue){
        String val = queue.poll();
        if(val.equals("#"))
            return null;
        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserialize(queue);
        node.right = deserialize(queue);
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
        System.out.println("" + res);
        TreeNode head = serialize.Deserialize(res);
        serialize.PreOrderUnRecur(head);
    }
}
