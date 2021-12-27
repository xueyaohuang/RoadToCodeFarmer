// post order traversal O(N)
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
class Solution {
    double avg = 0;
    public double maximumAverageSubtree(TreeNode root) {
        sumAndNum(root);
        return avg;
    }
    
    private int[] sumAndNum(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int sum = root.val, num = 1;
        int[] left = sumAndNum(root.left);
        int[] right = sumAndNum(root.right);
        sum += left[0] + right[0];
        num += left[1] + right[1];
        avg = Math.max(avg, (double)sum / num);
        return new int[]{sum, num};
    }
}
