package org.lyncc.bazinga.rx.bazinga.algorithm.answer.leetcode;

public class Solution147 {

    public static void main(String[] args) {
       ListNode head = new ListNode(1);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(2);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(9);
        head.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode6;
        ListNode listNode = insertionSortList(head);
        printLinkNode(listNode);

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

    public static ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(-1);

        while(head != null){
            ListNode needInsertNode = head;
            head = head.next;
            needInsertNode.next = null;
            if(dummyHead.next == null){
                dummyHead.next = needInsertNode;
            }else{
                ListNode tmpNode = dummyHead;
                while(tmpNode.next != null && tmpNode.next.val < needInsertNode.val){
                    tmpNode = tmpNode.next;
                }
                needInsertNode.next = tmpNode.next;
                tmpNode.next = needInsertNode;
            }
        }

        ListNode resultNode = dummyHead.next;
        dummyHead.next = null;
        return resultNode;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}
