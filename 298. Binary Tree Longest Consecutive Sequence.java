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
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return longestConsecutiveHelper(root, root.val, 0);
    }
    
    // dfs，在每个节点都把curMax与当前最大比较
    private int longestConsecutiveHelper(TreeNode root, int target, int curMax) {
        if (root == null) {
            return curMax;
        }
        if (root.val == target) {
            curMax++;
        } else {
            curMax = 1;
        }
        int left = longestConsecutiveHelper(root.left, root.val + 1, curMax);
        int right = longestConsecutiveHelper(root.right, root.val + 1, curMax);
        return Math.max(curMax, Math.max(left, right));
    }
}

class Solution {
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] res = new int[1];
        longestConsecutiveHelper(root, res);
        return res[0];
    }
    
    private int longestConsecutiveHelper(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        
        int left = longestConsecutiveHelper(root.left, res);
        int right = longestConsecutiveHelper(root.right, res);
        int len = 0;
        
        if (root.left != null && root.left.val == root.val + 1) {
            len = left;
        }
        if (root.right != null && root.right.val == root.val + 1) {
            len = Math.max(len, right);
        }
        
        res[0] = Math.max(res[0], len + 1);
        
        return len + 1;
    }
}
