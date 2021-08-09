package baobao.suanfa;

public class fanzhuanlianbiao {
    public ListNode reverseList(ListNode head){
        if(head == null || head.next ==null){
            return  head;
        }
        ListNode newNode = reverseList(head.next);
        head.next.next=head;
        head.next =null;
        return newNode;
    }
}

