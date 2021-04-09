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
// Just inorder traversal
class Solution {
    
    private int res = Integer.MAX_VALUE;
    private TreeNode prev = null;
    
    public int minDiffInBST(TreeNode root) {
        dfs(root);
        return res;
    }
    
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (prev != null) {
            res = Math.min(res, root.val - prev.val);
        }
        prev = root;
        dfs(root.right);
    }
}

class Solution {
    public int minDiffInBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        TreeNode prev = null;
        int res = Integer.MAX_VALUE;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode cur = stack.pop();
                if (prev != null) {
                    res = Math.min(res, cur.val - prev.val);
                }
                prev = cur;
                node = cur.right;
            }
        }
        return res;
    }
}
