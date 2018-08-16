/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
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
class Solution {
    public ListNode reverseList(ListNode head) {
        return helper(head, null);
    }
    private ListNode helper(ListNode head, ListNode pre) {
        if (head == null) {
            return pre;
        }
        ListNode temp = head.next;
        head.next = pre;
        return helper(temp, head);
    }
}
