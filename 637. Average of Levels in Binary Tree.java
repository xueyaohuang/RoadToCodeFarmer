class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<Double>();
        }
        List<Double> res = new ArrayList<>();
        int height = getHeight(root, 0);
        long[] sum = new long[height];
        int[] count = new int[height];
        averageOfLevelsHelper(root, sum, count, 0, height);
        for (int i = 0; i < height; i++) {
            res.add((double)sum[i] / count[i]);
        }
        return res;
    }
    
    private void averageOfLevelsHelper(TreeNode root, long[] sum, int[] count, int level, int height) {
        if (root == null) {
            return;
        }
        sum[level] += root.val;
        count[level]++;
        averageOfLevelsHelper(root.left, sum, count, level + 1, height);
        averageOfLevelsHelper(root.right, sum, count, level + 1, height);
    }
    
    private int getHeight(TreeNode root, int curHeight) {
        if (root == null) {
            return curHeight;
        }
        return 1 + Math.max(getHeight(root.left, curHeight), getHeight(root.right, curHeight));
    }
}

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<Double>();
        }
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            int count = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                sum += node.val;
                count++;
            }
            res.add((double)sum / count);
        }
        return res;
    }
}
