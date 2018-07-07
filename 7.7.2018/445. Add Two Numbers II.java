/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ll1 = reverse(l1);
        ListNode ll2 = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        int sum = 0;
        while (ll1 != null || ll2 != null) {
            if (ll1 != null) {
                sum += ll1.val;
                ll1 = ll1.next;
            }
            if (ll2 != null) {
                sum += ll2.val;
                ll2 = ll2.next;
            }
            node.next = new ListNode(sum % 10);
            sum /= 10;
            node = node.next;
        }
        if (sum != 0) {
            node.next = new ListNode(1);
        }
        return reverse(dummy.next);
    }
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
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
