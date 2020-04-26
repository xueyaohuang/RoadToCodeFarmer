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
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return bstFromPreorderHelper(preorder, new int[]{0}, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private TreeNode bstFromPreorderHelper(int[] preorder, int[] rootIdx, long min, long max) {
        if (rootIdx[0] >= preorder.length) {
            return null;
        }
        if (preorder[rootIdx[0]] <= min || preorder[rootIdx[0]] >= max) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[rootIdx[0]]);
        rootIdx[0]++;
        root.left = bstFromPreorderHelper(preorder, rootIdx, min, root.val);
        root.right = bstFromPreorderHelper(preorder, rootIdx, root.val, max);
        return root;
    }
}
