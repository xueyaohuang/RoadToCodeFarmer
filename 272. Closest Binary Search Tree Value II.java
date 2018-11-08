/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // O(n)
 // 由于是BST，最后的k个数一定是inorder连续的k个数
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> dq = new LinkedList<>();
        inorder(root, target, k, dq);
        return dq;
    }
    
    // BST inorder遍历，由于是连续的k个数，最小的数从头poll，最大的数从尾部add。
    // 用一个deque，但是要返回List<Integer>， 可以用LinkedList
    private boolean inorder(TreeNode root, double target, int k, LinkedList<Integer> dq) {
        if (root == null) {
            return false;
        }
        
        if (inorder(root.left, target, k, dq)) {
            return true;
        }
        
        if (dq.size() == k) {
            if (Math.abs(dq.peekFirst() - target) < Math.abs(root.val - target)) {
                return true;
            } else {
                dq.pollFirst();
            }
        }
        
        dq.addLast(root.val);
        
        return inorder(root.right, target, k, dq);
    }
    
}
 
 // n(lgk)
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<Pair> pq = new PriorityQueue(k, new Comparator<Pair>() {
            public int compare(Pair p1, Pair p2) {
                return Double.compare(p2.val, p1.val);
            }
        });
        dfs(root, target, k, pq);
        List<Integer> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.poll().key);
        }
        return res;
    }
    
    private void dfs(TreeNode root, double target, int k, PriorityQueue<Pair> pq) {
        if (root == null) {
            return;
        }
        if (pq.size() >= k) {
            if (Math.abs(root.val - target) < Math.abs(pq.peek().key - target)) {
                pq.poll();
                pq.offer(new Pair(root.val, Math.abs(root.val - target)));
            }
        } else {
            pq.offer(new Pair(root.val, Math.abs(root.val - target)));
        }
        dfs(root.left, target, k, pq);
        dfs(root.right, target, k, pq);
    }
    
    class Pair {
        int key;
        double val;
        public Pair(int key, double val) {
            this.key = key;
            this.val = val;
        }
    }
}
