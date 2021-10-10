// dp[index][diff] equals to the length of arithmetic sequence at index with difference diff.
// Complexity, Time O(N^2), Space O(N^2)
class Solution {
    public int longestArithSeqLength(int[] A) {
        if (A.length <= 2) {
            return A.length;
        }
        int res = 2;
        Map<Integer, Integer>[] dp = new HashMap[A.length];
        for (int j = 0; j < A.length; j++) {
            dp[j] = new HashMap<>();
            for (int i = 0; i < j; i++) {
                int diff = A[j] - A[i];
                dp[j].put(diff, dp[i].getOrDefault(diff, 1) + 1);
                res = Math.max(res, dp[j].get(diff));
            }
        }
        return res;
    }
}
