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
//same as LC.783
class Solution {
    
    private int res = Integer.MAX_VALUE;
    private TreeNode prev = null;
    
    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return res;
    }
    
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev != null) {
            res = Math.min(res, root.val - prev.val);
        }
        prev = root;
        dfs(root.right);
    }
}
