package wilburhsu.LeetCode;
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class _21_MergeTwoSortedLists {
    //首先new了一个结点对象，然后head和prev都是指向该结点的引用
    //当prev.next指向l1后，该结点的next指向的结点也发生了改变，即head.next也指向l1
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode prev = head;

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;
            }else{
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        prev.next = l1 == null ? l2 : l1;
        return head.next;
    }
}
