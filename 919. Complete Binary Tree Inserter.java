/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class CBTInserter {
    
    TreeNode root;
    Queue<TreeNode> allNodes;
    TreeNode curParent;

    public CBTInserter(TreeNode root) {
        this.root = root;
        this.allNodes = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        allNodes.offer(root);
        queue.offer(root);
        boolean foundFirstNonComplete = false;
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
                allNodes.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                allNodes.offer(cur.right);
            }
            if ((cur.left == null || cur.right == null) && (!foundFirstNonComplete)) {
                curParent = cur;
                foundFirstNonComplete = true;
            }
        }
        while (allNodes.peek() != curParent) {
            allNodes.poll();
        }
        allNodes.poll();
    }
    
    public int insert(int v) {
        if (curParent.left == null) {
            curParent.left = new TreeNode(v);
            allNodes.offer(curParent.left);
            return curParent.val;
        } else {
            curParent.right = new TreeNode(v);
            int result = curParent.val;
            allNodes.offer(curParent.right);
            curParent = allNodes.poll();
            return result;
        }
    }
    
    public TreeNode get_root() {
        return root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
 
 // sol 2:
 /*
Store tree nodes to a list self.tree in bfs order.
Node tree[i] has left child tree[2 * i + 1] and tree[2 * i + 2]

So when insert the Nth node (0-indexed), we push it into the list.
we can find its parent tree[(N - 1) / 2] directly.
*/
class CBTInserter {
    
    List<TreeNode> tree;
    
    public CBTInserter(TreeNode root) {
        tree = new ArrayList<>();
        tree.add(root);
        for (int i = 0; i < tree.size(); i++) {
            if (tree.get(i).left != null) {
                tree.add(tree.get(i).left);
            }
            if (tree.get(i).right != null) {
                tree.add(tree.get(i).right);
            }
        }
    }

    public int insert(int v) {
        int N = tree.size();
        TreeNode node = new TreeNode(v);
        tree.add(node);
        if (N % 2 == 1) {
            tree.get((N - 1) / 2).left = node;
        } else {
            tree.get((N - 1) / 2).right = node;
        }  
        return tree.get((N - 1) / 2).val;
    }

    public TreeNode get_root() {
        return tree.get(0);
    }
}
