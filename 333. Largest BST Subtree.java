/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
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
class Solution {
    public int largestBSTSubtree(TreeNode root) {
        int[] size = {0};
        largestBSTSubtreeHelper(root, size);
        return size[0];
    }
    
    // int[]: nums, min, max
    private int[] largestBSTSubtreeHelper(TreeNode root, int[] size) {
        if (root == null) {
            return new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE};
        }
        int[] res = new int[]{0, Integer.MIN_VALUE, Integer.MAX_VALUE};
        int[] left = largestBSTSubtreeHelper(root.left, size);
        int[] right = largestBSTSubtreeHelper(root.right, size);
        
        // valid BST
        if (root.val > left[2] && root.val < right[1]) {
            res[0] = 1 + left[0] + right[0];
            res[1] = Math.min(root.val, left[1]);  // 包含root的BST的下界更新成最小的下界
            res[2] = Math.max(root.val, right[2]); // 包含root的BST的上界更新成最大的上界
            size[0] = Math.max(size[0], res[0]);
        } else {
            return new int[]{Math.max(left[0], right[0]), Integer.MIN_VALUE, Integer.MAX_VALUE};
        }
        return res;
    }
}
