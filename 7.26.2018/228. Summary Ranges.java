class Solution {
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<String>();
        }
        List<String> res = new ArrayList<String>();
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < nums.length - 1 && nums[i] + 1 != nums[i + 1]) {
                if (start == i) {
                    res.add(String.valueOf(nums[i]));
                } else {
                    res.add(nums[start] + "->" + nums[i]);
                }
                start = i + 1;
            }
            if (i == nums.length - 1) {
                if (start == nums.length - 1) {
                    res.add(String.valueOf(nums[start]));
                } else {
                    res.add(nums[start] + "->" + nums[nums.length - 1]);
                }
            }
        }
        return res;
    }
}
