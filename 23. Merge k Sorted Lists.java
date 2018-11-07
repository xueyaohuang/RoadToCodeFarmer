/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int len = lists.length;
        return mergeKListsHelper(lists, 0, len - 1);
    }
    
    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        if (l1 != null) {
            node.next = l1;
        }
        if (l2 != null) {
            node.next = l2;
        }
        return dummy.next;
    }
    
    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            ListNode l1 = mergeKListsHelper(lists, start, mid);
            ListNode l2 = mergeKListsHelper(lists, mid + 1, end);
            return mergeTwoList(l1, l2);
        }
        return lists[start];
    }
}
