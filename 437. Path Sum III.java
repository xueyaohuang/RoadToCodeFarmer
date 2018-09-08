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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // 开头不一定是root，所以要遍历开头
        // pathSum(root.left, sum) ， pathSum(root.right, sum) 就是在遍历开头，因为sum没变
        return pathSumFromHere(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFromHere(TreeNode root, int sum) { // 定义清楚这个函数是干啥的
        if (root == null) {
            return 0;
        }
        // 结尾不一定是leaf，所以要遍历结尾
        // pathSumFromHere(root.left xxx), pathSumFromHere(root.right xxx)就是在遍历结尾，因为sum减去了parent的val
        return (root.val == sum ? 1 : 0) + pathSumFromHere(root.left, sum - root.val) + pathSumFromHere(root.right, sum - root.val);
    }
}
