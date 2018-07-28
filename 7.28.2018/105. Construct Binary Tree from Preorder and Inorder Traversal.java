class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return helper(preorder, inorder, 0, 0, inorder.length - 1);
    }
    private TreeNode helper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
                break;
            }
        }
        root.left = helper(preorder, inorder, preStart + 1, inStart, rootIndex - 1);
        root.right = helper(preorder, inorder, preStart + rootIndex - inStart + 1, rootIndex + 1, inEnd);
        return root;
    }
}

class Solution {
    int pre = 0;
    int in = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfs(preorder, inorder, null);
    }
    private TreeNode dfs(int[] preorder, int[] inorder, TreeNode preRoot) {
        if (in > inorder.length - 1 || (preRoot != null && preRoot.val == inorder[in])) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pre]);
        pre++;
        root.left = dfs(preorder, inorder, root); // 当root.left遇到 root.val == inorder[in]，说明dfs找到这个root.left已经到底了，再没有left了。所以return null。
        in++;
        root.right = dfs(preorder, inorder, preRoot); // 当root.right preRoot.val == inorder[in]，说明dfs找到这个root.right已经到底了，再没有right了。所以return null。注意，这里的preRoot = preRoot，而dfs left时，preRoot = root。这事因为不论preorder还是inorder，都是right node最后访问，所以在preorder中，left的上一级是root，right的上一级是preRoot。
        return root;
    }
}

