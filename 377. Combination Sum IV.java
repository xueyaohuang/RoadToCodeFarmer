// unbounded knapsack, 两个for loop在某些情况下可以互换，某些情况不行
// 在这里不能互换，应为不同顺序的组合算不一样的，详见
// https://github.com/xueyaohuang/RoadToCodeFarmer/blob/master/Data%20Structures%20and%20Algorithms/dynamicProgramming.md#%E4%B8%A4%E9%87%8D-nested-for-loop%E8%B0%81%E5%85%88%E8%B0%81%E5%90%8E%E5%8C%BA%E5%88%AB%E6%98%AF%E6%95%B0%E4%B8%8D%E6%95%B0%E5%85%83%E7%B4%A0%E9%A1%BA%E5%BA%8F%E4%B8%8D%E5%90%8C%E7%9A%84%E7%BB%84%E5%90%88
class Solution {   
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i >= nums[j]) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
/*
The problem with negative numbers is that now the combinations could be potentially of infinite length. Think about nums = [-1, 1] and
target = 1. We can have all sequences of arbitrary length that follow the patterns -1, 1, -1, 1, ..., -1, 1, 1 and
1, -1, 1, -1, ..., 1, -1, 1 (there are also others, of course, just to give an example). So we should limit the length of the combination
sequence, so as to give a bound to the problem.
*/
// https://leetcode.com/problems/combination-sum-iv/discuss/85038/JAVA:-follow-up-using-recursion-and-memorization.
// use hashmap and recursion.
