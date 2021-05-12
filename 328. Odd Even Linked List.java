/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }
        // 把even接到odd后面
        odd.next = evenHead;
        return head;
    }
}

class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode dummy = head.next;
        while (even != null && even.next != null) {
            if (odd.next.next != null) {
                odd.next = odd.next.next;
                if (odd.next != null) {
                    odd = odd.next;
                }
            }
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = dummy;
        return head;
    }
}
