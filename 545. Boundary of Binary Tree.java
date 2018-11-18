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
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        List<Integer> res = new ArrayList<>();
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return res;
        }
        
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();
        
        left.add(root.val);
        if (root.left != null) {
            getLeftBoundary(root.left, left);
        }
        
        right.add(root.val);
        if (root.right != null) {
            getRightBoundary(root.right, right);
        }
        
        getLeaves(root, leaves);
        
        if (root.left != null) {
            left.remove(left.size() - 1);
        }
        if (root.right != null) {
            right.remove(right.size() - 1);
        }
        Collections.reverse(right);
        right.remove(right.size() - 1);
        res.addAll(left);
        res.addAll(leaves);
        res.addAll(right);
        return res;
    }
    
    private void getLeftBoundary(TreeNode root, List<Integer> left) {
        if (root == null) {
            return;
        }
        left.add(root.val);
        if (root.left != null) {
            getLeftBoundary(root.left, left);
        } else if (root.right != null) {
            getLeftBoundary(root.right, left);
        }
    }
    
    private void getRightBoundary(TreeNode root, List<Integer> right) {
        if (root == null) {
            return;
        }
        right.add(root.val);
        if (root.right != null) {
            getRightBoundary(root.right, right);
        } else if (root.left != null) {
            getRightBoundary(root.left, right);
        }
    }
    
    private void getLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
        }
        getLeaves(root.left, leaves);
        getLeaves(root.right, leaves);
    }
}
