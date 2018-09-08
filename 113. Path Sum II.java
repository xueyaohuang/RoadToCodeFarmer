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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, sum, res, new ArrayList<>());
        return res;
    }
    
    private void dfs(TreeNode root, int sum, List<List<Integer>> res, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                res.add(new ArrayList<>(path)); // 注意 res.add时，每次都要new一个instance
            }
            path.remove(path.size() - 1); // 注意当前元素用完后要remove，否则会一直累计
        } else {
            dfs(root.left, sum - root.val, res, path);
            // recursion left 完后不能 path.remove(path.size() - 1);
            // remove的作用是返回上一层recursion前，要去掉当前recursion加入的node。
            // 这里left完了要继续往下recursion到right子树，并没有返回上一层recursion，多益不能remove
            dfs(root.right, sum - root.val, res, path);
            path.remove(path.size() - 1); // remove的作用是返回上一层recursion前，要去掉当前recursion加入的node。
        }
    }
}
