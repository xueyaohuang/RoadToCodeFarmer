# A general approach to level order traversal

```
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x; 
    }
 }
```

There are many questions that can be solved by level order traversal. We can use both BFS and DFS.

Let's first see the two templates of BFS and DFS.

1. Binary Tree Level Order Traversal

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

// DFS, or preorder way:
// 是按preorder的顺序添加的每一个元素，并不是一层一层加上去的。不过左右顺序还是从左到右
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderHelper(root, res, 0);
        return res;
    }
    
    private void levelOrderHelper(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(root.val);
        levelOrderHelper(root.left, res, level + 1);
        levelOrderHelper(root.right, res, level + 1);
    }
}
```

2. Binary Tree Level Order Traversal II

```
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

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderHelper(root, res, 0);
        return res;
    }
    
    private void levelOrderHelper(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(0, new ArrayList<Integer>());
        }
        res.get(res.size() - 1 - level).add(root.val);
        levelOrderHelper(root.left, res, level + 1);
        levelOrderHelper(root.right, res, level + 1);
    }
}
```

3. Binary Tree Zigzag Level Order Traversal

```
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

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        levelOrderHelper(root, res, 0);
        return res;
    }
    
    private void levelOrderHelper(TreeNode root, List<List<Integer>> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(new ArrayList<Integer>());
        }
        if (level % 2 == 0) {
            res.get(level).add(root.val);
        } else {
            res.get(level).add(0, root.val);
        }
        levelOrderHelper(root.left, res, level + 1);
        levelOrderHelper(root.right, res, level + 1);
    }
}
```


4. Average of Levels in Binary Tree

```
class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<Double>();
        }
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add((double)sum / size);
        }
        return res;
    }
}

class Solution {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) {
            return new ArrayList<Double>();
        }
        List<Double> res = new ArrayList<>();
        int height = getHeight(root, 0);
        long[] sum = new long[height];
        int[] count = new int[height];
        averageOfLevelsHelper(root, sum, count, 0, height);
        for (int i = 0; i < height; i++) {
            res.add((double)sum[i] / count[i]);
        }
        return res;
    }
    
    private void averageOfLevelsHelper(TreeNode root, long[] sum, int[] count, int level, int height) {
        if (root == null) {
            return;
        }
        sum[level] += root.val;
        count[level]++;
        averageOfLevelsHelper(root.left, sum, count, level + 1, height);
        averageOfLevelsHelper(root.right, sum, count, level + 1, height);
    }
    
    private int getHeight(TreeNode root, int curHeight) {
        if (root == null) {
            return curHeight;
        }
        return 1 + Math.max(getHeight(root.left, curHeight), getHeight(root.right, curHeight));
    }
}
```


5. Binary Tree Right Side View

```
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            res.add(queue.peek().val);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return res;
    }
}

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        rightViewHelper(root, res, 0);
        return res;
    }
    private void rightViewHelper(TreeNode root, List<Integer> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(root.val);
        }
        rightViewHelper(root.right, res, level + 1);
        rightViewHelper(root.left, res, level + 1);    
    }
}
```

6. Find Largest Value in Each Tree Row

```
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}

class Solution {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }
    
    private void dfs(TreeNode root, List<Integer> res, int level) {
        if (root == null) {
            return;
        }
        if (res.size() == level) {
            res.add(root.val);
        }
        if (root.val > res.get(level)) {
            res.set(level, root.val);
        }
        dfs(root.left, res, level + 1);
        dfs(root.right, res, level + 1);
    }
}
```


7. Populating Next Right Pointers in Each Node

```
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode temp = root;
        while (temp != null) {
            TreeLinkNode inner = temp;
            while (inner != null) {
                if (inner.left != null) {
                    inner.left.next = inner.right;
                }
                if (inner.right != null && inner.next != null) {
                    inner.right.next = inner.next.left;
                }
                inner = inner.next;
            }
            temp = temp.left;
        }
    }
}

public class Solution {
    public void connect(TreeLinkNode root) {
        // recursion终止条件
        if (root == null || root.left == null) {
            return;
        }
        
        // 把当前这一层要干的全部事情说清楚
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        
        // 进入左右子树的递归
        connect(root.left);
        connect(root.right);
    }
}
```

8. 623. Add One Row to Tree

```
class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty() && depth < d - 1) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        for (TreeNode node : queue) {
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = new TreeNode(v);
            node.right = new TreeNode(v);
            node.left.left = left;
            node.right.right = right;
        }
        return root;
    }
}

class Solution {
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newRoot = new TreeNode(v);
            newRoot.left = root;
            return newRoot;
        }
        if (root == null) {
            return null;
        }
        if (d == 2) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = new TreeNode(v);
            root.right = new TreeNode(v);
            root.left.left = left;
            root.right.right = right;
        }
        addOneRow(root.left, v, d - 1);
        addOneRow(root.right, v, d - 1);
        return root;
    }
}
```
