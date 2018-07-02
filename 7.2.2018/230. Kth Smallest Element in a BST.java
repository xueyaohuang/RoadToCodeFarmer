class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int count = 0;
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            }
            else {
                TreeNode node = stack.pop();
                count++;
                if (count == k) {
                    res = node.val;
                    break;
                }
                p = node.right;
            }
        }
        return res;
    }
}

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
    public int kthSmallest(TreeNode root, int k) {
        int left = countNodes(root.left);
        if (k <= left) {
            return kthSmallest(root.left, k);
        }
        else if (k > left + 1) {
            return kthSmallest(root.right, k - left - 1);
        }
        return root.val;
    }
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}

