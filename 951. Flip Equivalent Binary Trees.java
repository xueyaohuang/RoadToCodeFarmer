/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 
Analysis:

In worst case, the recursion corresponds to a perfect quaternary (means 4-nary) tree, which has 4 ^ d = N ^ 2 nodes, and we have to traverse all nodes. d = logN is the depth of the binary tree.

One worst case for input:
two perfect binary trees: root1 & root2.

    Root1's nodes are all 0s;
    Root2's nodes are all 0s, with the exception that left and right bottoms are both 1s.
but
The problem states Each value in each tree will be a unique integer in the range [0, 99], hence we have the following deduction:

ir1.left.val == r2.left.val and r1.left.val == r2.right.val, 

at most 1 of the 2 relations is true; otherwise r2.left.val == r2.right.val, this contradicts the above problem constraint.

Therefore, at least 1 out of flipEquiv(r1.left, r2.left) and flipEquiv(r1.left, r2.right) will terminate; Similiarly, at least 1 out of flipEquiv(r1.right, r2.right) andflipEquiv(r1.right, r2.leftt) will terminate.

Obviously at most 2 out of the 4 recursive calls could go all the way down to the bottom.

That is why the time is O(N).

Without the aforementioned constraint, all of the 4 recursive calls could expand the 4-nary tree to the bottom and result time O(N ^ 2).
 */
class Solution {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == root2;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return (flipEquiv(root1.right, root2.right) && flipEquiv(root1.left, root2.left)) || (flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right));
    }
}
