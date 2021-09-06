// 考虑的Corner cases： 
// 1. overflow， Handle overflow by converting to long.
// 2. 开头和结尾
// 3. 每次衔接，相差一个数就只加一个数，相差超过一个数需要加一个范围 a->b
// 4. nums中可能有重复的数要跳过
// 5. nums是空或者null

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (lower == upper) {
            if (nums.length == 0) {
                res.add(String.valueOf(lower));
                return res;
            }
            return res;
        }
        if (nums.length == 0) {
            res.add(lower + "->" + upper);
            return res;
        }
        if (nums[0] > lower) {
            if (nums[0] - lower > 1) {
                res.add(lower + "->" + (nums[0] - 1));
            } else {
                res.add(String.valueOf(lower));
            }
        }
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 2) {
                res.add((nums[i - 1] + 1) + "->" + (nums[i] - 1));
            } else if (nums[i] - nums[i - 1] > 1) {
                res.add(String.valueOf(nums[i - 1] + 1));
            }
        }
        if (nums[nums.length - 1] < upper) {
            if (upper - nums[nums.length - 1] > 1) {
                res.add((nums[nums.length - 1] + 1) + "->" + upper);
            } else {
                res.add(String.valueOf(upper));
            }
        }
        return res;
    }
}

class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (lower > upper) {
            return res;
        }
        if (nums == null || nums.length == 0) {
            if (lower == upper) {
                res.add(lower + "");
            } else {
                res.add(lower + "->" + upper);
            }
            return res;
        }
        if (lower < (long)nums[0] - 1) {
            long a = (long)nums[0] - 1;
            res.add(lower + "->" + a);
        } else if (lower == (long)nums[0] - 1) {
            res.add(lower + "");
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 >= nums[i + 1]) {
                continue;
            } else if (nums[i] + 2 == nums[i + 1]) {
                int a = nums[i] + 1;
                res.add(a + "");
            } else if ((long)nums[i] + 2 < nums[i + 1]){
                long a = (long)nums[i] + 1;
                int b = nums[i + 1] - 1;
                res.add(a + "->" + b);
            }
        }
        if ((long)nums[nums.length - 1] + 1 < upper) {
            long a = nums[nums.length - 1] + 1;
            res.add(a + "->" + upper);
        } else if ((long)nums[nums.length - 1] + 1 == upper) {
            res.add(upper + "");
        }
        return res;
    }
}
