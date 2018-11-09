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
    public boolean checkEqualTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        int sum = getSum(root);
        if (sum % 2 != 0) {
            return false;
        }
        boolean[] canEqualSplit = new boolean[1];
        checkEqualTreeHelper(root.left, sum / 2, canEqualSplit);
        checkEqualTreeHelper(root.right, sum / 2, canEqualSplit);
        return canEqualSplit[0];
    }
    
    private int checkEqualTreeHelper(TreeNode root, int target, boolean[] canEqualSplit) {
        if (root == null) {
            return 0;
        }
        int left = checkEqualTreeHelper(root.left, target, canEqualSplit);
        int right = checkEqualTreeHelper(root.right, target, canEqualSplit);
        int sum = root.val + left + right;
        if (sum == target) {
            canEqualSplit[0] = true;
        }
        return sum;
    }
    
    private int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + getSum(root.left) + getSum(root.right);
    }
}
