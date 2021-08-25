class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 妙啊
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int reminder = sum % k;
            if (map.containsKey(reminder)  && i - map.get(reminder) > 1) {
                return true;
            }
            map.putIfAbsent(reminder, i);
        }
        return false;
    }
}
