# method1
class Solution {
    public int majorityElement(int[] nums) {
        int majority = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                count = 1;
                majority = nums[i];
            }
            else if (nums[i] == majority) {
                count++;
            }
            else {
                count--;
            }
        }
        return majority;
    }
}

# method2
public class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
