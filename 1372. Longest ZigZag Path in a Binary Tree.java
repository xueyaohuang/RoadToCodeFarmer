/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int max = 0;
    public int longestZigZag(TreeNode root) {
        // actually we just need one of them
        maxFromHere(root, true, 0);
        maxFromHere(root, false, 0);
        return max;
    }
    
    private void maxFromHere(TreeNode root, boolean isLeftNode, int steps) {
        if (root == null) {
            return;
        }
        // update max step sofar
        max = Math.max(max, steps); 
        if (isLeftNode) {
            // if current root is left node, and we keep going left, we should give up the steps before current root. Here we update steps to 1, it means root to root.left is one step. Don't worry if root.left is null, since we check null at the beginning, in the next recursion max won't be updated.
            maxFromHere(root.left, true, 1);
            maxFromHere(root.right, false, steps + 1);
        } else {
            maxFromHere(root.left, true, steps + 1);
            maxFromHere(root.right, false, 1);
        }
    }
}
