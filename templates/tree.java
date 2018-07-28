// Binary Tree Level Order Traversal
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (! queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                temp.add(node.val);
            }
            res.add(temp);
        }
        return res;
    }
}

// Binary Tree Bottom-up Level Order Traversal, 顺序问题都是往前插入就行了，i.e. temp.add(0, node.val)或者res.add(0, temp)
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(0, temp);            
        }
        return res;
    }
}

// Binary Tree Zigzag Level Order Traversal
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;
        while (! queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                if (level % 2 == 0) {
                    temp.add(node.val);
                }
                else {
                    temp.add(0, node.val);
                }
            }
            level++;
            res.add(temp);
        }
        return res;
    }
}

// reverse inorder traversal: right->root->left. 
// EX. Leetcode 538	Convert BST to Greater Tree  
class Solution {
    
    private int sum = 0; // sum has to be a global variable.
    
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        convertBST(root.right);
        int temp = root.val;
        root.val += sum;
        sum += temp;
        convertBST(root.left);
        return root;
    }
}

class Solution {
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return root;
        }
        int sum = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            }
            else {
                TreeNode node = stack.pop();
                int temp = node.val;
                node.val += sum;
                sum += temp;
                p = node.left;
            }
        }
        return root;
    }
}

// 105. Construct Binary Tree from Preorder and Inorder Traversal

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
