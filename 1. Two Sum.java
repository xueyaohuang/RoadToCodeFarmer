class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        int[] res = new int[2];
        for (int i = 0; i < len; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                return res; // return early
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}
