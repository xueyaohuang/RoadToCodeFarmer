/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//找最左边和最右边 root的index是0 左边为负数，右边为正数。
//dfs确定max 和min， 然后bfs加入list中。
//time : O(n) space : O(n)
class Solution {
    
    private int leftMost;
    private int rightMost;
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        getRange(root, 0);
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> idxQ = new LinkedList<>();
        
        for (int i = leftMost; i <= rightMost; i++) {
            res.add(new ArrayList<Integer>());
        }
        
        nodeQ.offer(root);
        //-leftMost 意味这把root的坐标右移leftMost位，这样root左边的负值坐标就可以变为正数，主要是为了res.get(idx)，不能get负数index。
        idxQ.offer(-leftMost);
        
        // BFS
        while (!nodeQ.isEmpty()) {
            TreeNode node = nodeQ.poll();
            int idx = idxQ.poll();
            res.get(idx).add(node.val);
            if (node.left != null) {
                nodeQ.offer(node.left);
                idxQ.offer(idx - 1);
            }
            if (node.right != null) {
                nodeQ.offer(node.right);
                idxQ.offer(idx + 1);
            }
        }
        return res;
    }
    
    // DFS
    private void getRange(TreeNode root, int index) {
        if (root == null) {
            return;
        }
        leftMost = Math.min(leftMost, index);
        rightMost = Math.max(rightMost, index);
        getRange(root.left, index - 1);
        getRange(root.right, index + 1);
    }
}
