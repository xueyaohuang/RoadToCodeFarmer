class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int width = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> indexes = new LinkedList<>(); // 每个node在当前level是第几个
        queue.offer(root);
        indexes.offer(0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = 0;
            int end = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                int idx = indexes.poll();
                if (i == 0) {
                    start = idx;
                } 
                if (i == size - 1) {
                    end = idx;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    indexes.offer(idx * 2);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    indexes.offer(idx * 2 + 1);
                }
            }
            width = Math.max(width, end - start + 1);
        }
        return width;
    }
}

class Solution {
    List<Integer> leftMost = new ArrayList<>();
    int width = 0;
    
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root, 0, 0);
        return width;
    }
    
    private void dfs(TreeNode root, int depth, int idx) {
        if (root == null) {
            return;
        }
        if (depth == leftMost.size()) {
            leftMost.add(idx);
        }
        width = Math.max(width, idx - leftMost.get(depth) + 1);
        dfs(root.left, depth + 1, idx * 2);
        dfs(root.right, depth + 1, idx * 2 + 1);
    }
}

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<Integer> leftMost = new ArrayList<>();
        int width = 0;
        width = dfs(root, 0, 0, width, leftMost);
        return width;
    }
    
    private int dfs(TreeNode root, int depth, int idx, int width, List<Integer> leftMost) {
        if (root == null) {
            return 0;
        }
        if (depth == leftMost.size()) {
            leftMost.add(idx);
        }
        int left = dfs(root.left, depth + 1, idx * 2, width, leftMost);
        int right = dfs(root.right, depth + 1, idx * 2 + 1, width, leftMost);
        // idx - leftMost.get(depth) + 1 是当前level的宽度
        width = Math.max(Math.max(left, right), idx - leftMost.get(depth) + 1);
        return width;
    }
}
