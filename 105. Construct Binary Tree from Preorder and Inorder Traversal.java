/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 用preStart确定root
// 用inStart和inEnd确定子树的范围
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

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++)
            map.put(inorder[i], i);

        TreeNode root=new TreeNode(preorder[0]);
        TreeNode p=root;
        Stack<TreeNode> tree=new Stack<>();
        tree.push(p);

        for(int i=1;i<preorder.length;i++){
            int temp=map.get(preorder[i]);
            TreeNode node=new TreeNode(preorder[i]);

            if(temp<map.get(tree.peek().val)){
                p.left=node;
                p=p.left;
            }
            else {
                while(!tree.isEmpty()&&temp>map.get(tree.peek().val))
                    p=tree.pop();
                p.right=node;
                p=p.right;
            }
            tree.push(node);
        }
        return root;
    }
}
