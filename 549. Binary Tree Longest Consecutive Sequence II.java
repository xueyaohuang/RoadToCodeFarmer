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
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }
    
    // res[0]:从该node往下递增的最长序列
    // res[1]:从该node往下递减的最长序列
    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        
        if (root.left != null && root.left.val == root.val + 1) {
            left[0]++;
        } else {
            left[0] = 1;
        }
        
        if (root.left != null && root.left.val == root.val - 1) {
            left[1]++;
        } else {
            left[1] = 1;
        }
        
        if (root.right != null && root.right.val == root.val + 1) {
            right[0]++;
        } else {
            right[0] = 1;
        }
        
        if (root.right != null && root.right.val == root.val - 1) {
            right[1]++;
        } else {
            right[1] = 1;
        }
        
        max = Math.max(max, Math.max(left[0] + right[1] - 1, left[1] + right[0] - 1));
        return new int[]{Math.max(left[0], right[0]), Math.max(left[1], right[1])};
    }
}
