/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode even = head;
        ListNode odd = head.next;
        ListNode oddNode = odd;
        while (odd != null && odd.next != null) {
            even.next = even.next.next;
            odd.next = odd.next.next;
            even = even.next;
            odd = odd.next;  
        }
        even.next = oddNode;
        return head;
    }
}
