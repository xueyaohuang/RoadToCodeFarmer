class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (! map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{1, i, i});
            }
            else {
                int[] temp = map.get(nums[i]);
                temp[0]++;
                temp[2] = i;
            }
        }
        int deg = 0;
        int res = 0;
        for (int[] value : map.values()) {
            if (value[0] > deg) {
                deg = value[0];
                res = value[2] - value[1] + 1;
            }
            if (value[0] == deg) {
                res = Math.min(value[2] - value[1] + 1, res);
            }
        }
       return res;        
    }
}
