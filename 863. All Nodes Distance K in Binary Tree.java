/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        if (K == 0) {
            res.add(target.val);
        } else {
            int dist = distanceToTarget(root, target, K, res);
            if (dist == K) {
                res.add(root.val);
            }
        }
        
        return res;
    }
    
    // 凡是向下到不了target的node，都会return -1. 比如题目例子中的1，0，8
    // 到了target后，就不会往下继续走distanceToTarget这个函数了，而是改为searchInSubtree
    private int distanceToTarget(TreeNode node, TreeNode target, int K, List<Integer> res) {
        if (node == null) {
            return -1;
        }
        if (node == target) {
            searchInSubtree(node, K, res);
            return 0;
        }
        
        int left = distanceToTarget(node.left, target, K, res);
        int right = distanceToTarget(node.right, target, K, res);
        
        if (left != -1) {
            if (left == K) {
                res.add(node.left.val);
            }
            searchInSubtree(node.right, K - left - 2, res);
            return left + 1;
        }
        
        if (right != -1) {
            if (right == K) {
                res.add(node.right.val);
            }
            searchInSubtree(node.left, K - right - 2, res);
            return right + 1;
        }
        
        return -1;
    }
    
    // 在当前root的子树中，找与root相距distance的node。所以只会往下（子树）找
    private void searchInSubtree(TreeNode root, int distance, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (distance < 0) {
            return;
        }
        if (distance == 0) {
            res.add(root.val);
        }
        searchInSubtree(root.left, distance - 1, res);
        searchInSubtree(root.right, distance - 1, res);
    }
}


// use LCA
class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Map<TreeNode, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        getDistance(root, map, 0);
        int targetToRoot = map.get(target);
        
        for (TreeNode node : map.keySet()) {
            int d1 = map.get(node);
            TreeNode lca = lowestCommonAncestor(root, target, node);
            int d2 = map.get(lca);
            if (d1 + targetToRoot == K + 2 * d2) {
                res.add(node.val);
            }
        }
        return res;
    }
    
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
    
    private void getDistance(TreeNode root, Map<TreeNode, Integer> map, int distance) {
        if (root == null) {
            return;
        }
        map.put(root, distance);
        getDistance(root.left, map, distance + 1);
        getDistance(root.right, map, distance + 1);
    }
}
