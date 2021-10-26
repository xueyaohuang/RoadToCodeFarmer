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

/*
node.left is left bound if node is left bound;
node.right could also be left bound if node is left bound && node has no right child;
Same applys for right bound;
if node is left bound, add it before 2 child - pre order;
if node is right bound, add it after 2 child - post order;
A leaf node that is neither left or right bound belongs to the bottom line;
*/
public class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root != null) {
            res.add(root.val);
            getBounds(root.left, res, true, false);
            getBounds(root.right, res, false, true);
        }
        return res;
    }

    private void getBounds(TreeNode node, List<Integer> res, boolean lb, boolean rb) {
        if (node == null) return;
        if (lb) res.add(node.val);
        if (!lb && !rb && node.left == null && node.right == null) res.add(node.val);
        getBounds(node.left, res, lb, rb && node.right == null);
        getBounds(node.right, res, lb && node.left == null, rb);
        if (rb) res.add(node.val);
    }
}
