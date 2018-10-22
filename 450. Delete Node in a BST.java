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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            // 如果root是leaf，或者只有一个子节点，比较简单
            if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            } else { // 如果有2个子节点，需要找到root的inorder successor的值，把root的值改成inorder successor的值，然后右子树变成删除inorder successor后的右子树
                int inorderSuccessor = minRootValue(root.right);
                root.val = inorderSuccessor;
                root.right = deleteNode(root.right, inorderSuccessor);
            }
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
    
    // 找到一颗BST的最小值
    private int minRootValue(TreeNode root) {
        TreeNode node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.val;
    }
}
