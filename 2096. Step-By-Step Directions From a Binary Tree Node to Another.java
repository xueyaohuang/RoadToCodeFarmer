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
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = getLCA(root, startValue, destValue);
        StringBuilder sb = new StringBuilder();
        lcaToDest(lca, destValue, sb);
        return startToLca(lca, startValue) + sb.toString();
    }
    
    private TreeNode getLCA(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        if (root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = getLCA(root.left, p, q);
        TreeNode right = getLCA(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
    
    private String startToLca(TreeNode lca, int startValue) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(lca);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == startValue) {
                    return sb.toString();
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            sb.append("U");
        }
        return sb.toString();
    }
    
    private boolean lcaToDest(TreeNode lca, int destValue, StringBuilder sb) {
        if (lca == null) {
            return false;
        }
        if (lca.val == destValue) {
            return true;
        }
        sb.append('L');
        if (lcaToDest(lca.left, destValue, sb)) {
            return true;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append('R');
        if (lcaToDest(lca.right, destValue, sb)) {
            return true;
        }
        sb.deleteCharAt(sb.length() - 1);
        return false;
    }
}

class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        TreeNode lca = getLCA(root, startValue, destValue);
        StringBuilder res = new StringBuilder();
        getPath(lca, startValue, res);
        int n = res.length();
        res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            res.append("U");
        }
        getPath(lca, destValue, res);
        return res.toString();
    }
    
    private TreeNode getLCA(TreeNode root, int p, int q) {
        if (root == null) {
            return null;
        }
        if (root.val == p || root.val == q) {
            return root;
        }
        TreeNode left = getLCA(root.left, p, q);
        TreeNode right = getLCA(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
    
    private boolean getPath(TreeNode lca, int destValue, StringBuilder sb) {
        if (lca == null) {
            return false;
        }
        if (lca.val == destValue) {
            return true;
        }
        sb.append('L');
        if (getPath(lca.left, destValue, sb)) {
            return true;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append('R');
        if (getPath(lca.right, destValue, sb)) {
            return true;
        }
        sb.deleteCharAt(sb.length() - 1);
        return false;
    }
}


/*

 1. Build directions for both start and destination from the root.
      Say we get "LLRRL" and "LRR".
 2. Remove common prefix path.
      We remove "L", and now start direction is "LRRL", and destination - "RR"
 3. Replace all steps in the start direction to "U" and add destination direction.
      The result is "UUUU" + "RR".
*/
class Solution {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder sb = new StringBuilder();
        getPath(root, startValue, sb);
        String startPath = sb.toString();
        // System.out.println(startPath);

        sb = new StringBuilder();
        getPath(root, destValue, sb);
        String destPath = sb.toString();
        // System.out.println(destPath);
        int idx = 0;
        for (; idx < Math.min(startPath.length(), destPath.length()); idx++) {
            if (startPath.charAt(idx) != destPath.charAt(idx)) {
                 break;
            }
        }
        // System.out.println(idx);
        sb = new StringBuilder();
        for (int i = idx; i < startPath.length(); i++) {
            sb.append("U");
        }
        for (int i = idx; i < destPath.length(); i++) {
            sb.append(destPath.charAt(i));
        }
        return sb.toString();
    }
    
    private boolean getPath(TreeNode lca, int destValue, StringBuilder sb) {
        if (lca == null) {
            return false;
        }
        if (lca.val == destValue) {
            return true;
        }
        sb.append('L');
        if (getPath(lca.left, destValue, sb)) {
            return true;
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append('R');
        if (getPath(lca.right, destValue, sb)) {
            return true;
        }
        sb.deleteCharAt(sb.length() - 1);
        return false;
    }
}
