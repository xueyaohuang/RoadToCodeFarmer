class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return false;
        }
        Map<Integer, Integer> reminder = new HashMap<>();
        reminder.put(0, -1);//妙啊
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) {
                sum %= k;
            }
            if (reminder.containsKey(sum)) {  // 不能写成if (reminder.containsKey(sum) && i - reminder.get(sum) > 1),否则else的执行会有问题
                 if (i - reminder.get(sum) > 1) {
                     return true;
                 }             
            } else {
                reminder.put(sum, i);
            }
        }
        return false;
    }
}
