/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Time complexity is O(n) because the solution post-order traverses across each tree node once.
// Space complexity is O(h), where h is the height of the tree, or O(lgn).
class Solution {
    int diameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        longestFromHere(root);
        return diameter;
    }
    // 这个longestFromHere算的其实是从当前节点下去最多有多少个node，而不是有多少个edge，所以diameter = Math.max(diameter, left + right)
    private int longestFromHere(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = longestFromHere(node.left);
        int right = longestFromHere(node.right);
        diameter = Math.max(diameter, left + right);
        return 1 + Math.max(left, right);
    }
}

class Solution {
    int len = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        maxFromHere(root);
        return len;
    }
    
    // 这个maxFromHere算的是从当前节点下去最多有多少个edge，而不是有多少个node
    private int maxFromHere(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int left = maxFromHere(root.left);
        int right = maxFromHere(root.right);
        int cur = left + (root.left == null ? 0 : 1) + right + (root.right == null ? 0 : 1);
        len = Math.max(len, cur);
        return 1 + Math.max(left, right);
    }
}

// https://www.geeksforgeeks.org/diameter-n-ary-tree/
// https://www.geeksforgeeks.org/longest-path-undirected-tree/
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] len = new int[1];
        if (root != null) {
            maxAtThisNode(root, len);
        }
        return len[0];
    }
    
    // 这个maxAtThisNode算的是从当前节点下去最多有多少个edge，而不是有多少个node
    // 但是这里的left和right并不是直接等于maxAtThisNode(node.left, len)和maxAtThisNode(node.right, len)，
    // left和right和上一中解法中的left和right含义不同
    private int maxAtThisNode(TreeNode node, int[] len) {
        if (node == null) {
            return 0;
        }
        int left = node.left == null ? 0 : 1 + maxAtThisNode(node.left, len);
        int right = node.right == null ? 0 : 1 + maxAtThisNode(node.right, len);
        len[0] = Math.max(len[0], left + right);
        return Math.max(left, right);
    }
}

// 与第687题对比，687还有附加的val相等的条件，所以要分left，right，rootLeft， rootRight四种情况。

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int passRoot = depth(root.left) + depth(root.right);
        int lDia = diameterOfBinaryTree(root.left);
        int rDia = diameterOfBinaryTree(root.right);
        return Math.max(passRoot, Math.max(lDia, rDia));
    }
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}

