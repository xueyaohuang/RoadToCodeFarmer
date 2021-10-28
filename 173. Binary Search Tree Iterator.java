/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class BSTIterator {
    
    Deque<TreeNode> stack;
    TreeNode cur;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        cur = root;
        pushLeft();
    }
    
    public int next() {
        TreeNode node = stack.pop();
        cur = node.right;
        pushLeft();
        return node.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    
    private void pushLeft() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }
}
/*
This is in fact average O(1) time. Think about the number of times a node has been visited
after iterating the whole tree. Each node has been visited twice. In some cases the
while loop doesn't execute, so that node at that call is only visited once. Where
does the other visit go? It goes to the while loop when you visit another node.
That's why it's "average" O(1) time.
*/

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

class BSTIterator {
    
    List<Integer> list;
    int idx;

    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        dfs(root);
    }
    
    public int next() {
        return list.get(idx++);
    }
    
    public boolean hasNext() {
        return idx < list.size();
    }
    
    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
}
