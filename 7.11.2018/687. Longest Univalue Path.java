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
    int len = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root != null) {
            longestUnivaluePathHelper(root);
        }
        return len;
    }
    private int longestUnivaluePathHelper(TreeNode root) {
        int left = root.left == null ? 0 : longestUnivaluePathHelper(root.left);
        int right = root.right == null ? 0 : longestUnivaluePathHelper(root.right);
        int rootLeft = root.left != null && root.val == root.left.val ? 1 + left : 0;
        int rootRight = root.right != null && root.val == root.right.val ? 1 + right : 0;
        len = Math.max(len, rootLeft + rootRight);
        return Math.max(rootLeft, rootRight);
    }
}
