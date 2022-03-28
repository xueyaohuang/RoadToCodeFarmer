// 注意longest common substring 和 subsequence的区别，也就是连续和不连续的区别。
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}

// 如果是subsequence，那么就是
// print out the LCS, 一般都是用while loop从后往前打印
// https://www.geeksforgeeks.org/printing-longest-common-subsequence/

class Solution {
    public int findLength(int[] A, int[] B) {
        if (A == null || B == null) {
            return 0;
        }
        int lena = A.length;
        int lenb = B.length;
        int[][] dp = new int[lena + 1][lenb + 1];
        
        for (int i = 1; i <= lena; i++) {
            for (int j = 1; j <= lenb; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[lena][lenb];
    }
}
