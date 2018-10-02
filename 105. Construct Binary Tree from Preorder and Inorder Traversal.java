/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// 用preStart确定root
// 用inStart和inEnd确定子树的范围
// Space: O(N)
// Because each node we create a helper(), the recursion stack will cost O(N)

// Time: O(N log N) for a balanced tree, O(N^2) for a skew tree
// As mentioned above, the helper() runs O(N) time, and for each helper(), there is a for-loop to search the inorder index.

// For a balanced tree, the range of the search will be reduced by half each time, so the search costs O(log n)
// Therefore the time is O(N) * O(log N) = O(N log N)

// For a skew tree, the range of the search will only be reduced by 1, so the search still costs O(N)
// Therefore the time is O(N) * O(N) = O(N^2)
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return buildTreeHelper(preorder, inorder, 0, 0, inorder.length - 1);
    }
    
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int preStart, int inStart, int inEnd) {
        if (preStart >= preorder.length || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int rootIdx = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                rootIdx = i;
                break;
            }
        }
        root.left = buildTreeHelper(preorder, inorder, preStart + 1, inStart, rootIdx - 1);
        root.right = buildTreeHelper(preorder, inorder, preStart + rootIdx - inStart + 1, rootIdx + 1, inEnd);
        return root;
    }
}

public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0) return null;
    Stack<TreeNode> s = new Stack<>();
    TreeNode root = new TreeNode(preorder[0]), cur = root;
    for (int i = 1, j = 0; i < preorder.length; i++) {
        if (cur.val != inorder[j]) {
            cur.left = new TreeNode(preorder[i]);
            s.push(cur);
            cur = cur.left;
        } else {
            j++;
            while (!s.empty() && s.peek().val == inorder[j]) {
                cur = s.pop();
                j++;
            }
            cur = cur.right = new TreeNode(preorder[i]);
        }
    }
    return root;
}

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder == null || preorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<inorder.length;i++)
            map.put(inorder[i], i);

        TreeNode root=new TreeNode(preorder[0]);
        TreeNode p=root;
        Stack<TreeNode> tree=new Stack<>();
        tree.push(p);

        for(int i=1;i<preorder.length;i++){
            int temp=map.get(preorder[i]);
            TreeNode node=new TreeNode(preorder[i]);

            if(temp<map.get(tree.peek().val)){
                p.left=node;
                p=p.left;
            }
            else {
                while(!tree.isEmpty()&&temp>map.get(tree.peek().val))
                    p=tree.pop();
                p.right=node;
                p=p.right;
            }
            tree.push(node);
        }
        return root;
    }
}

// 给定一个BST的preorder traverse array，返回这个BST的root。
// 与上面不同在于，在preorder序列中找下一个大的element，可以用binary search
// Time: O(n) for a balanced tree, O(nlgn) for a skew tree

public class Solution {
    
    public static TreeNode buildBST(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return buildBSTHelper(preorder, 0, preorder.length - 1);
    }
    
    private static TreeNode buildBSTHelper(int[] preorder, int rootIdx, int end) {
        if (rootIdx >= preorder.length || rootIdx > end) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[rootIdx]);
        int rightRootidx = firstGreaterIdx(preorder, rootIdx);
        
        // int rightRootidx = rootIdx + 1;
        // while (rightRootidx < preorder.length && preorder[rightRootidx] < preorder[rootIdx]) {
        //     rightRootidx++;
        // }
        
        root.left = buildBSTHelper(preorder, rootIdx + 1, rightRootidx - 1);
        root.right = buildBSTHelper(preorder, rightRootidx, end);
        
        return root;
        
    }
    
    private static void printTreeInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printTreeInorder(root.left);
        System.out.println(root.val);
        printTreeInorder(root.right);
    }
    
    private static int firstGreaterIdx(int[] nums, int curIdx) {
        int start = curIdx + 1;
        int end = nums.length;
        while (start < end) {
            int mid = (start + end) /2;
            if (nums[mid] < nums[curIdx]) {
                start = mid + 1;
            } else if (nums[mid] > nums[curIdx]) {
                end = mid;
            }
        }
        return start;
    }
    
    public static void main(String args[]) {

        int[] preorder = {20, 10, 5, 1, 7, 15, 30, 25, 35, 32, 40};
        
        TreeNode root = buildBST(preorder);
        
        printTreeInorder(root);

    }
}

// O(n) time, since rootIdx goes from 0 to preorder.length - 1
public class Solution {
    
    int rootIdx = 0;
    
    public TreeNode buildBST(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return buildBSTHelper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private TreeNode buildBSTHelper(int[] preorder, int min, int max) {
        if (rootIdx >= preorder.length) {
            return null;
        }
        if (preorder[rootIdx] <= min || preorder[rootIdx] >= max) {
            return null;
        }
        
        TreeNode root = new TreeNode(preorder[rootIdx]);
        rootIdx++;
        root.left = buildBSTHelper(preorder, min, root.val);
        root.right = buildBSTHelper(preorder, root.val, max);
        
        return root;
        
    }
    
    private void printTreeInorder(TreeNode root) {
        if (root == null) {
            return;
        }
        printTreeInorder(root.left);
        System.out.println(root.val);
        printTreeInorder(root.right);
    }
    
    public static void main(String args[]) {
        
        Solution sol = new Solution();

        int[] preorder = { 20, 10, 5, 1, 7, 15, 30, 25, 35, 32, 40 };
        
        TreeNode root = sol.buildBST(preorder);
        
        sol.printTreeInorder(root);

    }
}

