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
    Map<Integer, Integer> map;
    public boolean checkEqualTree(TreeNode root) {
        map = new HashMap<>();
        int sum = sumFromHere(root);
        if (sum % 2 != 0) {
            return false;
        }
        if (sum == 0) {
            return map.get(sum) > 1;
        }
        return map.containsKey(sum / 2);
    }
    
    private int sumFromHere(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = sumFromHere(root.left);
        int right = sumFromHere(root.right);
        int sum = root.val + left + right;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
    }
}

class Solution {
    Map<TreeNode, Integer> map;
    public boolean checkEqualTree(TreeNode root) {
        map = new HashMap<>();
        sumFromHere(root);
        int sum = map.get(root);
        if (sum % 2 != 0) {
            return false;
        }
        for (TreeNode n : map.keySet()) {
            if (n != root && map.get(n) == sum / 2) {
                return true;
            }
        }
        return false;
    }
    
    private int sumFromHere(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = sumFromHere(root.left);
        int right = sumFromHere(root.right);
        int sum = root.val + left + right;
        map.put(root, sum);
        return sum;
    }
}

class Solution {
    public boolean checkEqualTree(TreeNode root) {
        if (root == null) {
            return false;
        }
        int sum = getSum(root);
        if (sum % 2 != 0) {
            return false;
        }
        boolean[] canEqualSplit = new boolean[1];
        checkEqualTreeHelper(root.left, sum / 2, canEqualSplit);
        checkEqualTreeHelper(root.right, sum / 2, canEqualSplit);
        return canEqualSplit[0];
    }
    
    private int checkEqualTreeHelper(TreeNode root, int target, boolean[] canEqualSplit) {
        if (root == null) {
            return 0;
        }
        int left = checkEqualTreeHelper(root.left, target, canEqualSplit);
        int right = checkEqualTreeHelper(root.right, target, canEqualSplit);
        int sum = root.val + left + right;
        if (sum == target) {
            canEqualSplit[0] = true;
        }
        return sum;
    }
    
    private int getSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + getSum(root.left) + getSum(root.right);
    }
}
