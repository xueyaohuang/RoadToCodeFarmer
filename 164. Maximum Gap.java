// 1. bucket

class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int maxGap = 0;
        int len = nums.length;
        int maxNum = nums[0];
        int minNum = nums[0];
        for (int i = 0; i < len; i++) {
            maxNum = Math.max(maxNum, nums[i]);
            minNum = Math.min(minNum, nums[i]);
        }
        int avgGap = (maxNum - minNum) / (len - 1); // avgGap是3.9也只取3.
        if (avgGap == 0) { // avgGap至少是1，才能分bucket
            avgGap++;
        }
        int numBucket = (maxNum - minNum) / avgGap + 1;
        int[] minBucket = new int[numBucket];  //每个bucket中的最大值
        int[] maxBucket = new int[numBucket];  //每个bucket中的最小值
        for (int i = 0; i < len; i++) {
            int idx = (nums[i] - minNum) / avgGap;
            maxBucket[idx] = Math.max(maxBucket[idx], nums[i]);  
            if (minBucket[idx] == 0 || minBucket[idx] > nums[i]) {
                minBucket[idx] = nums[i];
            }        
        }
        // 由于bucket里的两个数差值不超过avg gap，总有两个数的gap要超过avg gap，这两个数只能在两个bucket之间，也就是
        // 下一个bucket中的最小，和上一个bucket的最大之差中有一个是最小的。
        for (int i = 0; i < numBucket; i++) {
            maxGap = Math.max(maxGap, minBucket[i] - minNum);
            minNum = maxBucket[i] == 0 ? minNum : maxBucket[i];
        }
        return maxGap;
    }
}

// 2. naive way, sort and loop

class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int maxGap = 0;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            maxGap = Math.max(maxGap, nums[i] - nums[i - 1]);
        }
        return maxGap;
    }
}
