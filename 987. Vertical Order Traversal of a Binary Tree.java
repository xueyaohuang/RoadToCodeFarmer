/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
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
