/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// level order dfs的变种
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        height(root, res);
        return res;
    }
    
    // 得到每个node距离地面有多高
    private int height(TreeNode root, List<List<Integer>> res) {
        if (root == null) {
            return -1;
        }
        int height = 1 + Math.max(height(root.left, res), height(root.right, res));
        if (height == res.size()) {
            res.add(new ArrayList<Integer>());
        }
        res.get(height).add(root.val);
        root = null;
        return height;
    }
}

// 每次移除一层leaves，直到把root也移除。
class Solution {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
             return res;
        }
        while (root != null) {
            List<Integer> level = new ArrayList<>();
            root = remove(root, level);
            res.add(level);
        }
        return res;
    }
    
    private TreeNode remove(TreeNode root, List<Integer> level) {
        if (root == null) {
            return root;
        }
        if (root.left == null && root.right == null) {
            level.add(root.val);
            return null;
        }
        root.left = remove(root.left, level);
        root.right = remove(root.right, level);
        return root;
    }
}
