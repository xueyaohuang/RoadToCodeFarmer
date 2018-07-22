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
        TreeNode cur = root;
        TreeNode prev = null;
        TreeNode next = null;
        TreeNode temp = null;
        while (cur != null) {
            next = cur.left;
            cur.left = temp;
            temp = cur.right;
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
        TreeNode node = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return node;
    }
}
