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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        int left = sumOfLeftLeaves(root.left);
        int right = sumOfLeftLeaves(root.right);
        return sum + left + right;
    }
}

class Solution {
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return sumLeft(root, null);
    }
    
    private int sumLeft(TreeNode root, TreeNode prev) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (prev != null && root == prev.left && root.left == null && root.right == null) {
            sum += root.val;
        }
        int left = sumLeft(root.left, root);
        int right = sumLeft(root.right, root);
        return sum + left + right;
    }
}
