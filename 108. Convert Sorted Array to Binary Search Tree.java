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
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }
    
    private TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int rootIdx = (start + end) / 2;
        TreeNode root = new TreeNode(nums[rootIdx]);
        root.left = sortedArrayToBSTHelper(nums, start, rootIdx - 1);
        root.right = sortedArrayToBSTHelper(nums, rootIdx + 1, end);
        return root;
    }
}
