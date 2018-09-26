class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int sum = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) { // 跳过重复
                continue;
            }
            int left = i + 1, right = len - 1;
            while (left < right) {
                int temp = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - temp) < Math.abs(target - sum)) {
                        sum = temp;
                }
                if (temp == target) {
                    return target;
                } else if (temp > target) {
                    right--;
                    while (left < right && nums[right] == nums[right + 1]) { // 跳过重复
                        right--;
                    }
                } else {
                    left++;  
                    while (left < right && nums[left - 1] == nums[left]) { // 跳过重复
                        left++;
                    }
                }
            }
        }
        return sum;
    }
}
