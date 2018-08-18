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

# method3
class Solution {
    public int majorityElement(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int isOne = 0;
            for (int j = 0; j < nums.length; j++) {
                if ((nums[j] & (1 << i)) != 0) {
                    isOne++;
                } else {
                    isOne--;
                }
            }
            if (isOne > 0) {
                res |= (1 << i);
            }
        }
        return res;
    }
}
