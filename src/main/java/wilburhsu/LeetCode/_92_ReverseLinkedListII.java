package wilburhsu.LeetCode;

public class _92_ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode current = dummy;
        for(int i = 0;i < m-1; i++)
            current = current.next;
        pre = current;
        ListNode start = current.next;
        for(int i = m; i <= n; i++)
            current = current.next;
        ListNode next = current.next;
        current.next = null;
        pre.next = reverse(start);
        start.next = next;

        return dummy.next;
    }

    public ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode current = head;
        while(current != null){
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node7 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        ListNodeTest listNodeTest = new ListNodeTest();
        ListNode l = listNodeTest.reverseBetween(node1, 1, 2);
        while (l != null) {
            System.out.print(l.val + " -> ");
            l = l.next;
        }
    }
}
