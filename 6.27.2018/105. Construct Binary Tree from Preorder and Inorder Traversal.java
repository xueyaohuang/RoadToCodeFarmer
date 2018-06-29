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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return treeHelper(0, 0, inorder.length - 1, preorder, inorder);
    }
    private TreeNode treeHelper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
            }
        }
        root.left = treeHelper(preStart + 1, inStart, rootIndex - 1, preorder, inorder);
        root.right = treeHelper(preStart + rootIndex - inStart + 1, rootIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}

public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0) return null;
    Stack<TreeNode> s = new Stack<>();
    TreeNode root = new TreeNode(preorder[0]), cur = root;
    for (int i = 1, j = 0; i < preorder.length; i++) {
        if (cur.val != inorder[j]) {
            cur.left = new TreeNode(preorder[i]);
            s.push(cur);
            cur = cur.left;
        } else {
            j++;
            while (!s.empty() && s.peek().val == inorder[j]) {
                cur = s.pop();
                j++;
            }
            cur = cur.right = new TreeNode(preorder[i]);
        }
    }
    return root;
}

## 106. Construct Binary Tree from Inorder and Postorder Traversal

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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return treeHelper(postorder.length - 1, 0, inorder.length - 1, inorder, postorder);
    }
    private TreeNode treeHelper(int posEnd, int inStart, int inEnd, int[] inorder, int[] postorder) {
        if (posEnd < 0 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[posEnd]);
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
            }
        }
        root.left = treeHelper(posEnd - (inEnd - rootIndex) - 1, inStart, rootIndex - 1, inorder, postorder);
        root.right = treeHelper(posEnd - 1, rootIndex + 1, inEnd, inorder, postorder);
        return root;
    }
}

 public TreeNode buildTree(int[] inorder, int[] postorder) {
        
        if( inorder.length == 0 ) return null;
        // last element in the postorder is the root of the tree
         TreeNode root = new TreeNode(postorder[postorder.length-1]);
         Stack<TreeNode> st = new Stack<>();
         st.push(root);
        
        for(int i=postorder.length-2, j = inorder.length-1;i>=0;--i){
            //postorder[i] is the right child till the element in inorder traversal is not equal (i.e.rightmost node)
            if( st.peek().val != inorder[j] ){
                st.push(st.peek().right = new TreeNode(postorder[i]));
            }else{
                TreeNode temp = null;
                //pop  till all the elements matching inorder elements are removed 
                while(!st.empty() && st.peek().val == inorder[j]){
                     temp = st.pop(); j--;
                }
                st.push(temp.left =  new TreeNode(postorder[i])); //continue in post-order fashion
            } 
        }
       return root;
}
