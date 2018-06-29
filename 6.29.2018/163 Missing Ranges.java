// Handle overflow by converting to long.

public class Solution {
    /*
     * @param nums: a sorted integer array
     * @param lower: An integer
     * @param upper: An integer
     * @return: a list of its missing ranges
     */
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (lower > upper) {
            return res;
        }
        if (nums == null || nums.length == 0) {
            if (lower == upper) {
                res.add(lower + "");
            }
            else {
                res.add(lower + "->" + upper);
            }
            return res;
        }
        if (lower < (long)nums[0] - 1) {
            long a = (long)nums[0] - 1;
            res.add(lower + "->" + a);
        }
        else if (lower == (long)nums[0] - 1) {
            res.add(lower + "");
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 >= nums[i + 1]) {
                continue;
            }
            else if (nums[i] + 2 == nums[i + 1]) {
                int a = nums[i] + 1;
                res.add(a + "");
            }
            else if ((long)nums[i] + 2 < nums[i + 1]){
                long a = (long)nums[i] + 1;
                int b = nums[i + 1] - 1;
                res.add(a + "->" + b);
            }
        }
        if ((long)nums[nums.length - 1] + 1 < upper) {
            long a = nums[nums.length - 1] + 1;
            res.add(a + "->" + upper);
        }
        else if ((long)nums[nums.length - 1] + 1 == upper) {
            res.add(upper + "");
        }
        return res;
    }
}
