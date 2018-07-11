class Solution {
    public int rob(TreeNode root) {
        return robHelper(root, new HashMap<TreeNode, Integer>());
    }
    private int robHelper(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        if (map.containsKey(root)) {
            return map.get(root);
        }
        int val = 0;
        if (root.left != null) {
            val += robHelper(root.left.left, map) + robHelper(root.left.right, map);
        }
        if (root.right != null) {
            val += robHelper(root.right.left, map) + robHelper(root.right.right, map);
        }
        val = Math.max(val + root.val, robHelper(root.left, map) + robHelper(root.right, map));
        map.put(root, val);
        return val;
    }
}
