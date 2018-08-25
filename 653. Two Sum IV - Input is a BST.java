// best 
class Solution {
    int k;
    TreeNode root;
    public boolean findTarget(TreeNode root, int k) {
        this.k = k;
        this.root = root;
        return dfs(root);
    }
    private boolean dfs(TreeNode node) {
        if (null == node) return false;
        return dfs(node.left) || k > node.val && (search(root, k - node.val) || dfs(node.right));
    }
    private boolean search(TreeNode node, int value) {
        if (null == node) return false;
//        System.out.println("Search: "+value +", in "+node.val);
        if (value == node.val) return true;
        if (value < node.val) return search(node.left, value);
        return search(node.right, value);
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
