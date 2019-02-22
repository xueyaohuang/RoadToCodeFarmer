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
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        int[] level = new int[2];
        int[] parents = new int[2];
        getInfo(root, x, y, level, parents, 0);

        if (level[0] == level[1] && parents[0] != parents[1]) {
            return true;
        }
        return false;
    }
    
    private void getInfo(TreeNode root, int x, int y, int[] level, int[] parents, int curDepth) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            if (root.left.val == x) {
                level[0] = curDepth + 1;
                parents[0] = root.val;
            }
            if (root.left.val == y) {
                level[1] = curDepth + 1;
                parents[1] = root.val;
            }
        }
        if (root.right != null) {
            if (root.right.val == x) {
                level[0] = curDepth + 1;
                parents[0] = root.val;
            }
            if (root.right.val == y) {
                level[1] = curDepth + 1;
                parents[1] = root.val;
            }
        }
        if (level[0] != 0 && level[1] != 0) {
            return;
        }
        getInfo(root.left, x, y, level, parents, curDepth + 1);
        getInfo(root.right, x, y, level, parents, curDepth + 1);
    }
}
