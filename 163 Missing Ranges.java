// 考虑三点： 1. overflow， Handle overflow by converting to long.
// 2. 开头和结尾
// 3. 每次衔接，相差一个数就只加一个数，相差超过一个数需要加一个范围 a->b
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
