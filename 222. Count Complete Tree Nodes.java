/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// time complexity O(lgn * lgn)
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        // if left == right， 左子树是perfect binary tree
        if (left == right) {
            return countNodes(root.right) + (1 << left);
        }
        // o/w， 右子树是perfect binary tree
        return countNodes(root.left) + (1 << right);
    }
    
    // O(lgn)
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + getHeight(root.left);
    }
}
