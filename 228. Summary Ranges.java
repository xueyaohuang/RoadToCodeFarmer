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
                if (start == i) {
                    res.add(String.valueOf(nums[start]));
                } else {
                    res.add(nums[start] + "->" + nums[nums.length - 1]);
                }
            }
        }
        return res;
    }
}

class Solution {
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        int len = nums.length;
        int start = 0;
        int end = 1;
        while (end < len) {
            while (end < len && nums[end - 1] + 1 == nums[end]) {
                end++;
            }
            if (start == end - 1) {
                res.add(String.valueOf(nums[start]));
            } else {
                res.add(nums[start] + "->" + nums[end - 1]);
            }
            start = end;
            end++;
        }
        if (start < len) {
            if (start == end - 1) {
                res.add(String.valueOf(nums[start]));
            } else {
                res.add(nums[start] + "->" + nums[end - 1]);
            }
        }
        return res;
    }
}
