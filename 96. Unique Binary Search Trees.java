// BST: All nodes in the left subtree are smaller than the root!!!!!!!
// numRoot is vaules from 1, 2... numRoot that are stored in the BST. --> outer for loop
// left is the number of nodes in the left subtree when the value of the root is left + 1 --> inner for loop.
// 当选定了左subtree的node个数时，root的value也就确定了，比如n=9, left=3，那么root value必然是4，并且left nodes必然是1，2，3，这是BST性质决定的。
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) { // i means number of total nodes
            for (int left = 0; left < i; left++) { // left means number of left nodes
                dp[i] += dp[left] * dp[i - left - 1];
            }
        }
        return dp[n];
    }
}
