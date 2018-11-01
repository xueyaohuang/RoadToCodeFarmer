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
    private Map<Integer, Integer> map = new HashMap<>();
    private int max = 0;
    
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        
        List<Integer> list = new ArrayList<>();
        
        traverse(root);
        for (int i : map.keySet()) {
            if (map.get(i) == max) {
                list.add(i);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;      
    }
    
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        max = Math.max(max, map.get(root.val));
        traverse(root.left);
        traverse(root.right);
    }
    
}
