/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

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
    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        
        List<TreeNode>[] dp = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = new ArrayList<>();
        }
        dp[0].add(null);
        
        for (int numNode = 1; numNode <= n; numNode++) {
            for (int leftNode = 0; leftNode < numNode; leftNode++) {
                for (TreeNode left : dp[leftNode]) {
                    for (TreeNode right : dp[numNode - 1 - leftNode]) {
                        TreeNode root = new TreeNode(leftNode + 1);
                        root.left = left;
                        root.right = shift(right, leftNode + 1);
                        dp[numNode].add(root);
                    }
                }
            }
        }
        return dp[n];
    }
    
    // right tree的结构不变，但是每个node的值都要加上 leftNode + 1。
    private TreeNode shift(TreeNode root, int val) {
        if (root == null) {
            return root;
        }
        
        TreeNode shiftedRoot= new TreeNode(root.val + val);
        shiftedRoot.left = shift(root.left, val);
        shiftedRoot.right = shift(root.right, val);
        
        return shiftedRoot; 
    }
}
