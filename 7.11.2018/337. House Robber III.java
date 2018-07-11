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

class Solution {
    public int rob(TreeNode root) {
        int[] res = robHelper(root);
        return Math.max(res[0], res[1]);
    }
    private int[] robHelper(TreeNode root) {
        if (root == null) {
            return new int[2];
        }
        int[] res = new int[2];
        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] +right[0];
        return res;
    }
}
