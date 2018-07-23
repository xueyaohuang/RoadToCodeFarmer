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
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int min1 = root.val;
        int min2 = Integer.MAX_VALUE;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) { // BFS, 跟level order traversal差不多。如果不需要知道level order traversal每一层具体有哪些值，不需要 for (int i = 0; i < queue.size(); i++).
            TreeNode cur = queue.poll();
            if (cur.val > min1 && cur.val < min2) {
                min2 = cur.val;
            }
            if (cur.left != null) {
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        return min2 == Integer.MAX_VALUE ? -1 : min2;
    }
}

class Solution {

    int min1;
    int min2 = Integer.MAX_VALUE;
    
    public int findSecondMinimumValue(TreeNode root) {
        min1 = root.val;
        dfs(root);
        return min2 == Integer.MAX_VALUE ? -1 : min2;
    }
    
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.val > min1  && root.val < min2) {
            min2 = root.val;
        }
        else if (root.val == min1) {
            dfs(root.left);
            dfs(root.right);
        }
    }
}
