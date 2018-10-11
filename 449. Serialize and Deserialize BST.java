/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(root.val).append('.');
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
                sb.append(node.left.val).append('.');
            } else {
                sb.append('#').append('.');
            }
            
            if (node.right != null) {
                queue.offer(node.right);
                sb.append(node.right.val).append('.');
            } else {
                sb.append('#').append('.');
            }
        }
        
        sb.deleteCharAt(sb.length() - 1);
        while (sb.charAt(sb.length() - 1) == '#') {
            int size = sb.length();
            sb.delete(size - 2, size);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        
        // You need to escape the dot if you want to split on a literal dot
        String[] strs = data.split("\\.");
        int len = strs.length;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        queue.offer(root);
        int idx = 1;
        
        while (!queue.isEmpty() && idx < len) {
            TreeNode node = queue.poll();
            if (!strs[idx].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(strs[idx]));
                queue.offer(node.left);
            }
            idx++;
            if (idx >= len) {
                break;
            }
            
            if (!strs[idx].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(strs[idx]));
                queue.offer(node.right);
            }
            idx++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
