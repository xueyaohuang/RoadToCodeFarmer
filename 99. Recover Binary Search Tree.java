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
    
    TreeNode first = null;
    TreeNode second = null;
    TreeNode prev = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        // In order traversal to find the two elements
        inorder(root);
        // swap the value of the two nodes
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        // If first element has not been found, assign it to prevElement
        if (first == null && prev.val > root.val) {
            first = prev;
        }
        // If first element is found, assign the second element to the root
        if (first != null && prev.val > root.val) {
            second = root;
        }
        prev = root;
        
        inorder(root.right);
    }
}
