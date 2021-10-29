/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
 
 // two passes
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        inorder(root1, l1);
        inorder(root2, l2);
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            if (l1.get(i) == l2.get(j)) {
                res.add(l1.get(i));
                res.add(l2.get(j));
                i++;
                j++;
            } else if (l1.get(i) > l2.get(j)) {
                res.add(l2.get(j));
                j++;
            } else {
                res.add(l1.get(i));
                i++;
            }
        }
        while (i < l1.size()) {
            res.add(l1.get(i++));
        }
        while (j < l2.size()) {
            res.add(l2.get(j++));
        }
        return res;
    }
    
    private void inorder(TreeNode root, List<Integer> l) {
        if (root == null) {
            return;
        }
        inorder(root.left, l);
        l.add(root.val);
        inorder(root.right, l);
    }
}


// one pass
class Solution {
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        TreeNode n1 = root1;
        TreeNode n2 = root2;
        n1 = pushLeft(n1, stack1);
        n2 = pushLeft(n2, stack2);
        while (n1 != null || !stack1.isEmpty() || n2 != null || !stack2.isEmpty()) {
            if (n1 == null && !stack1.isEmpty()) {
                n1 = stack1.pop();
            }
            if (n2 == null && !stack2.isEmpty()) {
                n2 = stack2.pop();
            }
            if (n1 == null) {
                res.add(n2.val);
                n2 = pushLeft(n2.right, stack2);
                continue;
            } 
            if (n2 == null) {
                res.add(n1.val);
                n1 = pushLeft(n1.right, stack1);
                continue;
            }
            
            if (n1.val == n2.val) {
                res.add(n1.val);
                res.add(n2.val);
                n1 = pushLeft(n1.right, stack1);
                n2 = pushLeft(n2.right, stack2);
            } else if (n1.val < n2.val) {
                res.add(n1.val);
                n1 = pushLeft(n1.right, stack1);
            } else {
                res.add(n2.val);
                n2 = pushLeft(n2.right, stack2);
            }
        }
        return res;
    }
    
    // We need return value here in order to move the node forward
    private TreeNode pushLeft(TreeNode n, Deque<TreeNode> stack) {
        while (n != null) {
            stack.push(n);
            n = n.left;
        }
        return n;
    }
}
