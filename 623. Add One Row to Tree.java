
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 1. 先level order, 把第d-1层的node加入到queue里面
// 2. 对d-1层的node做修改
class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty() && depth < d - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        for (TreeNode node : queue) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = new TreeNode(v);
            node.right = new TreeNode(v);
            node.left.left = left;
            node.right.right = right;
        }
        return root;
    }
}

class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        if (root == null) {
            return null;
        }
        if (d == 2) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = new TreeNode(v);
            root.right = new TreeNode(v);
            root.left.left = left;
            root.right.right = right;
        }
        addOneRow(root.left, v, d - 1);
        addOneRow(root.right, v, d - 1);
        return root;
    }
}

