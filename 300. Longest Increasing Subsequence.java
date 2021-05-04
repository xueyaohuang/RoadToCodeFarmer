// subarray可以用 xxxEndHere + xxxSoFar
// subsequence 必须用dp，通常是两个for loop
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

// https://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
/*
At each loop, tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
For example, say we have nums = [4,5,6,3], then all the available increasing subsequences are:

len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
len = 3   :      [4, 5, 6]            => tails[2] = 6

We can easily prove that tails is a increasing array. Therefore it is possible to do a binary search in tails array to find the one needs update.

Each time we only do one of the two:

(1) if x is larger than all tails, append it, increase the size by 1
(2) if tails[i-1] < x <= tails[i], update tails[i]

Doing so will maintain the tails invariant. The the final answer is just the size.
*/

class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = 1;
        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 面试可以试试用Arrays.binarySearch
            // int idx = Arrays.binarySearch(tails, 0, len, num);
            int idx = binarySearch(tails, 0, len, nums[i]);
            if (idx < 0) {
                idx = -(idx + 1);
            }
            tails[idx] = nums[i];
            if (len == idx) {
                len++;
            }
        }
        return len;
    }
    
    private int binarySearch(int[] nums, int from, int to, int target) {
        int start = from;
        int end = to - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        // 不用检查 if (nums[start] == target)，因为跳出while后，target一定没在nums中找到
        if (nums[start] < target) {
            // 为什么如果nums[start] < target，insert position就是to？因为更新的时候如果nums[mid] < target，start = mid + 1（start动了），那么最终停下来的时候，
            // nums[start]应该比target大，或者start是最后一个。
            return -to - 1;
        }
        return -start - 1;
    }
}
// 如果是Longest Increasing Array， 就只需要一个for loop。相当于只考虑j = i - 1, 没有for (int j = 0; j< i; j++)。
