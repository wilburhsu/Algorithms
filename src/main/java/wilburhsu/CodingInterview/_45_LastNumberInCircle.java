package wilburhsu.CodingInterview;

public class _45_LastNumberInCircle {
    public int LastRemaining_Solution(int n, int m) {
        if(n <= 0 || m <= 0)
            return -1;
        //先构造循环链表
        ListNode head = new ListNode(0);
        ListNode index = head;
        ListNode tmp = null;
        for(int i = 1; i < n ; i++){//!!!Attention，循环起始值为1
            tmp = new ListNode(i);
            index.next = tmp;
            index = tmp;
        }
        tmp.next = head;//将第n-1个结点(也就是尾结点)指向头结点

        ListNode tmp2 = null;
        while(n != 1){
            tmp2 = head;
            //找到第m个结点的前驱
            for(int i = 1;i < m-1; i++)
                tmp2 = tmp2.next;
            //删除第m个结点：
            //将第m个结点的前驱指向第m个结点后面那个结点，tmp2表示第m个结点的前驱
            tmp2.next = tmp2.next.next;
            head = tmp2.next;//更新头结点
            n--;
        }
        return head.val;
    }
}

class ListNode{
    int val;
    ListNode next = null;

    public ListNode(int val){
        this.val = val;
    }
}
