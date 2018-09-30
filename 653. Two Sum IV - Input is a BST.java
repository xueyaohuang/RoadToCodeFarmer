// 自己的方法
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        set.add(root.val);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (set.contains(k - node.val) && k - node.val != node.val) {
                return true;
            }
            if (node.left != null) {
                queue.offer(node.left);
                set.add(node.left.val);
            }
            if (node.right != null) {
                queue.offer(node.right);
                set.add(node.right.val);
            }
        }
        
        return false;
    }
}

// best 
class Solution {
    private TreeNode root;
    private int k;
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        this.root = root;
        this.k = k;
        return dfs(root);
    }
    
    private boolean dfs(TreeNode node) {
        if (node == null) {
            return false;
        }
        // 注意是search(root, k - node.val),不是search(node, k - node.val)
        return dfs(node.left) || (k != 2 * node.val && search(root, k - node.val)) || dfs(node.right);
    }
    
    private boolean search(TreeNode node, int target) {
        if (node == null) {
            return false;
        }
        if (node.val == target) {
            return true;
        } else if (node.val < target) {
            return search(node.right, target);
        } else {
            return search(node.left, target);
        }
    }
}

// inorder and search array
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
    
    private void inorder(TreeNode root, List<Integer> list) {
        if (root.left != null) {
            inorder(root.left, list);
        }
        list.add(root.val);
        if (root.right != null) {
            inorder(root.right, list);
        }
    }
}

// use set
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        return findTargetHelper(root, set, k);
    }
    
    private boolean findTargetHelper(TreeNode root, Set<Integer> set, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTargetHelper(root.left, set, k) || findTargetHelper(root.right, set, k);
    }
}

// 自己的答案，很慢
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        return findTargetHelper(root, root.left, k) || findTargetHelper(root, root.right, k);
    }
    
    private boolean findTargetHelper(TreeNode p, TreeNode q, int k) {
        if (p == null || q == null) {
            return false;
        }
        if (p != q && p.val + q.val == k) {
            return true;
        } else if (p.val + q.val < k) {
            return findTargetHelper(p.right, q, k) || findTargetHelper(p, q.right, k);
        } else {
            return findTargetHelper(p.left, q, k) || findTargetHelper(p, q.left, k);
        }
    }
}
