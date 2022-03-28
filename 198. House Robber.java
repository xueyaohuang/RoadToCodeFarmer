class Solution {
    public int rob(int[] nums) {
        int rob = nums[0];
        int notRob = 0;
        for (int i = 1; i < nums.length; i++) {
            int temp = notRob;
            notRob = Math.max(rob, notRob);
            rob = temp + nums[i]; // 不需要rob = Math.max(rob, temp + nums[i]), 因为rob第i个就一定不能rob i-1，而temp就是不rob i-1的最大值
        }
        return Math.max(rob, notRob);
    }
}

class Solution {
    public int rob(int[] nums) {
        int rob = 0, notRob = 0;
        for (int i : nums) {
            int preRob = rob;
            rob = notRob + i; //if rob current value, previous house must not be robbed
            notRob = Math.max(preRob, notRob); //if not rob ith house, take the max value of robbed (i-1)th house and not rob (i-1)th house
        }
        return Math.max(rob, notRob);
    }
}


// 变种：给定一个int array，元素有正有负。求一个sum最大的subsequence，保证每次跳过的元素最多为一个。
// 注意是subsequence，并且每次最多跳过一个元素，所以subsequence长度最少是len/2-1
// e.g. [-1,-2,-3,-4,-5]，最大是-6，取-2，-4.
// [-3,2,4,-1,-2,-5]，最大是4，取2，4，-2

public int maxSubsequenceSum(int[] nums) {
    if (nums == null || nums.length == 0) {
        return 0;
    }
    int len = nums.length;
    int pick = 0;
    int notPick = 0;
    for (int i = 0; i < len; i++) {
        int prePick = pick;
        pick = Math.max(pick, notPick) + nums[i]; // 这次pick，前面那次可以pick，也可以不pick
        notPick = prePick; // 这次不pick，前面那次必定要pick。
    }
    return Math.max(pick, notPick);
}

// 类似的题还有：Maximum sum subarray removing at most one element
// https://www.geeksforgeeks.org/maximum-sum-subarray-removing-one-element/
