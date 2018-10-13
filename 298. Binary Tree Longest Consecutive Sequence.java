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
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return longestConsecutiveHelper(root, root.val, 0);
    }
    
    private int longestConsecutiveHelper(TreeNode root, int target, int curMax) {
        if (root == null) {
            return curMax;
        }
        if (root.val == target) {
            curMax++;
        } else {
            curMax = 1;
        }
        int left = longestConsecutiveHelper(root.left, root.val + 1, curMax);
        int right = longestConsecutiveHelper(root.right, root.val + 1, curMax);
        return Math.max(curMax, Math.max(left, right));
    }
}
