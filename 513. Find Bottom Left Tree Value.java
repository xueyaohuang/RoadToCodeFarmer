/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// level order travsal，返回res最后一行的第一个数
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(temp);
        }
        return res.get(res.size() - 1).get(0);
    }
}

// 优化空间复杂度，先得到最大深度，level order时，到了最后一层，把第一个val返回
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        int depth = maxDepth(root);
        if (depth == 1) {
            return root.val;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    if (level == depth) {
                        return node.left.val;
                    }
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    if (level == depth) {
                        return node.right.val;
                    }
                }
            }
        }
        return -1;
    }
    
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

class Solution {
    int maxDepth = 0;
    int res = 0;
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return res;
    }
    
    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        if (depth > maxDepth) {
            res = root.val;
            maxDepth = depth;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}
