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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return treeHelper(postorder.length - 1, 0, inorder.length - 1, inorder, postorder);
    }
    private TreeNode treeHelper(int posEnd, int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (posEnd < 0 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[posEnd]);
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
            }
        }
        root.left = treeHelper(posEnd - (inEnd - rootIndex) - 1, inStart, rootIndex - 1, inorder, postorder);
        root.right = treeHelper(posEnd - 1, rootIndex + 1, inEnd, inorder, postorder);
        return root;
    }
}

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int length = postorder.length;
        if(length < 1) return null;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(postorder[length - 1]);
        TreeNode cur = root;
        
        for(int i = length - 2, j = length - 1; i >= 0; i--) {
            if(cur.val != inorder[j]) {
                cur.right = new TreeNode(postorder[i]);
                stack.push(cur);
                cur = cur.right;
            }
            else {
                j--;
                while(!stack.isEmpty() && stack.peek().val == inorder[j]) {
                    cur = stack.pop();
                    j--;
                }
                cur = cur.left = new TreeNode(postorder[i]);
            }
        }
        return root;
    }
}

class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0 || postorder.length != inorder.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0;i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode p = root;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int idx = map.get(postorder[i]);
            TreeNode node = new TreeNode(postorder[i]);
            if (idx > map.get(stack.peek().val)) {
                p.right = node;
                p = p.right;
            } else {
                while (!stack.isEmpty() && idx < map.get(stack.peek().val)) {
                    p = stack.pop();
                }
                p.left = node;
                p = p.left;
            }
            stack.push(node);
        }
        return root;
    }
}
