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
// Space: O(N)
// Because each node we create a helper(), the recursion stack will cost O(N)

// Time: O(N log N) for a balanced tree, O(N^2) for a skew tree
// As mentioned above, the helper() runs O(N) time, and for each helper(), there is a for-loop to search the inorder index.

// For a balanced tree, the range of the search will be reduced by half each time, so the search costs O(log n)
// Therefore the time is O(N) * O(log N) = O(N log N)

// For a skew tree, the range of the search will only be reduced by 1, so the search still costs O(N)
// Therefore the time is O(N) * O(N) = O(N^2)
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length - 1);
    }
    
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIdx = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                rootIdx = i;
                break;
            }
        }
        root.left = buildTreeHelper(preorder, inorder, preStart + 1, inStart, rootIdx - 1);
        root.right = buildTreeHelper(preorder, inorder, preStart + rootIdx - inStart + 1, rootIdx + 1, inEnd);
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
