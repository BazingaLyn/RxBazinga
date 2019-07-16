package org.lyncc.bazinga.rx.bazinga.algorithm.answer.leetcode;




class Solution19 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);
//        ListNode listNode6 = new ListNode(6);
//        head.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        listNode4.next = listNode5;
//        listNode5.next = listNode6;

        printLinkNode(head);

        removeNthFromEnd(head,1);
        printLinkNode(head);
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode lastNode = dummyNode;
        ListNode preNode = dummyNode;
        while(n != 0){
            lastNode = lastNode.next;
            n--;
        }
        while(lastNode.next != null){
            lastNode = lastNode.next;
            preNode = preNode.next;
        }
        preNode.next = preNode.next.next;
        return dummyNode.next;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static void printLinkNode(ListNode node){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(node.val).append("->");
        while(node.next != null){
            stringBuffer.append(node.next.val).append("->");
            node = node.next;
        }
        System.out.println(stringBuffer.toString());
    }
}



