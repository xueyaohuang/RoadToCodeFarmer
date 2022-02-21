/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        int count = 0;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode node = stack.pop();
                count++;
                if (count == k) {
                    return node.val;
                }
                cur = node.right;
            }
        }
        return 0;
    }
}

class Solution {
    int count = 0;
    int res = -1;
    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return res;
    }
    
    private void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        dfs(root.left, k);
        count++;
        if (count == k) {
            res = root.val;
            return;
        }
        dfs(root.right, k);
    }
}

/*
Follow up: Get tree node values into an array list by inorder travseral.
When insert or delete an element, first use binary search to find the index in the array list, then use list.add(index, value) and list.remove(index).
Do not operate on the tree.
To get the kth element, just do list.get(k).
*/
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return list.get(k - 1);
    }
    
    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }
}

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int left = countNodes(root.left);
        if (k <= left) {
            return kthSmallest(root.left, k);
        }
        else if (k > left + 1) {
            return kthSmallest(root.right, k - left - 1);
        }
        return root.val;
    }
    private int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
