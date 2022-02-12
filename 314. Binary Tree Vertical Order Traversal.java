/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//找最左边和最右边 root的index是0 左边为负数，右边为正数。
//dfs确定max 和min， 然后bfs加入list中。
//time : O(n) space : O(n)
class Solution {
    
    private int leftMost;
    private int rightMost;
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        getRange(root, 0);
        Queue<TreeNode> nodeQ = new LinkedList<>();
        Queue<Integer> idxQ = new LinkedList<>();
        
        for (int i = leftMost; i <= rightMost; i++) {
            res.add(new ArrayList<Integer>());
        }
        
        nodeQ.offer(root);
        //-leftMost 意味这把root的坐标右移leftMost位，这样root左边的负值坐标就可以变为正数，主要是为了res.get(idx)，不能get负数index。
        idxQ.offer(-leftMost);
        
        // BFS
        while (!nodeQ.isEmpty()) {
            TreeNode node = nodeQ.poll();
            int idx = idxQ.poll();
            res.get(idx).add(node.val);
            if (node.left != null) {
                nodeQ.offer(node.left);
                idxQ.offer(idx - 1);
            }
            if (node.right != null) {
                nodeQ.offer(node.right);
                idxQ.offer(idx + 1);
            }
        }
        return res;
    }
    
    // DFS
    private void getRange(TreeNode root, int index) {
        if (root == null) {
            return;
        }
        leftMost = Math.min(leftMost, index);
        rightMost = Math.max(rightMost, index);
        getRange(root.left, index - 1);
        getRange(root.right, index + 1);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int leftMost;
    private int rightMost;
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<Integer, List<int[]>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(root, map, 0, 0);
        for (int i = leftMost; i <= rightMost; i++) {
            Collections.sort(map.get(i), (a, b) -> a[1] - b[1]);
            List<Integer> temp = new ArrayList<>();
            for (int[] arr : map.get(i)) {
                temp.add(arr[0]);
            }
            res.add(temp);
        }
        return res;
    }
    
    private void dfs(TreeNode root, Map<Integer, List<int[]>> map, int curIdx, int level) {
        if (root == null) {
            return;
        }
        leftMost = Math.min(leftMost, curIdx);
        rightMost = Math.max(rightMost, curIdx);
        map.putIfAbsent(curIdx, new ArrayList<>());
        map.get(curIdx).add(new int[]{root.val, level});
        dfs(root.left, map, curIdx - 1, level + 1);
        dfs(root.right, map, curIdx + 1, level + 1);
    }
}
