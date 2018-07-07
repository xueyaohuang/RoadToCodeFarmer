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
    
    private int sum = 0; // sum has to be a global variable.
    
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        convertBST(root.right);
        int temp = root.val;
        root.val += sum;
        sum += temp;
        convertBST(root.left);
        return root;
    }
}

class Solution {
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            }
            else {
                TreeNode node = stack.pop();
                int temp = node.val;
                node.val += sum;
                sum += temp;
                p = node.left;
            }
        }
        return root;
    }
}

