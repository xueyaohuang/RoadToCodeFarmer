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
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int passRoot = depth(root.left) + depth(root.right);
        int lDia = diameterOfBinaryTree(root.left);
        int rDia = diameterOfBinaryTree(root.right);
        return Math.max(passRoot, Math.max(lDia, rDia));
    }
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}
