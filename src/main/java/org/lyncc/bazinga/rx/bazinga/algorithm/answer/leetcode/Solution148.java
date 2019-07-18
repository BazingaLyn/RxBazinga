package org.lyncc.bazinga.rx.bazinga.algorithm.answer.leetcode;

public class Solution148 {

    public ListNode sortList2(ListNode head) {
        quickSort(head,null);
        return head;
    }
    public void quickSort(ListNode head,ListNode end){
        if(head!=end && head.next!=end){
            ListNode q=partition(head,end);
            quickSort(head,q);
            quickSort(q.next,end);
        }
    }
    public ListNode partition(ListNode head,ListNode end){
        ListNode p=head;
        ListNode cur=head.next;
        int key=head.val;
        while(cur!=end){
            if(cur.val<=key){
                p=p.next;
                int tmp=p.val;
                p.val=cur.val;
                cur.val=tmp;
            }
            cur=cur.next;
        }
        int tmp=head.val;
        head.val=p.val;
        p.val=tmp;
        return p;

    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

}
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}