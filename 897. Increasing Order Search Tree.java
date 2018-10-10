/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 需要两个辅助node，做tree的题感觉没有抓手的时候，通常需要辅助node。
class Solution {
    TreeNode prev;
    TreeNode newRoot;
    public TreeNode increasingBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        increasingBST(root.left);
        if (prev != null) {
            prev.right = root;
            root.left = null;
        }
        prev = root;
        // 把inorder的第一个node设置成head。
        if (newRoot == null) {
            newRoot = root;
        }
        increasingBST(root.right);
        return newRoot;
    }
}
