class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                return true;
            }
            return false;   
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}

class Solution {
    boolean hasSum = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        dfs(root, sum, 0);
        return hasSum;
    }
    
    private void dfs(TreeNode root, int sum, int cur) {
        if (root == null) {
            return;
        }
        cur += root.val;
        if (root.left == null && root.right == null) {
            if (cur == sum) {
                hasSum = true;
            }
        }
        dfs(root.left, sum, cur);
        dfs(root.right, sum, cur);
    }
}
