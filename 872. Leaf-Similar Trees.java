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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        getLeafValue(root1, l1);
        getLeafValue(root2, l2);
        if (l1.size() != l2.size()) {
            return false;
        }
        for (int i = 0; i < l1.size(); i++) {
            if (l1.get(i) != l2.get(i)) {
                return false;
            }
        }
        return true;
    }
    
    private void getLeafValue(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            res.add(root.val);
        }
        getLeafValue(root.left, res);
        getLeafValue(root.right, res);
    }
}
