/*
1. Calculate prefix sum of input
2. Maintain a sliding window, there are 2 cases, firstLen subarray is before secondLen subarray, and secondLen subarray is before firstLen subarray
3. Traverse through array, firstLen before secondLen, find maxium value of firstLen subarray and fix to it, add this value to secondLen subarray,
compare with result to find max
4. Traverse through array, secondLen before firstLen, find maxium value of secondLen subarray and fix to it, add this value to firstLen subarray,
compare with result to find max

*/
class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int res = 0;
        int leftMAX = 0;
        for (int i = firstLen + secondLen; i <= n; i++) {
            leftMAX = Math.max(leftMAX, sum[i - secondLen] - sum[i - secondLen - firstLen]);
            res = Math.max(res, leftMAX + sum[i] - sum[i - secondLen]);
        }
        leftMAX = 0;
        for (int i = firstLen + secondLen; i <= n; i++) {
            leftMAX = Math.max(leftMAX, sum[i - firstLen] - sum[i - firstLen - secondLen]);
            res = Math.max(res, leftMAX + sum[i] - sum[i - firstLen]);
        }
        return res;
    }
}
