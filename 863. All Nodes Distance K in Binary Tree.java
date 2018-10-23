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
        List<Integer> result = new LinkedList();
        dfs(root, target, K, result);
        return result;
    }

    // Return distance from node to target if exists, else -1
    private int dfs(TreeNode node, TreeNode target, int K, List<Integer> result) {
        if (node == null)
            return -1;
        
        if (node == target) {
            subtree_add(node, 0, K, result);
            return 1;
        }

        int left = dfs(node.left, target, K, result);
        if (left != -1) {
            if (left == K) {
                result.add(node.val);
            } else {
                subtree_add(node.right, left + 1, K, result);
            }
            return left + 1;
        }

        int right = dfs(node.right, target, K, result);
        if (right != -1) {
            if (right == K) {
                result.add(node.val);
            } else {
                subtree_add(node.left, right + 1, K, result);
            }
            return right + 1;
        }

        return -1;
    }

    // Add all nodes 'K - dist' from the node to answer.
    public void subtree_add(TreeNode node, int dist, int K, List<Integer> result) {
        if (node == null) 
            return;
        if (dist == K)
            result.add(node.val);
        else {
            subtree_add(node.left, dist + 1, K, result);
            subtree_add(node.right, dist + 1, K, result);
        }
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
