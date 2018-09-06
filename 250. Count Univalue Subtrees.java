class Solution {
    private int count = 0;
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        isUnivalSubTree(root);
        return count;
    }
    
    private boolean isUnivalSubTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = isUnivalSubTree(root.left);
        boolean right = isUnivalSubTree(root.right);
        if (left && right) {
            if (root.left != null && root.left.val != root.val) {
                return false;
            }
            if (root.right != null && root.right.val != root.val) {
                return false;
            }
            count++;
            return true;
        }
        return false;
    }
}

// 如果不想用instance variable，可以在主函数中声明一个长度为1的整形数组，但是不能用int，因为java参数传递的问题
// java 参数传递是传value，不是reference，所以如果count是个int，那么在深层递归函数中不能改变count的值。
// 但是如果count是个object，比如int[]或者List<Integer> 之类的，可以改变object的内容
class Solution {
    
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int[] count = new int[1]; 
        isUnivalSubTree(root, count);
        return count[0];
    }
    
    private boolean isUnivalSubTree(TreeNode root, int[] count) {
        if (root == null) {
            return true;
        }
        boolean left = isUnivalSubTree(root.left, count);
        boolean right = isUnivalSubTree(root.right, count);
        if (left && right) {
            if (root.left != null && root.left.val != root.val) {
                return false;
            }
            if (root.right != null && root.right.val != root.val) {
                return false;
            }
            count[0]++;
            return true;
        }
        return false;
    }
}
