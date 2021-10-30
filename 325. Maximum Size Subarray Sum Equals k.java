class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int maxLen = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // 什么都没有的时候sum是0
        map.put(0, -1);
        
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            // 因为是求最长的长度，所以要保留最先出现的sum的位置
            map.putIfAbsent(sum, i);
            if (map.containsKey(sum - k)) {
                // 从0到i的和是sum，从0到map.get(sum - k)的和是sum-k，所以从map.get(sum - k)+1到i的和是k，长度是i - map.get(sum - k)
                maxLen = Math.max(maxLen, i - map.get(sum - k));
            }
        }
        return maxLen;
    }
}
