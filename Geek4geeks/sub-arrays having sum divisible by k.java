// 跟lc523， 560很像
// sol1
import java.util.*;
public class Solution {
    public static int subarraySumModK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        int sum = 0;
        int mod = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 确保mod是正数，nums中有负数时很重要
            // 比如k = 5, mod1 = 2, mod2 = -3, 虽然mod1不等于mod2，但是这时sum array sum还是可以mod 5需要把mod2转换成2
            mod = (sum % k + k) % k; 
            if (map.containsKey(mod)) {
                count += map.get(mod);
            }
            map.put(mod, map.getOrDefault(mod, 0) + 1);
        }
        return count;
    }
    
    public static void main(String args[]) {
        int[] nums = {4, 5, 0, -12, -23, 1};
        int res = subarraySumModK(nums, 5);
        System.out.println(res);
    }
}

//sol2
import java.util.*;
public class Solution {
    public static int subarraySumModK(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        int sum = 0;
        int[] mod = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            mod[(sum % k + k) % k]++;
        }
        
        for (int i = 0; i < k; i++) {
            if (mod[i] > 1) {
                count += mod[i] * (mod[i] - 1) / 2;
            }
        }
        // add the elements which are divisible by k itself 
        count += mod[0]; 
        return count;
    }
    
    public static void main(String args[]) {
        int[] nums = {4, 5, 0, -2, -3, 1};
        int res = subarraySumModK(nums, 5);
        System.out.println(res);
    }
}
