package wilburhsu.CodingInterview;

public class _56_EntryNodeInListLoop {
    public ListNode EntryNodeOfLoop(ListNode pHead){
        if(pHead == null || pHead.next == null)
            return null;
        ListNode slow = pHead,fast = pHead;
        do {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast)
                break;
        } while(fast.next != null || slow != fast);

        int count = 0;
        do{
            fast = fast.next;
            count++;
        }while (slow != fast);

        slow = pHead;
        fast = pHead;
        for(int i = 0;i < count;i++)
            fast = fast.next;

        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode listNode0 = new ListNode(0);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        listNode0.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        listNode6.next = listNode3;
        _56_EntryNodeInListLoop listLoop = new _56_EntryNodeInListLoop();
        System.out.println(listLoop.EntryNodeOfLoop(listNode0).val);
    }
}



