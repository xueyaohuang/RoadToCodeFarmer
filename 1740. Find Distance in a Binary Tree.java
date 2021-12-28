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
 // find LCA, and calculate the distance, three passes.
class Solution {
    public int findDistance(TreeNode root, int p, int q) {
        TreeNode lca = lowestCommonAncestor(root, p, q);
        int distP = distance(lca, p, 0);
        int distQ = distance(lca, q, 0);
        return distP + distQ;
    }
    
    private TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        if (root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
    
    private int distance(TreeNode root, int p, int cur) {
        if (root == null) {
            return -1;
        }
        if (root.val == p) {
            return cur;
        }
        int left = distance(root.left, p, cur + 1);
        if (left == -1) {
            return distance(root.right, p, cur + 1);
        }
        return left;
    }
}

// one pass
class Solution {
    int distance = 0;

    public int findDistance(TreeNode root, int p, int q) {
        dfs(root, p, q);
        return distance;
    }
    
    /**
		The return value means the distance from root node to EITHER p OR q. If
		neither p nor q are reachable from the root, return -1.
		
		It is either p or q but not both, because if the root node can reach both 
		p and q, it is a common ancestor of p and q and the answer should already 
		be available.
        
        
	**/
    private int dfs(TreeNode root, int p, int q) {
        if (root == null) {
            return -1;
        }
        int left = dfs(root.left, p, q);
        int right = dfs(root.right, p, q);
        // 当root是LCA的时候可以计算distance，有两种情况
        // 1. root.val == p || root.val == q，此时root是p或q其中的一个，
        // distance = (left >= 0 ? left : right) + 1;
        // 2. left >= 0 && right >= 0，此时root不是p或q任何一个
        // distance = left + right + 2;
        if (root.val == p || root.val == q) {
            if (left >= 0 || right >= 0) {
                distance = (left >= 0 ? left : right) + 1;
            }
            return 0;
        }
        if (left >= 0 && right >= 0) {
            distance = left + right + 2;
            // The reason we return -1 here is that since we have already found the answer and
            // updated the "distance", we don't want to update it anymore because that would be
            // wrong. So after we found and record the answer, we return -1 to "cheat" the previous
            // method calls that we didn't find p and q so that the method won't start calculating
            // the distance nor recording the result.
            return -1;
        }
        if (left >= 0) {
            return left + 1;
        }
        if (right >= 0) {
            return right + 1;
        }
        return -1;
    }
}
