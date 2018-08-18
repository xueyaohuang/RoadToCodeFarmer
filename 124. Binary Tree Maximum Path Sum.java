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
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxDownPath(root);
        return max;
    }
    // 返回从当前node往下最大的sum，sum包括当前node的值
    private int maxDownPath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, maxDownPath(root.left));
        int right = Math.max(0, maxDownPath(root.right));
        max = Math.max(max, left + root.val + right);
        return Math.max(left, right) + root.val;
    }
}
