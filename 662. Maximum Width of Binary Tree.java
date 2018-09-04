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
