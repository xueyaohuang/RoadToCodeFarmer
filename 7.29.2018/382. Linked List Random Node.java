/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

// reservoir sampling
class Solution {
    
    Random rand;
    ListNode curr;

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        curr = head;
        rand = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        ListNode node = curr;
        ListNode res = null;
        int n = 1;
        while (node != null) {
            if (rand.nextInt(n) == 0) {
                res = node;
            }
            node = node.next;
            n++;
        }
        return res.val;
    }
}
 
 
class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    ListNode node;
    ListNode moveNode;
    int listLength;
    
    public Solution(ListNode head) {
        node = head;
        moveNode = node;
        listLength = 0;
        while (moveNode != null) {
            moveNode = moveNode.next;
            listLength++;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        Random rand = new Random();
        moveNode = node;
        int nodeNumb = rand.nextInt(listLength);
        while (nodeNumb > 0) {
            moveNode = moveNode.next;
            nodeNumb--;
        }
        return moveNode.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
