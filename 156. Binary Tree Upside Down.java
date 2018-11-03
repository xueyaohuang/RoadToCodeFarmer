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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        
        TreeNode cur = root;
        TreeNode next = null;
        TreeNode prev = null;
        TreeNode prevRight = null;
        
        while (cur != null) {
            next = cur.left;
            cur.left = prevRight;
            prevRight = cur.right;
            cur.right = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}

class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        TreeNode node = upsideDownBinaryTree(root.left); // 先一直怼到最底下的left，再慢慢往上操作左右
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return node;
    }
}
