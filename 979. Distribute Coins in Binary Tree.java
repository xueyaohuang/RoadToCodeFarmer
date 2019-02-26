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
    int moves = 0;
    
    public int distributeCoins(TreeNode root) {
        moveFromCurrentNode(root);
        return moves;
    }
    
    private int moveFromCurrentNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = moveFromCurrentNode(root.left);
        int right = moveFromCurrentNode(root.right);
        moves += Math.abs(left) + Math.abs(right);
        return root.val + left + right - 1;
    }
}

class Solution {
    public int distributeCoins(TreeNode root) {
        int moves = 0;
        if (root.left != null) {
            moves += distributeCoins(root.left);
            root.val += root.left.val - 1;
            moves += Math.abs(root.left.val - 1);
        }
        if (root.right != null) {
            moves += distributeCoins(root.right);
            root.val += root.right.val - 1;
            moves += Math.abs(root.right.val - 1);
        }
        return moves;
    }
}
