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
    int len = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root != null) {
            maxAtThisNode(root);
        }
        return len;
    }
    private int maxAtThisNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = node.left == null ? 0 : 1 + maxAtThisNode(node.left);
        int right = node.right == null ? 0 : 1 + maxAtThisNode(node.right);
        len = Math.max(len, left + right);
        return Math.max(left, right);
    }
}
// 与第687题对比，687还有附加的val相等的条件，所以要分left，right，rootLeft， rootRight四种情况。

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int passRoot = depth(root.left) + depth(root.right);
        int lDia = diameterOfBinaryTree(root.left);
        int rDia = diameterOfBinaryTree(root.right);
        return Math.max(passRoot, Math.max(lDia, rDia));
    }
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}

