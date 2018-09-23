/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) {
            return head;
        }
        ListNode cur = head;
        int count = 0;
        // find the first node in the next group. after while loop, cur is the next (k + 1)th node
        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }
        if (count == k) {  // if k+1 node is found
            cur = reverseKGroup(cur, k);  // reverse list with k+1 node as head
            // reverse current k-group: 
            ListNode prev = cur;
            while (count-- > 0) {
                ListNode temp = head.next;
                head.next = prev;
                prev = head;
                head = temp;
            }
            return prev;
        }
        return head;
    }
}
