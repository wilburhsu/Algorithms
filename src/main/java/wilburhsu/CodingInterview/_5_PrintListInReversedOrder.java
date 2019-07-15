package wilburhsu.CodingInterview;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 面试题5：从尾到头打印链表
 * */

public class _5_PrintListInReversedOrder {

    private ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if(listNode == null)
            return list;

        Stack<ListNode> stack = new Stack<>();
        while(listNode != null){
            stack.push(listNode);
            listNode = listNode.next;
        }

        while (!stack.isEmpty())
            list.add(stack.pop().val);

        return list;
    }

    public ArrayList<Integer> printListReverseByRecursion(ListNode listNode){
        if(listNode == null)
            return list;
        printListReverseByRecursion(listNode.next);
        list.add(listNode.val);
        return list;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        _5_PrintListInReversedOrder print = new _5_PrintListInReversedOrder();
        System.out.println(print.printListFromTailToHead(node1)); // 调用栈方法
        //printListReverseByRecursion(node1); // 调用递归方法
    }
}

