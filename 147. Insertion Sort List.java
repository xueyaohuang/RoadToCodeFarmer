/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
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
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE); // 需要把dummy.val设置成Integer.MIN_VALUE，这样第一次head.val 必定 >= tail.val，能把dummy和head连上
        ListNode tail = dummy;
        while (head != null) {
            if (head.val >= tail.val) { // 这种情况是升序，跟直接把tail的next接到head上，move head到head.next
                tail.next = head;
                head = head.next;
                tail = tail.next;
                tail.next = null;
            } else { //这种情况需要把head插入到合适的位置，首先要找到合适的位置。插入一个node，要知道插入点的前后node，用prev，next记录待插入点的前后位置。从头（dummy）开始找待插入的位置。
                ListNode prev = dummy;
                ListNode next = dummy.next;
                // 找到应该插入的位置
                while (head.val >= next.val) {
                    prev = next;
                    next = next.next;
                }
                // 插入head
                ListNode temp = head.next;
                head.next = next;
                prev.next = head;
                head = temp;
            }
        }
        return dummy.next;
    }
}

// 看不懂的垃圾算法
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
