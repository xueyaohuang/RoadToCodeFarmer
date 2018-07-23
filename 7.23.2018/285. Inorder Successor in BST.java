/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 没有利用是BST
class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode q = root;
        while (!stack.isEmpty() || q != null) {
            if (q != null) {
                stack.push(q);
                q = q.left;
            }
            else {
                TreeNode node = stack.pop();
                if (node == p) {
                    if (node.right != null) {
                        TreeNode right = node.right;
                        while (right.left != null) {
                            right = right.left;
                        }
                        return right;
                    }
                    else if (!stack.isEmpty()){
                        return stack.pop();
                    }
                    else {
                        return null;
                    }
                }
                q = node.right;
            }
        }
        return null;
    }     
}

class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        else {
            TreeNode left = inorderSuccessor(root.left, p);
            return left == null ? root : left;
        }
    }
}

/*
Predecessor

public TreeNode predecessor(TreeNode root, TreeNode p) {
  if (root == null)
    return null;

  if (root.val >= p.val) {
    return predecessor(root.left, p);
  } else {
    TreeNode right = predecessor(root.right, p);
    return (right != null) ? right : root;
  }
}
*/

class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> ls = new ArrayList<>();
        traverse(root, ls);
        for(int i = 0; i < ls.size() - 1; i++){
            if(ls.get(i) == p){
                return ls.get(i + 1);
            }
        }
        return null;
    }
    private void traverse(TreeNode root, List<TreeNode> ls){
        if(root == null) return;
        else{
            traverse(root.left, ls);
            ls.add(root);
            traverse(root.right, ls);
        }
    }
}

class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode cur=root;
        Stack<TreeNode>stk=new Stack<>();
        
        while(cur!=null || !stk.isEmpty()){
            while(cur!=null){
                stk.push(cur);
                cur=cur.left;
            }
            cur=stk.pop();
            if(cur.val>p.val){
                return cur;
            }
            cur=cur.right;            
        }
        
        return null;
    }
}
