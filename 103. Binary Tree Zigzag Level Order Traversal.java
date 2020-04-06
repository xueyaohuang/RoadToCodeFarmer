/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// BFS
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 这里用linkedlist而不是arraylist，因为linkedlist 在头部add时的time complexity是O(1)
            List<Integer> temp = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (level % 2 == 0) {
                    temp.add(node.val);
                }
                else {
                    temp.add(0, node.val);
                }
            }
            level++;
            res.add(temp);
        }
        return res;
    }
}

// DFS
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }
    
    private void dfs(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(new LinkedList<>());
        }
        if (level % 2 == 0) {
            res.get(level).add(root.val);
        } else {
            res.get(level).add(0, root.val);
        }
        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
    }
}
