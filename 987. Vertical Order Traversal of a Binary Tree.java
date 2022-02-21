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
    int leftMost = 0;
    int rightMost = 0;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<int[]>> map = new HashMap<>();
        dfs(root, map, 0, 0);
        for (int i = leftMost; i <= rightMost; i++) {
            Collections.sort(map.get(i), (a, b) -> a[1] == b[1] ? a[0] - b[0] : a[1] - b[1]);
            List<Integer> temp = new ArrayList<>();
            for (int[] arr : map.get(i)) {
                temp.add(arr[0]);
            }
            res.add(temp);
        }
        return res;
    }
    
    private void dfs(TreeNode root, Map<Integer, List<int[]>> map, int level, int col) {
        if (root == null) {
            return;
        }
        map.putIfAbsent(col, new ArrayList<>());
        leftMost = Math.min(leftMost, col);
        rightMost = Math.max(rightMost, col);
        map.get(col).add(new int[]{root.val, level});
        dfs(root.left, map, level + 1, col - 1);
        dfs(root.right, map, level + 1, col + 1);
    }
}


// sol 2
class Tuple {
    int y;
    int val;
    public Tuple(int y, int val) {
        this.y = y;
        this.val = val;
    }
}

class Solution {
    int leftMost;
    int rightMost;
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<List<Tuple>> tuple = new ArrayList<>();
        getRange(root, 0);
        for (int i = leftMost; i <= rightMost; i++) {
            tuple.add(new ArrayList<Tuple>());
        }
        dfs(root, tuple, 0, 0);
        for (List<Tuple> t : tuple) {
            Collections.sort(t, (t1, t2) -> t1.y == t2.y ? t1.val - t2.val : t2.y - t1.y);
            List<Integer> temp = new ArrayList<>();
            for (Tuple tp : t) {
                temp.add(tp.val);
            }
            res.add(temp);
        }
        return res;
    }
    
    private void dfs(TreeNode root, List<List<Tuple>> tuple, int x, int y) {
        if (root == null) {
            return;
        }
        tuple.get(x - leftMost).add(new Tuple(y, root.val));
        dfs(root.left, tuple, x - 1, y - 1);
        dfs(root.right, tuple, x + 1, y - 1);
    }
    
    private void getRange(TreeNode root, int x) {
        if (root == null) {
            return;
        }
        leftMost = Math.min(leftMost, x);
        rightMost = Math.max(rightMost, x);
        getRange(root.left, x - 1);
        getRange(root.right, x + 1);
    }
}
