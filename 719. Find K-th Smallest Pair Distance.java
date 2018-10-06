// counting sort 1
// O(n^2)
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int maxDiff = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                maxDiff = Math.max(maxDiff, Math.abs(nums[i] - nums[j]));
            }
        }
        
        int[] freq = new int[maxDiff + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                freq[Math.abs(nums[i] - nums[j])]++;
            }
        } 
        
        int count = 0;
        for (int i = 0; i <= maxDiff; i++) {
            count += freq[i];
            if (count >= k) {
                return i;
            }
        }
        return maxDiff;
    }
}

// counting sort 2
// O(n^2)
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        int n = 1000000;
        int[] freq = new int[n];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                freq[Math.abs(nums[i] - nums[j])]++;
            }
        } 
        
        int count = 0;
        for (int i = 0; i <= n; i++) {
            count += freq[i];
            if (count >= k) {
                return i;
            }
        }
        return n;
    }
}

// binary search
// 这种题，直接求不好求，但是可以知道结果的范围，然后在这个范围内用binary search，类似的还有下面这个题。
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int start = 0;
        int end = nums[len - 1] - nums[0];
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            // 下面注释的代码有误，两个数的和是这么写的，但这个题是两个数的差。
            // int i = 0, j = len - 1;
            // int count = 0;
            // while (i < j) {
            //     if (nums[j] - nums[i] > mid) {
            //         j--;
            //     } else {
            //         count += j - i;
            //         i++;
            //     }
            // }
            int j = 0;
            int count = 0;
            // 求出距离小于等于mid的pair的个数
            for (int i = 0; i < len; i++) {
                while (j < len && nums[j] - nums[i] <= mid) { // 注意这里必须有等号，否则有可能等于mid的距离有很多但是只数了一个
                    j++;
                }
                count += j - 1 - i;
            }
            if (count >= k) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}

// Place k elements such that minimum distance is maximized
// https://www.geeksforgeeks.org/place-k-elements-such-that-minimum-distance-is-maximized/
import java.util.*;

public class MyClass {
    
    public static int largestMinDist(int[] nums, int k) { 
        // Sort the positions 
        Arrays.sort(nums); 
      
        // Initialize result. 
        int res = 0; 
        int n = nums.length;
        // Consider the maximum possible distance 
        int start = 0;
        int end = nums[n-1] - nums[0]; 
      
        // Do binary search for largest 
        // minimum distance 
        while (start < end) { 
            int mid = (start + end) / 2; 
      
            // If it is possible to place k  
            // elements with minimum distance mid,  
            // search for higher distance. 
            if (canPlace(mid, nums, n, k)) { 
                // Change value of variable max to 
                // mid if all elements can be 
                // successfully placed 
                res = Math.max(res, mid); 
                start = mid + 1; 
            } else {   // If not possible to place k elements,  search for lower distance 
                end = mid;
            }
        } 
        return res; 
    } 
    
    public static boolean canPlace(int mid, int[] nums, int n, int k) { 
        // Place first element at arr[0] position 
        int cur = nums[0]; 
      
        // Initialize count of elements placed. 
        int count = 1; 
      
        // Try placing k elements with minimum 
        // distance mid. 
        for (int i = 1; i < n; i++) { 
            if (nums[i] - cur >= mid) { 
                // Place next element if its 
                // distance from the previously 
                // placed element is greater 
                // than current mid 
                cur = nums[i]; 
                count++; 
      
                // Return if all elements are  
                // placed successfully 
                if (count == k) {
                    return true; 
                }
            } 
        } 
        return false; 
    } 
    
    public static void main(String args[]) {
        int nums[] = {1, 2, 7, 5, 11, 12}; 
        int k = 3; 
        System.out.print(largestMinDist(nums, k)); 
    }
}

