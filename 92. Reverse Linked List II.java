/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m == n) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        for (int i = 1; i < m; i++) {
            head = head.next;
        }
        ListNode premNode = head;
        ListNode mNode = head.next;
        ListNode nNode = head.next;
        ListNode postnNode = nNode.next;
        // 一边移动一边reverse
        for (int i = m; i < n; i++) {
            ListNode next = postnNode.next;
            postnNode.next = nNode;
            nNode = postnNode;
            postnNode = next;
        }
        premNode.next = nNode;
        mNode.next = postnNode;
        return dummy.next;
    }
}

class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        for (int i = 1; i <= right - left; i++) {
            fast = fast.next;
        }
        for (int i = 1; i < left; i++) {
            prev = slow;
            slow = slow.next;
            fast = fast.next;
        }
        ListNode next = fast.next;
        fast.next = null;
        ListNode reversed = reverse(slow);
        if (prev != null) {
            prev.next = reversed;
        }
        slow.next = next;
        return left == 1 ? reversed : head;
    }
    
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
