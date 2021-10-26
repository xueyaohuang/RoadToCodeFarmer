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
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        List<Integer> res = new ArrayList<>(), left = new ArrayList<>(), right = new ArrayList<>(), leaves = new ArrayList<>();
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return res;
        }
        
        left.add(root.val);
        if (root.left != null) {
            getLeftBoundary(root.left, left);
            left.remove(left.size() - 1);
        }
        
        if (root.right != null) {
            getRightBoundary(root.right, right);
            right.remove(right.size() - 1);
        }
        
        getLeaves(root, leaves);
        Collections.reverse(right);
        res.addAll(left);
        res.addAll(leaves);
        res.addAll(right);
        return res;
    }
    
    private void getLeftBoundary(TreeNode root, List<Integer> left) {
        if (root == null) {
            return;
        }
        left.add(root.val);
        if (root.left != null) {
            getLeftBoundary(root.left, left);
        } else if (root.right != null) {
            getLeftBoundary(root.right, left);
        }
    }
    
    private void getRightBoundary(TreeNode root, List<Integer> right) {
        if (root == null) {
            return;
        }
        right.add(root.val);
        if (root.right != null) {
            getRightBoundary(root.right, right);
        } else if (root.left != null) {
            getRightBoundary(root.left, right);
        }
    }
    
    private void getLeaves(TreeNode root, List<Integer> leaves) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leaves.add(root.val);
            return;
        }
        getLeaves(root.left, leaves);
        getLeaves(root.right, leaves);
    }
}

class Solution {
    List<Integer> res;
    List<Integer> rightBoundary;
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        if (root.left == null &&  root.right == null) {
            return res;
        }
        getLeftBoundary(root);
        getLeavesBoundary(root);
        rightBoundary = new ArrayList<>();
        getRightBoundary(root);
        for (int i = rightBoundary.size() - 1; i >= 0; i--) {
            res.add(rightBoundary.get(i));
        }
        return res;
    }
    
    private void getLeftBoundary(TreeNode root) {
        TreeNode node = root.left;
        if (node == null) {
            return;
        }
        while (node != null) {
            if (node.left == null && node.right == null) {
                return;
            }
            res.add(node.val);
            if (node.left != null) {
                node = node.left;
            } else if (node.right != null) {
                node = node.right;
            } else {
                return;
            }
        }
    }
    
    private void getLeavesBoundary(TreeNode root) {
        TreeNode node = root;
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            res.add(node.val);
            return;
        }
        getLeavesBoundary(node.left);
        getLeavesBoundary(node.right);
    }
    
    private void getRightBoundary(TreeNode root) {
        TreeNode node = root.right;
        if (node == null) {
            return;
        }
        while (node != null) {
            if (node.left == null && node.right == null) {
                return;
            }
            rightBoundary.add(node.val);
            if (node.right != null) {
                node = node.right;
            } else if (node.left != null) {
                node = node.left;
            } else {
                return;
            }
        }
    }
}

// one pass
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> left = new LinkedList<>(), right = new LinkedList<>();
        preorder(root, left, right, 0);
        left.addAll(right);
        return left;
    }

    public void preorder(TreeNode cur, List<Integer> left, List<Integer> right, int flag) {
        if (cur == null) return;
        if (flag == 2) right.add(0, cur.val);
        else if (flag <= 1 || cur.left == null && cur.right == null) left.add(cur.val);
        preorder(cur.left, left, right, flag <= 1 ? 1 : (flag == 2 && cur.right == null) ? 2 : 3);
        preorder(cur.right, left, right, flag % 2 == 0 ? 2 : (flag == 1 && cur.left == null) ? 1 : 3);
    }
}
