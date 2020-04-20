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
    int res = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getDepth(root, 1);
        return res;
    }
    
    private void getDepth(TreeNode root, int cur) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res = Math.min(res, cur);
            return;
        }
        getDepth(root.left, cur + 1);
        getDepth(root.right, cur + 1);
    }
}

class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null || root.right == null) {
            return 1 + Math.max(minDepth(root.left), minDepth(root.right));
        }
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        
    }
}
