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
/*

    If we remove any edge of the Binary Tree, we can forms: a sub-tree and its complement.
    Firstly, we dfs to calculate the total sum of all nodes in the Binary Tree.
    Then, we dfs in post order to calculate sum of each sub-tree in the Binary Tree.
        If the sum of current sub-tree is currSum, then its complement is totalSum - currSum.
        We update the answer if currSum * (totalSum - currSum) is greater than current answer.

*/
class Solution {
    long res;
    long sumOfAllNodes;
    public int maxProduct(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int mod = (int)Math.pow(10, 9) + 7;
        // Firstly, get total sum of all nodes in the Binary Tree
        sumOfAllNodes = sumFromHere(root);
        // Then dfs in post order to calculate sum of each subtree and its complement
        sumFromHere(root);
        return (int)(res % mod);
    }
    
    private int sumFromHere(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = sumFromHere(root.left);
        int right = sumFromHere(root.right);
        int sum = root.val + left + right;
        res = Math.max(res, (sumOfAllNodes - sum) * sum);
        return sum;
    }
}
