/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

####### Inorder Traversal ########

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorderTraversalHelper(root, list);
        return list;
    }
    private void inorderTraversalHelper(TreeNode root, List<Integer> list) {
        if (root != null) {
            if (root.left != null) {
                inorderTraversalHelper(root.left, list);
            }
            list.add(root.val);
            if (root.right != null) {
                inorderTraversalHelper(root.right, list);
            } 
        }
    }
}

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // Deque<TreeNode> stack = new ArrayDeque<>(); Both stack and deque are applicable.
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } 
            else {
                TreeNode node = stack.pop();
                result.add(node.val);  // Add after all left children
                p = node.right;   
            }
        }
        return result;
    }
}


####### Preorder Traversal ########

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorderTraversalHelper(root, list);
        return list;
    }
    private void preorderTraversalHelper(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            if (root.left != null) {
                preorderTraversalHelper(root.left, list);
            }
            if (root.right != null) {
                preorderTraversalHelper(root.right, list);
            }
        }
    }
}

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // Deque<TreeNode> stack = new ArrayDeque<>(); Both stack and deque are applicable.
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                result.add(p.val);  // Add before going to children
                p = p.left;
            }
            else {
                TreeNode node = stack.pop();
                p = node.right;   
            }
        }
        return result;
    }
}


####### Postorder Traversal ########

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorderTraversalHelper(root, list);
        return list;
    }
    private void postorderTraversalHelper(TreeNode root, List<Integer> list) {
        if (root != null) {
            if (root.left != null) {
                postorderTraversalHelper(root.left, list);
            }
            if (root.right != null) {
                postorderTraversalHelper(root.right, list);
            }
            list.add(root.val);
        }
    }
}

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                list.addFirst(p.val);
                p = p.right;
            }
            else {
                TreeNode node = stack.pop();
                p = node.left;
            }
        }
        return list;
    }
}











