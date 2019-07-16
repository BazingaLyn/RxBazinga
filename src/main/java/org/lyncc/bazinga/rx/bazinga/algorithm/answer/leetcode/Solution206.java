package org.lyncc.bazinga.rx.bazinga.algorithm.answer.leetcode;

class Solution206 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur != null){
            ListNode nextTempNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTempNode;
        }
        return pre;
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
