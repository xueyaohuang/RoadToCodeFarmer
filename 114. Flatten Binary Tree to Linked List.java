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
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        flatten(left);
        flatten(right);
        if (left != null) {
            TreeNode node = left;
            while (node != null && node.right != null) {
                node = node.right;
            }
            node.right = right;
            root.right = left;
        }
        root.left = null;
    }
}

class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;  
        flatten(left);
        flatten(right);
        root.left = null;
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        cur.right = right;
    }
}

class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode node = root;
        while (node != null) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            if (left != null) {
                TreeNode cur = left;
                while (cur.right != null) {
                    cur = cur.right;
                }
                cur.right = right;
                node.left = null;
                node.right = left;
            }
            node = node.right;
        }
    }
}
