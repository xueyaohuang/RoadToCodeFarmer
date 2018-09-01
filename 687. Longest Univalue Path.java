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
        if (root == null) {
            return 0;
        }
        longestUnivaluePathHelper(root, root.val);
        return len;
    }
    private int longestUnivaluePathHelper(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        int left = longestUnivaluePathHelper(root.left, root.val);
        int right = longestUnivaluePathHelper(root.right, root.val);
        len = Math.max(len, left + right);
        if (root.val == val) {
            return Math.max(left, right) + 1;
        }
        return 0;
    }
}

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
