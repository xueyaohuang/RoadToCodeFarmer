class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> toDeleteNodes = new HashSet<>();
        for (int i : to_delete) {
            toDeleteNodes.add(i);
        }
        deleteNodesHelper(root, toDeleteNodes, res, false);
        return res;
    }
    
    // helper 要做的是对当前root的操作，而不要想着对root.left和root.right操作，对child的操作会在recursion里
    private TreeNode deleteNodesHelper(TreeNode root, Set<Integer> toDeleteNodes, List<TreeNode> temp, boolean hasParent) {
        if (root == null) {
            return null;
        }
        boolean deleteCur = toDeleteNodes.contains(root.val);
        if (!hasParent && !deleteCur) {
            temp.add(root);
        }
        root.left = deleteNodesHelper(root.left, toDeleteNodes, temp, !deleteCur);
        root.right = deleteNodesHelper(root.right, toDeleteNodes, temp, !deleteCur);
        return deleteCur ? null : root;
    }
}

class Solution {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        Set<Integer> toDeleteNodes = new HashSet<>();
        for (int i : to_delete) {
            toDeleteNodes.add(i);
        }
        deleteNodesHelper(root, toDeleteNodes, res, true, null, false);
        return res;
    }
    
    // helper 要做的是对当前root的操作，而不要想着对root.left和root.right操作，对child的操作会在recursion里
    private void deleteNodesHelper(TreeNode root, Set<Integer> toDeleteNodes, List<TreeNode> temp, boolean isParentDeleted, TreeNode parent, boolean isLeft) {
        if (root == null) {
            return;
        }
        boolean deleteCur = toDeleteNodes.contains(root.val);
        if (isParentDeleted && !deleteCur) {
            temp.add(root);
        }
        if (deleteCur && parent != null) {
            if (isLeft) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        deleteNodesHelper(root.left, toDeleteNodes, temp, deleteCur, root, true);
        deleteNodesHelper(root.right, toDeleteNodes, temp, deleteCur, root, false);
    }
}
