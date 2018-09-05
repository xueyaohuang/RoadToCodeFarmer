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
