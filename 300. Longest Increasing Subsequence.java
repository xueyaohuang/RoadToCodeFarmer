// 11.6
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

/*
public static int binarySearch(int[] a,
                               int fromIndex,
                               int toIndex,
                               int key)
Returns:
index of the search key, if it is contained in the array within the specified range; otherwise,
(-(insertion point) - 1). The insertion point is defined as the point at which the key would be
inserted into the array: the index of the first element in the range greater than the key, or
toIndex if all elements in the range are less than the specified key. Note that this guarantees
that the return value will be >= 0 if and only if the key is found.
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = 0;
        int[] dp = new int[nums.length];
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (len == i) {
                len++;
            }
        }
        return len;
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
