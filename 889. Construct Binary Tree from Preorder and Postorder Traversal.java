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
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || post == null) {
            return null;
        }
        int len = pre.length;
        // stack里放的是没有完成的node，当stack.peek().val == post[j]，说明这个node和它的子树完全建立好了，就把这个node
        // 从stack里pop出来
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(pre[0]);
        stack.push(root);
        int postIdx = 0;
        
        for (int i = 1; i < len; i++) {
            TreeNode node = new TreeNode(pre[i]);
            while (stack.peek().val == post[postIdx]) {
                stack.pop();
                postIdx++;
            }
            if (stack.peek().left == null) {
                stack.peek().left = node;
            } else {
                stack.peek().right = node;
            }
            stack.push(node);
        }
        
        return root;
    }
}

// O(n2)
class Solution {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructFromPrePostHelper(pre, post, 0, 0, post.length - 1);
    }
    
    private TreeNode constructFromPrePostHelper(int[] pre, int[] post, int preStart, int postStart, int postEnd) {
        if (postStart > postEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        if (preStart == pre.length - 1) {
            return root;
        }
        int leftPost = postEnd;
        
        for (; leftPost >= postStart; leftPost--) {
            if (post[leftPost] == pre[preStart + 1]) {
                break;
            }
        }
        root.left = constructFromPrePostHelper(pre, post, preStart + 1, postStart, leftPost);
        root.right = constructFromPrePostHelper(pre, post, preStart + leftPost - postStart + 2, leftPost + 1, postEnd - 1);
        return root;
    }
}
