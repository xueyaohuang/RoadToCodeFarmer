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
    public int closestValue(TreeNode root, double target) {
        if (root.val == target) {
            return root.val;
        }
        double diff = Math.abs(root.val - target);
        if (target < root.val && root.left != null) {
            int left = closestValue(root.left, target);
            return diff > Math.abs(target - left) ? left : root.val;
        }
        if (target > root.val && root.right != null) {
            int right = closestValue(root.right, target);
            return diff > Math.abs(target - right) ? right : root.val;
        }
        return root.val;
    }
}

class Solution {
    int res;
    public int closestValue(TreeNode root, double target) {
        res = root.val;
        dfs(root, target);
        return res;
    }
    
    private void dfs(TreeNode root, double target) {
        if (root == null) {
            return;
        }
        if (Math.abs(root.val - target) < Math.abs(res - target)) {
            res = root.val;
        }
        if (root.val <= target) {
            dfs(root.right, target);
        } else {
            dfs(root.left, target);
        }
    }
}

class Solution {
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        TreeNode node = root;
        while (node != null) {
            if (Math.abs(node.val - target) < Math.abs(res - target)) {
                res = node.val;
            }
            if (node.val == target) {
                return node.val;
            } else if (node.val < target) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return res;
    }
}

public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        int[] res = new int[]{0};
        dfs(root, target, res);
        return res[0];
    }
    
    private void dfs(TreeNode cur, double target, int[] res) {
        if (cur == null) {
            return;
        }
        if (Math.abs(cur.val - target) < Math.abs(res[0] - target)) {
            res[0] = cur.val;
        }
        dfs(cur.left, target, res);
        dfs(cur.right, target, res);
    }
}
