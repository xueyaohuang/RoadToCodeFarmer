* [题目](https://github.com/xueyaohuang/RoadToCodeFarmer/blob/master/mianjing/largerTree.png)
```
import java.util.*;

class TreeNode {
    
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

public class MyClass {
    
    public static TreeNode deserialize(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(nums[0]);
        queue.offer(root);
        int idx = 1;
        int len = nums.length;
        
        while (!queue.isEmpty() && idx < len) {
            TreeNode node = queue.poll();
            if (nums[idx] != -1) {
                node.left = new TreeNode(nums[idx]);
                queue.offer(node.left);
            }
            idx++;
            if (idx >= len) {
                break;
            }
            if (nums[idx] != -1) {
                node.right = new TreeNode(nums[idx]);
                queue.offer(node.right);
            }
            idx++;
        }
        return root;
    }
    
    public static String largerBranch(TreeNode root) {
        if (root == null) {
            return "";
        }
        int leftSum = sumRootedHere(root.left);
        int rightSum = sumRootedHere(root.right);
        if (leftSum == rightSum) {
            return "";
        } else if (leftSum < rightSum) {
            return "Right";
        } else {
            return "Left";
        }
    }
    
    public static int sumRootedHere(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.val + sumRootedHere(root.right) + sumRootedHere(root.left);
    }
    
    public static void main(String args[]) {
        
        int[] nums = {3,6,2,9,-1,10};
        TreeNode n1 = deserialize(nums);
        // TreeNode n2 = n1.left;
        // TreeNode n3 = n1.right;
        // TreeNode n4 = n2.left;
        // TreeNode n5 = n2.right;
        
        String res = largerBranch(n1);
        System.out.println(res);
    }
}

```
