# Binary Tree
1. 对任何一棵二叉树T，如果叶节点个数为a, 度为2的结点数为b，则a=b+1.
2. [二叉树分类](https://www.geeksforgeeks.org/binary-tree-set-3-types-of-binary-tree/)：full, complete, perfect, balanced, degenerate
3. TreeNode
```
public class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
```
4. 树的遍历
* DFS
  * 前序(pre-order)：先根后左再右
  * 中序(in-order)：先左后根再右
  * 后序(post-order)：先左后右再根
* BFS 先访问根节点，沿着树的宽度遍历子节点，直到所有节点均被访问为止。
  * 层序(level-order)
* 前/中/后序遍历使用递归，也就是栈的思想对二叉树进行遍历，广度优先一般使用队列的思想对二叉树进行遍历。
* 如果已知中序遍历和前序遍历或者后序遍历，那么就可以完全恢复出原二叉树结构。其中最为关键的是前序遍历中第一个一定是根，而后序遍历最后一个一定是根，中序遍历在得知根节点后又可进一步递归得知左右子树的根节点。但是这种方法也是有适用范围的：元素不能重复！否则无法完成定位。

__pre-order__
```
// recursive
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        preorderTraversalHelper(root, res);
        return res;
    }
    private void preorderTraversalHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        preorderTraversalHelper(root.left, res);
        preorderTraversalHelper(root.right, res);
    }
}

// iterative
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                res.add(node.val);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
        return res;
    }
}
```


__in-order__
```
// recursive
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderTraversalHelper(root, res);
        return res;
    }
    private void inorderTraversalHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorderTraversalHelper(root.left, res);
        res.add(root.val);
        inorderTraversalHelper(root.right, res);
    }
}

// iterative
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                res.add(node.val);
                node = node.right;
            }
        }
        return res;
    }
}
```

__post-order__
```
// recursive
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderTraversalHelper(root, res);
        return res;
    }
    private void postorderTraversalHelper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        postorderTraversalHelper(root.left, res);
        postorderTraversalHelper(root.right, res);
        res.add(root.val);
    }
}

// iterative
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        // 注意add(index, element) 是 List<> interface的method，
        // addFirst(element)是Deque里的method。如果想用addFirst，则
        // 需要申明 LinkedList<Integer> res = new LinkedList<Integer>()
        // 或者 Deque<Integer> res = new LinkedList<Integer>();
        // 由于一直往最头部加元素，所以用LinkedList比ArrayList快。
        List<Integer> res = new LinkedList<Integer>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                res.add(0, node.val); 
                node = node.right;
            } else {
                node = stack.pop();
                node = node.left;
            }
        }
        return res;
    }
}
```

__level-order__
```
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (! queue.isEmpty()) {
            // 如果不需要知道每一层具体有哪些，可以不需要size，直接BFS，返回的是List<Integer>而不是List<List<Integer>>
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
```

__Morris Travseral__
```
// http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html
class TreeNode { 
    int val; 
    TreeNode left;
    TreeNode right; 
  
    public TreeNode(int val) { 
        this.val = val; 
        this.left = null;
        this.right = null; 
    }
}
  
public class BinaryTree { 
    /* Function to traverse binary tree without recursion and without stack */
    // time O(n), space O(1)
    public void inorderMorrisTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode prev = null; 
 
        while (cur != null) { 
            if (cur.left == null) { 
                System.out.print(cur.val + " ");  // do something here, can be other things other than just print the node's value
                cur = cur.right; 
            } else { 
                /* Find the inorder predecessor of current */
                prev = cur.left; 
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                /* Make current as right child of its inorder predecessor */
                if (prev.right == null) { 
                    prev.right = cur; 
                    cur = cur.left; 
                } else { /* Revert the changes made in if part to restore the original tree i.e., fix the right child of predecssor*/
                    prev.right = null;  // recover the right pointer of this node to null
                    System.out.print(cur.val + " ");  // do something here, can be other things other than just print the node's
                    cur = cur.right; 
                }
            }
        }
    }
    
    public void preorderMorrisTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode prev = null; 
 
        while (cur != null) { 
            if (cur.left == null) { 
                System.out.print(cur.val + " "); // do something here, can be other things other than just print the node's
                cur = cur.right; 
            } else { 
                /* Find the inorder predecessor of current */
                prev = cur.left; 
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                /* Make current as right child of its inorder predecessor */
                if (prev.right == null) {
                    // do something here, can be other things other than just print the node's
                    System.out.print(cur.val + " "); // the only difference with inorder-traversal
                    prev.right = cur; 
                    cur = cur.left; 
                } else { /* Revert the changes made in if part to restore the original tree i.e., fix the right child of predecssor*/
                    prev.right = null;  // recover the right pointer of this node to null
                    cur = cur.right; 
                }
            }
        }
    }
  
    public static void main(String args[]) { 
        /* Constructed binary tree is 
               1 
             /   \ 
            2      3 
          /  \ 
        4     5 
        */
        BinaryTree tree = new BinaryTree();
        
        TreeNode root = new TreeNode(1); 
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3); 
        TreeNode n4 = new TreeNode(4); 
        TreeNode n5 = new TreeNode(5); 
        
        
        root.left = n2; 
        root.right = n3; 
        n2.left = n4; 
        n2.right = n5; 
  
        tree.inorderMorrisTraversal(root);
        System.out.println();
        tree.preorderMorrisTraversal(root);
    } 
} 
```

5. __BST__ 使用中序遍历可得到有序数组，这是二叉查找树的又一个重要特征。
BST的search，insert，delete。
```
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class BinarySearchTree {
    
    TreeNode root; 
  
    public BinarySearchTree() {  
        root = null;  
    }
    
    // A utility function to search a given key in BST 
    public TreeNode search(TreeNode root, int key) { 
        // Base Cases: root is null or key is present at root 
        if (root == null || root.val == key) {
            return root;
        }
            
        // val is greater than root's key 
        if (root.val > key) {
            return search(root.left, key); 
        }
        // val is less than root's key 
        return search(root.right, key); 
    }
    
    // Iteratively way to search a key
    public TreeNode search(int key) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == key) {
                return cur;
            } else if (cur.val > key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return cur; 
    }
    
    // A recursive function to insert a new key in BST
    public void insert(int key) { 
      this.root = insertIntoBST(root, key); 
    }
      
    // 不插重复值
    private TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
    
    // iteratively way to insert不插重复值
    public void insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return;
        }
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > key) {
                if (cur.left == null) {
                    cur.left = new TreeNode(key);
                    return;
                } else {
                    cur = cur.left;
                }
            } else if (cur.val < key) {
                if (cur.right == null) {
                    cur.right = new TreeNode(key);
                    return;
                } else {
                    cur = cur.right;
                }
            } else {
                return;
            }
        }
    }
    
    // iteratively way to insert不插重复值
    public boolean insert(int key) {
        if (root == null) {
            root = new TreeNode(key);
            return true;
        }
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val > key) {
                if (cur.left == null) {
                    cur.left = new TreeNode(key);
                    return true;
                } else {
                    cur = cur.left;
                }
            } else if (cur.val < key) {
                if (cur.right == null) {
                    cur.right = new TreeNode(key);
                    return true;
                } else {
                    cur = cur.right;
                }
            } else {
                return false;
            }
        }
    }
    
    public void deleteKey(int key) { 
        this.root = deleteFromBST(root, key); 
    }
    
    // 1. root 没有子节点，或者只有一个子节点
    // 2. root有 有两个子节点
    private TreeNode deleteFromBST(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val == key) {
            // 1. root 没有子节点，或者只有一个子节点
            if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            } else { // 2. root有 有两个子节点
                // Get the inorder successor (smallest in the right subtree) 
                root.val = minRootValue(root.right);
                // Delete the inorder successor
                root.right = deleteFromBST(root.right, root.val);
            }
        } else if (root.val < key) {
            root.right = deleteFromBST(root.right, key);
        } else {
            root.left = deleteFromBST(root.left, key);
        }
        return root;
    }
    
    private int minRootValue(TreeNode root) {
        TreeNode node = root;
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node.val;
    }
    
    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        printTree(root.left);
        System.out.println(root.val);
        printTree(root.right);
    }
    
    public static void main(String[] args) {
        
        BinarySearchTree tree = new BinarySearchTree(); 
  
        /* Let us create following BST 
              50 
           /     \ 
          30      70 
         /  \    /  \ 
        20   40  60   80 */
        
        tree.insert(50); 
        tree.insert(30); 
        tree.insert(20); 
        tree.insert(40); 
        tree.insert(70); 
        tree.insert(60); 
        tree.insert(80); 
  
        System.out.println("Inorder traversal of the given tree"); 
        printTree(tree.root);
        
        // TreeNode node1 = tree.search(tree.root, 80);
        // System.out.println(node1.val);
        // TreeNode node2 = tree.search(80);
        // System.out.println(node2.val);
        // System.out.println(tree.root.val);
  
        // System.out.println("\nDelete 20"); 
        // tree.deleteKey(20); 
        // System.out.println("Inorder traversal of the modified tree"); 
        // printTree(tree.root);
  
        // System.out.println("\nDelete 30"); 
        // tree.deleteKey(30); 
        // System.out.println("Inorder traversal of the modified tree"); 
        // printTree(tree.root);
  
        // System.out.println("\nDelete 50"); 
        // tree.deleteKey(50); 
        // System.out.println("Inorder traversal of the modified tree"); 
        // printTree(tree.root);
    }
}
```
