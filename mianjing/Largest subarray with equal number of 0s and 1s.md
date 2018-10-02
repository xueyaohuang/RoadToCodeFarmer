[Description](https://www.geeksforgeeks.org/largest-subarray-with-equal-number-of-0s-and-1s/)

```
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
```

follow up: 如果nums不全是0，1，有别的数，怎么办？  
加一个index j，j只有在nums[i]是0或者1的时候才++  

```
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
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] != 0 && nums[i] != 1) {
                continue;
            }
            curSum += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(curSum)) {
                maxLen = Math.max(maxLen, j - map.get(curSum));
            } else {
                map.put(curSum, j);
            }
            j++;
        }
        return maxLen;
    }
    
    public static void main(String args[]) {
        
        MyClass mc = new MyClass();
        
        int[] nums = {1, 0, 0, 5, 6, 7, 1, 0, 1, 1};
        
        int res = mc.longestEqualOneZero(nums);

        System.out.println(res);
    }
}
```
