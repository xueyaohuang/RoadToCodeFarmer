/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode temp = root;
        while (temp != null) {
            TreeLinkNode inner = temp;
            while (inner != null) {
                if (inner.left != null) {
                    inner.left.next = inner.right;
                }
                if (inner.right != null && inner.next != null) {
                    inner.right.next = inner.next.left;
                }
                inner = inner.next;
            }
            temp = temp.left;
        }
    }
}
