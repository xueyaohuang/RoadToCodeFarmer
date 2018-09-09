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

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<ListNode> stack1 = new ArrayDeque<>();
        Deque<ListNode> stack2 = new ArrayDeque<>();
        ListNode list = new ListNode(0);
        int sum = 0;
        while (l1 != null) {
            stack1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2);
            l2 = l2.next;
        }
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                sum += stack1.pop().val;
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop().val;
            }
            list.val = sum % 10;
            sum /= 10;
            ListNode head = new ListNode(sum);
            head.next = list;
            list = head;
        }
        return list.val == 0 ? list.next : list;
    }
}
