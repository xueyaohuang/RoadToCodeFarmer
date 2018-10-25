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
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int[] freq = new int[1];
        subTreeSum(root, map, freq);
        List<Integer> list = new ArrayList<>();
        for (int i : map.keySet()) {
            if (map.get(i) == freq[0]) {
                list.add(i);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
    
    private int subTreeSum(TreeNode root, Map<Integer, Integer> map, int[] freq) {
        if (root == null) {
            return 0;
        }
        int left = subTreeSum(root.left, map, freq);
        int right = subTreeSum(root.right, map, freq);
        int curSum = root.val + left + right;
        map.putIfAbsent(curSum, 0);
        map.put(curSum, map.get(curSum) + 1);
        freq[0] = Math.max(freq[0], map.get(curSum));
        return curSum;
    }
}
