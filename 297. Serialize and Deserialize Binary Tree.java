/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// based on level order traversal
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(','); //必须要","，否则没法分清有几位数字，比如123，可能是1，23，也可能是1，2，3，还可能是12, 3
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                sb.append(node.left.val).append(','); //入队的同时append
            } else {
                sb.append('#').append(',');
            }
            if (node.right != null) {
                queue.add(node.right);
                sb.append(node.right.val).append(',');
            } else {
                sb.append('#').append(',');
            }
        }
        sb.deleteCharAt(sb.length() - 1); //删除最后一个逗号
        while (sb.charAt(sb.length() - 1) == '#') { // 删除右半子树多余的井号和逗号
            int size = sb.length();
            sb.delete(size - 2, size); // 一次删逗号和井号, "，#"，所以长度是2
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] tree = data.split(",");
        int len = tree.length;
        TreeNode root = new TreeNode(Integer.parseInt(tree[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int idx = 1;
        while (!queue.isEmpty() && idx < len) {
            TreeNode node = queue.poll();
            if (!tree[idx].equals("#")) {
                node.left = new TreeNode(Integer.parseInt(tree[idx]));
                queue.add(node.left);
            }
            idx++;
            if (idx >= len) { // 重要
                break;
            }
            if (!tree[idx].equals("#")) {
                node.right = new TreeNode(Integer.parseInt(tree[idx]));
                queue.add(node.right);
            }
            idx++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

// preorder traversal.
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append('#').append(',');
            return;
        }
        sb.append(root.val).append(',');
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(",")));
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        String str = queue.poll();
        if (str.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(str));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
    }
}
