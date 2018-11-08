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
        boolean[] ret = new boolean[1];
        int sum = getSum(root);
        if (sum % 2 != 0) {
            return false;
        }
        
        helper(root, ret, sum/2, root);
        return ret[0];
    }
    private int getSum (TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int left = getSum(root.left);
        int right = getSum(root.right);
        
        return left+right+root.val;
        
    } 
        
    private int helper(TreeNode root, boolean[] ret, int target, TreeNode myRoot) {
        if (root == null) {
            return 0;
        }
        
        if (ret[0] == true) {
            return 0;
        }
        
        int sumLeft = helper(root.left, ret, target, myRoot);
        int sumRight = helper(root.right, ret, target, myRoot);
        
        if (root.val + sumRight + sumLeft == target && root != myRoot) {
            ret[0] = true;
        }

        
        return (root.val + sumLeft + sumRight);
    } 
}
