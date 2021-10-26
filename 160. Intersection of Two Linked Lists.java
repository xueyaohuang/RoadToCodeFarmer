/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pa = headA;
        ListNode pb = headB;
        // 要允许pa，pb到null，这样可以处理不相交的情况
        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }
        return pa;
    }
}

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode la = headA;
        ListNode lb = headB;
        int lenA = getLength(la);
        int lenB = getLength(lb);
        while (lenA < lenB) {
            lb = lb.next;
            lenB--;
        }
        while (lenA > lenB) {
            la = la.next;
            lenA--;
        }
        while (la != lb) {
            la = la.next;
            lb = lb.next;
        }
        return la;
    }
    
    private int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
    
}
