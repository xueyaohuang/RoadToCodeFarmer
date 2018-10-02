[Description](https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/)

import java.util.*;
public class MyClass {
    
    public int longestEqualOneZero(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int curSum = 0;
        int len = nums.length;
        int maxLen = 0;
        map.put(-1, 0);
        for (int i = 0; i < len; i++) {
            curSum += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(curSum)) {
                maxLen = Math.max(maxLen, i - map.get(curSum));
            } else {
                map.put(curSum, i);
            }
        }
        return maxLen;
    }
    
    public static void main(String args[]) {
        
        MyClass mc = new MyClass();
        
        int[] nums = {1, 0, 0, 1, 0, 1, 1};
        
        int res = mc.longestEqualOneZero(nums);

        System.out.println(res);
    }
}
