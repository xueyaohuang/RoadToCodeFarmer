/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */

public class Solution {
    public void connect(TreeLinkNode root) {
        while (root != null) { // check是否还有下一层nodes
            TreeLinkNode dummy = new TreeLinkNode(0);  // dummy node，dummy.next指向root下一层最左边的node，帮助来到下一层
            TreeLinkNode node = dummy; // node 就是一个moving node，不停地在当前层移动，来辅助把next point指向该指向的node
            while (root != null) { // 在这一层移动
                if (root.left != null) {
                    node.next = root.left; // 每层第一次执行这一步时，dummy.next指向了下一层最左边node
                    node = node.next;
                }
                if (root.right != null) {
                    node.next = root.right;
                    node = node.next;
                }
                root = root.next;
            }
            root = dummy.next;
        }
    }
}
