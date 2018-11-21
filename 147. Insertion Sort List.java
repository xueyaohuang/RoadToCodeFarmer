/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = new ListNode(head.val);
        head = removeHead(head);
        while (head != null) {
            ListNode toInsert = new ListNode(head.val);
            if (node.val >= toInsert.val) {
                toInsert.next = node;
                node = toInsert;
            } else {
                ListNode move = node;
                while (move.next != null && move.next.val < toInsert.val) {
                    move = move.next;
                }
                insert(move, toInsert);
            }
            head = removeHead(head);
        }
        return node;
    }
    private ListNode removeHead(ListNode head) {
        if (head != null) {
            ListNode newHead = head.next;
            head.next = null;
            return newHead;
        }
        return null;
    }
    private void insert(ListNode l1, ListNode l2) {
        ListNode node = l1.next;
        l1.next = l2;
        l2.next = node;
    }
}
