/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean[] isBalanced = new boolean[1];
        isBalanced[0] = true;
        depth(root, isBalanced);
        return isBalanced[0];
    }
    
    private int depth(TreeNode root, boolean[] isBalanced) {
        if (root == null) {
            return 0;
        }
        int left = depth(root.left, isBalanced);
        int right = depth(root.right, isBalanced);
        if (Math.abs(left - right) > 1) {
            isBalanced[0] = false;
        }
        return 1 + Math.max(left, right);
    }
}
