/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // The solution of this problem is pretty good: https://leetcode.com/problems/largest-bst-subtree/solution/
 // O(n^2)
 // top down 会很慢，因为在每个点都要check 是否是bst
class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            return sizeOfTree(root);
        }
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }
    
    // O(n)
    private boolean isBST(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }
    
    private int sizeOfTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + sizeOfTree(root.left) + sizeOfTree(root.right);
    }
}

// O(n)
// bottom up, 结合了求size和判断BST
// bottom up的好处是，先知道了下面subtree是否是bst，判断上面subtree是否是bst的时候可以直接利用下面ubtree的信息，避免了重复计算
class Solution {
    int size = 0;
    public int largestBSTSubtree(TreeNode root) {
        largestBSTSubtreeHelper(root);
        return size;
    }
    // int[]: nums of nodes, min val in the subtree, max val in the subtree
    private int[] largestBSTSubtreeHelper(TreeNode root) {
        if (root == null) {
            // set min to Integer.MAX_VALUE and max to Integer.MIN_VALUE so that a null node will never fail its parent to be a bst
            return new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        // default to current root is not bst, set num of nodes to 0, min to Integer.MIN_VALUE, and max to Integer.MAX_VALUE
        int[] res = new int[]{0, Integer.MIN_VALUE, Integer.MAX_VALUE};
        int[] left = largestBSTSubtreeHelper(root.left);
        int[] right = largestBSTSubtreeHelper(root.right);
        // valid BST
        if (root.val > left[2] && root.val < right[1]) {
            res[0] = 1 + left[0] + right[0];
            res[1] = Math.min(root.val, left[1]);
            res[2] = Math.max(root.val, right[2]);
            size = Math.max(size, res[0]);
        }
        return res;
    }
}
