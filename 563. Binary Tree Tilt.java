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
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        nodeSum(root, res);
        return res[0];
    }
    
    private int nodeSum(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = nodeSum(root.left, res);
        int right = nodeSum(root.right, res);
        res[0] += Math.abs(left - right);
        return root.val + left + right;
    }
}

class Solution {
    public int findTilt(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }
    
    private void dfs(TreeNode root, int[] res) {
        if (root == null) {
            return;
        }
        res[0] += nodeTile(root);
        dfs(root.left, res);
        dfs(root.right, res);
    }
    
    private int nodeTile(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = nodeSum(root.left);
        int right = nodeSum(root.right);
        return Math.abs(left - right);
    }
    
    private int nodeSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + nodeSum(root.left) + nodeSum(root.right);
    }
}
