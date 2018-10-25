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
    public List<List<String>> printTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // 这些参数都是找规律算的
        int depth = getDepth(root);
        int width = (int)Math.pow(2, depth) - 1;
        int rootIdx = (int)Math.pow(2, depth - 1) - 1;
        int quarterWidth = (width + 1) / 4;
        
        List<List<String>> res = new ArrayList<>();
        dfs(root, res, 0, rootIdx, width, quarterWidth);
        return res;
    }
    
    private void dfs(TreeNode root, List<List<String>> res, int depth, int idx, int width, int quarterWidth) {
        if (root == null) {
            return;
        }
        if (res.size() == depth) {
            String[] tempStr = new String[width];
            Arrays.fill(tempStr, "");
            List<String> temp = Arrays.asList(tempStr);
            res.add(temp);
        }
        res.get(depth).set(idx, String.valueOf(root.val));
        
        dfs(root.left, res, depth + 1, idx - quarterWidth, width, quarterWidth / 2);
        dfs(root.right, res, depth + 1, idx + quarterWidth, width, quarterWidth / 2);
    }
    
    
    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getDepth(root.left), getDepth(root.right));
    }
}
