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
    public List<TreeNode> allPossibleFBT(int N) {
        if (N <= 0) {
            return new ArrayList<>();
        }
        
        List<TreeNode>[] dp = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            dp[i] = new ArrayList<>();
        }
        dp[1].add(new TreeNode(0));
        
        for (int numNode = 1; numNode <= N; numNode += 2) {
            for (int leftNode = 1; leftNode < numNode; leftNode += 2) {
                for (TreeNode left : dp[leftNode]) {
                    for (TreeNode right : dp[numNode - 1 - leftNode]) {
                        TreeNode root = new TreeNode(0);
                        root.left = left;
                        root.right = right;
                        dp[numNode].add(root);
                    }
                }
            }
        }
        return dp[N];
    }
}
