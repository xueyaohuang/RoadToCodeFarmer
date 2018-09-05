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
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        int i = 0;
        int j = 0;
        while (j < s.length() && (Character.isDigit(s.charAt(j)) || s.charAt(j) == '-')) {
            j++;
        }
        // while后，j到了左子树第一个'('的位置，或者超出s的index
        int val = Integer.valueOf(s.substring(i, j));
        TreeNode root = new TreeNode(val);
        if (j < s.length()) {
            i = j + 1;
            int count = 1; // 数'(' 和')'的个数一样多时停下，recurse这一substring得到左子树
            while (j < s.length() && count != 0) {
                j++;
                if (s.charAt(j) == '(') {
                    count++;
                }
                if (s.charAt(j) == ')') {
                    count--;
                }
            }
            // while后j到了左子树最外层')'的位置，或者超出s的index
            root.left = str2tree(s.substring(i, j));
        }
        j += 2; // 跳过右子树第一个'(''
        if (j < s.length()) {
            root.right = str2tree(s.substring(j, s.length() - 1)); // s.length() - 1是右子树不包含最后一个')''
        }
        return root;
    }
}
