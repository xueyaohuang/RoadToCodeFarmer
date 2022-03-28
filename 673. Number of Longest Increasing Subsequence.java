class Solution {
    public int findNumberOfLIS(int[] nums) {
        int[] len = new int[nums.length];
        int[] cnt = new int[nums.length];
        int max = 1;
        int count = 0;
        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j]; // 注意不是cnt[i] = 1
                    }
                    else if (len[i] == len[j] + 1) {
                        cnt[i] += cnt[j]; // 注意不是cnt[i]++
                    }
                }
            }
            max = Math.max(max, len[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (len[i] == max) {
                count += cnt[i];
            }
        }
        return count;
    }
}
