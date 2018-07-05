class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
// 如果是Longest Increasing Array， 就只需要一个for loop。相当于只考虑j = i - 1, 没有for (int j = 0; j< i; j++)。
// 与LCS（sequence）的区别：LCS是确定的关系（==），这个题时不确定的关系（increasing， i.e. >）。所以不能只要一个for loop，不能写成以下代码：
// for (int i = 1; i < len; i++) {
//     if (nums[i - 1] < nums[i]) {
//         dp[i] = dp[i - 1] + 1;
//     }
//     else {
//         dp[i] = dp[i - 1];
//     }
// }
