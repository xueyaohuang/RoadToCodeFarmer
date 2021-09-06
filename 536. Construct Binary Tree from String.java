/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/*
You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

 

Example 1:


Input: s = "4(2(3)(1))(6(5))"
Output: [4,2,6,3,1,5]
Example 2:

Input: s = "4(2(3)(1))(6(5)(7))"
Output: [4,2,6,3,1,5,7]
Example 3:

Input: s = "-4(2(3)(1))(6(5)(7))"
Output: [-4,2,6,3,1,5,7]
 

Constraints:

0 <= s.length <= 3 * 104
s consists of digits, '(', ')', and '-' only.
*/

class Solution {
    public TreeNode str2tree(String s) {
        if (s.equals("")) {
            return null;
        }
        int i = 0;
        while (i < s.length() && s.charAt(i) != '(' && s.charAt(i) != ')') {
            i++;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, i)));
        int count = 0;
        int leftStart = i;
        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                count++;
            } else if (s.charAt(i) == ')') {
                count--;
            }
            i++;
            if (count == 0) {
                break;
            }
        }
        if (i - 1 > leftStart) {
            root.left = str2tree(s.substring(leftStart + 1, i));
        }
        if (i < s.length() - 1) {
            root.right = str2tree(s.substring(i + 1, s.length() - 1));
        }
        return root;
    }
}

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
