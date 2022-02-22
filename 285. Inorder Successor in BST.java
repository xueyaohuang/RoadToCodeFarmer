/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 二分，o(lgn)
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        TreeNode left = inorderSuccessor(root.left, p);
        return left == null ? root : left;
    }
}

/*
Inorder Predecessor

public TreeNode predecessor(TreeNode root, TreeNode p) {
  if (root == null)
    return null;

  if (root.val >= p.val) {
    return predecessor(root.left, p);
  } else {
    TreeNode right = predecessor(root.right, p);
    return (right != null) ? right : root;
  }
}
*/

// 没有利用是BST
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        TreeNode prev = null;
        TreeNode node = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                if (prev == p) {
                    return node;
                }
                prev = node;
                node = node.right;
            }
        }
        return null;
    }
}

class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (cur.val > p.val) { // 第一个值比p大的node，就是p的successor
                    return cur;
                }
                cur = cur.right;
            }
        }
        return null;
    }
}

class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> list = new ArrayList<>();
        inorderTraversal(root, list);
        for (int i = 0; i + 1 < list.size(); i++) {
            if (list.get(i) == p) {
                return list.get(i + 1);
            }
        }
        return null;
    }
    private void inorderTraversal(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root);
        inorderTraversal(root.right, list);
    }
}

