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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return constructMaxTreeHelper(nums, 0, nums.length - 1);
        
    }
    
    private int findMaxId(int[] nums, int start, int end) {
        int maxId = start;
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > nums[maxId]) {
                maxId = i;
            } 
        }
        return maxId;
    }
    
    private TreeNode constructMaxTreeHelper(int[] nums, int start, int end) {
        if (nums == null || start > end) {
            return null;
        }
        int maxId = findMaxId(nums, start, end);
        TreeNode root = new TreeNode(nums[maxId]);
        root.left = constructMaxTreeHelper(nums, start, maxId - 1);
        root.right = constructMaxTreeHelper(nums, maxId + 1, end);
        return root;
    }
}
