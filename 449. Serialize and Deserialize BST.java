/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
The main difference is as compact as possible.
For BST, a simple pre-order or post-order traversal is enough to construct a BST tree. You might wonder why pre-order/post-order? why not in-order? See here for details https://stackoverflow.com/a/12880809/5684889
*/

public class Codec {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] preorder = data.split(",");
        return deserializeHelper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, new int[]{0});
    }
    
    private TreeNode deserializeHelper(String[] preorder, int min, int max, int[] rootIdx) {
        if (rootIdx[0] >= preorder.length) {
            return null;
        }
        if (Integer.valueOf(preorder[rootIdx[0]]) <= min || Integer.valueOf(preorder[rootIdx[0]]) >= max) {
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.valueOf(preorder[rootIdx[0]]));
        rootIdx[0]++;
        root.left = deserializeHelper(preorder, min, root.val, rootIdx);
        root.right = deserializeHelper(preorder, root.val, max, rootIdx);
        
        return root;
    }
}

// 对preorder来说，恢复BST只需要上限max
public class Codec {
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        sb.append(root.val).append(",");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] preorder = data.split(",");
        return deserializeHelper(preorder, Integer.MAX_VALUE, new int[]{0});
    }
    
    private TreeNode deserializeHelper(String[] preorder, int max, int[] rootIdx) {
        if (rootIdx[0] >= preorder.length) {
            return null;
        }
        if (Integer.valueOf(preorder[rootIdx[0]]) >= max) {
            return null;
        }
        
        TreeNode root = new TreeNode(Integer.valueOf(preorder[rootIdx[0]]));
        rootIdx[0]++;
        root.left = deserializeHelper(preorder, root.val, rootIdx);
        root.right = deserializeHelper(preorder, max, rootIdx);
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
