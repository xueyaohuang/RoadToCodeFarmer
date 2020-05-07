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

class Solution {
    public int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            if (map.containsKey(n)) {
                int freq = map.get(n);
                if (freq == len / 2) {
                    return n;
                }
                map.put(n, freq + 1);
            } else {
                map.put(n, 1);
            }
        }
        return -1;
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
