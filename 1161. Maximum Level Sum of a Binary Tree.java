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
    int maxLevel = 0;
    public int maxLevelSum(TreeNode root) {
        Map<Integer, Integer> levelSum = new HashMap<>();
        getLevelSum(root, levelSum, 1);
        int maxSum = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 1; i <= maxLevel; i++) {
            int sum = levelSum.get(i);
            if (sum > maxSum) {
                res = i;
                maxSum = sum;
            }
        }
        return res;
    }
    
    private void getLevelSum(TreeNode root, Map<Integer, Integer> map, int level) {
        if (root == null) {
            return;
        }
        maxLevel = Math.max(maxLevel, level);
        map.put(level, map.getOrDefault(level, 0) + root.val);
        getLevelSum(root.left, map, level + 1);
        getLevelSum(root.right, map, level + 1);
    }
}
