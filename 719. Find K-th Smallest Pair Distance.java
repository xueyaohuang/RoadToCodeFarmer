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
            for (int i = 0; i < len; i++) {
                while (j < len && nums[j] - nums[i] <= mid) {
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
