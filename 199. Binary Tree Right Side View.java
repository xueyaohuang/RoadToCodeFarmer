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
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            res.add(queue.peek().val);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return res;
    }
}

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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        rightViewHelper(root, res, 0);
        return res;
    }
    private void rightViewHelper(TreeNode root, List<Integer> res, int level) {
        if (root == null) {
            return;
        }
        // size == level 说明到了下一层的第一个，由于先加right，所以就是right side view
        if (res.size() == level) {
            res.add(root.val);
        }
        rightViewHelper(root.right, res, level + 1);
        rightViewHelper(root.left, res, level + 1);    
    }
}
