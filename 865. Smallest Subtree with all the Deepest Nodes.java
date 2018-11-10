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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        Map<TreeNode, Integer> depth = new HashMap<>();
        int[] maxDepth = new int[1];
        dfs(root, depth, maxDepth, 0);
        int max = maxDepth[0];
        return subtreeWithAllDeepestHelper(root, depth, max);
    }
    
    private void dfs(TreeNode root, Map<TreeNode, Integer> depth, int[] maxDepth, int level) {
        if (root == null) {
            return;
        }
        depth.put(root, level);
        maxDepth[0] = Math.max(level, maxDepth[0]);
        dfs(root.left, depth, maxDepth, level + 1);
        dfs(root.right, depth, maxDepth, level + 1);
    }
    
    private TreeNode subtreeWithAllDeepestHelper(TreeNode root, Map<TreeNode, Integer> depth, int max) {
        if (root == null || depth.get(root) == max) {
            return root;
        }
        TreeNode left = subtreeWithAllDeepestHelper(root.left, depth, max);
        TreeNode right = subtreeWithAllDeepestHelper(root.right, depth, max);
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}
