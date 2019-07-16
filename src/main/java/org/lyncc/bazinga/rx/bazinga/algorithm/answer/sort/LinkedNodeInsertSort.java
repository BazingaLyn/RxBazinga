package org.lyncc.bazinga.rx.bazinga.algorithm.answer.sort;

public class LinkedNodeInsertSort {

    public ListNode insertionSortList(ListNode head) {
        if(head==null||head.next==null)return head;
        ListNode res = new ListNode(Integer.MIN_VALUE);
        res.next = head;
        ListNode ln = head;
        while(ln!=null){
            if(ln.next==null){
                return res.next;
            }

            ListNode nn = ln.next;

            if(nn.val<ln.val){
                ln.next = nn.next;
                ListNode tt = res;
                while(tt.next!=null){
                    if(nn.val<tt.next.val){
                        nn.next = tt.next;
                        tt.next = nn;
                        break;
                    }

                    tt = tt.next;
                }
            }else{
                ln=ln.next;
            }

        }
        return res.next;
    }

}


class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
