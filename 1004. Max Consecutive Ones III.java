// sliding window
class Solution {
    public int longestOnes(int[] nums, int k) {
        // 记录当前window内有多少个1
        int numOfOne = 0;
        int start = 0;
        int res = 0;
        for (int end = 0; end < nums.length; end++) {
            numOfOne += nums[end];
            // 如果当前window的长度减去1的个数大于k，就移动start
            while (end - start + 1 - numOfOne > k) {
                numOfOne -= nums[start];
                start++;
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}
