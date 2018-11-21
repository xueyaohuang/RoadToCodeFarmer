/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// space O(n)
// 不换node，只换node的值
// BST的node位置错了，inorder遇到的第一个prev.val > root.val的prev一定是val大的那个错的node，
// 第二个prev.val > root.val，这时root是val小的那个错的node
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

// Morris Travsal
// Space O(1)
// Time O(n)
// http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode predecessor = null;
        TreeNode prevNode = new TreeNode(Integer.MIN_VALUE);
        TreeNode cur = root;
        
        while (cur != null) { 
            if (cur.left == null) { 
                if (first == null && prevNode.val > cur.val) {
                    first = prevNode;
                }
                if (first != null && prevNode.val > cur.val) {
                    second = cur;
                }
                prevNode = cur;
                cur = cur.right; 
            } else { 
                // Find the inorder predecessor of current
                predecessor = cur.left; 
                while (predecessor.right != null && predecessor.right != cur) {
                    predecessor = predecessor.right;
                }
                // Make current as right child of its inorder predecessor
                if (predecessor.right == null) { 
                    predecessor.right = cur; 
                    cur = cur.left; 
                } else { // Revert the changes made in if part to restore the original tree i.e., fix the right child of predecssor
                    predecessor.right = null;  // recover the right pointer of this node to null
                    if (first == null && prevNode.val > cur.val) {
                        first = prevNode;
                    }
                    if (first != null && prevNode.val > cur.val) {
                        second = cur;
                    }
                    prevNode = cur;
                    cur = cur.right; 
                }
            }
        }
        
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
}
