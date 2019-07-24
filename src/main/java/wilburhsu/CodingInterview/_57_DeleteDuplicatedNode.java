package wilburhsu.CodingInterview;

public class _57_DeleteDuplicatedNode {
    public ListNode deleteDuplication(ListNode pHead){
        if(pHead == null || pHead.next == null)
            return pHead;

        ListNode head = new ListNode(-1);
        head.next = pHead;
        ListNode tmp = head;//记录pre的前置结点
        ListNode pre = pHead;
        ListNode last = pHead.next;
        while(last != null) {
            if(pre.val != last.val){
                tmp = pre;
                pre = last;
                last = last.next;
            } else {
                while(last != null && pre.val == last.val)
                    last = last.next;
                pre = tmp;
                pre.next = last;
            }
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode listNode0 = new ListNode(1);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3_1 = new ListNode(3);
        ListNode listNode3_2 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5_1 = new ListNode(5);
        ListNode listNode5_2 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode0.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3_1;
        listNode3_1.next = listNode3_2;
        listNode3_2.next = listNode4;
        listNode4.next = listNode5_1;
        listNode5_1.next = listNode5_2;
        listNode5_2.next = listNode6;
        _57_DeleteDuplicatedNode deleteNode = new _57_DeleteDuplicatedNode();
        ListNode node = deleteNode.deleteDuplication(listNode0);
        //ListNode node = listNode0;
        while(node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}
