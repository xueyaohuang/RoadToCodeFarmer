/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode plusOne(ListNode head) {
        ListNode reversed = reverseLinkedList(head);
        int sum = 1;
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (reversed != null) {
            sum += reversed.val;
            node.next = new ListNode(sum % 10);
            sum /= 10;
            reversed = reversed.next;
            node = node.next;
        }
        if (sum == 1) {
            node.next = new ListNode(1);
        }
        return reverseLinkedList(dummy.next);
        
    }
    private ListNode reverseLinkedList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }
}
