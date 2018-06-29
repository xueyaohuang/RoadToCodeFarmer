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
    public boolean isValidBST(TreeNode root) {
        return validHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean validHelper(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return validHelper(root.left, min, root.val) && validHelper(root.right, root.val, max);
    }
}

class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        TreeNode pre = null;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            }
            else {
                TreeNode node = stack.pop();
                if (pre != null && pre.val >= node.val) {
                    return false;
                }
                pre = node;
                p = node.right;
            }
        }
        return true;
    }
}




