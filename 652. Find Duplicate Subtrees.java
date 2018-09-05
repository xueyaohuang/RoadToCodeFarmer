// tree2str把每个子树的所有值打印成String，然后放入map，遇到同样形式的String说明有结构一样的子树。
class Solution {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<TreeNode> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        tree2str(root, res, map);
        return res;
    }
    
    private String tree2str(TreeNode root, List<TreeNode> res, Map<String, Integer> map) {
        if (root == null) {
            return "*"; // 用 * 代替 null节点
        }
        String left = tree2str(root.left, res, map);
        String right = tree2str(root.right, res, map);
        String rootStr = root.val + "." + left + right; // 用"."做分隔符，避免出现很多数字在一起分不清。比如 root=12, left = 1, right = 3，没有分隔符就是1213。也可能root = 1, left = 21， right = 3。
        map.put(rootStr, map.getOrDefault(rootStr, 0) + 1);
        if (map.get(rootStr) == 2) { // 为了避免res中有重复，只有第一次遇到出现过的才往res里面放
            res.add(root);
        }
        return rootStr;
    }
}
