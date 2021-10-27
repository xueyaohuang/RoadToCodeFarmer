// O(n)
class Solution {
    public int missingElement(int[] nums, int k) {
        int res = nums[0] + k;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= res) {
                res++;
            }  else {
                return res;
            }
        }
        return res;
    }
}

// O(lgn)
// int missing = nums[mid] - nums[0] - mid;  means the number of missing numbers between nums[0] and nums[mid]
// assume the answer is nums[0] + k + x
// the range of x is  between 0 and nums.length - 1, when it's 0, it means nums[1] is greater than nums[0] + k
// when it's nums.length - 1, it means nums is consecutive
// we will perform binary search on x
class Solution {
    public int missingElement(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end + 1) / 2;
            int missing = nums[mid] - nums[0] - mid;
            if (missing >= k) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }
        return nums[0] + k + start;
    }
}
/*
Thinking process of how we update start and end:
1.when missing < k, use num=[1 3 6 7 8 9], k=2 as an example,
mid = 1, missing = nums[mid] - nums[0] - mid = 3 - 1 - 1  = 1 < k,
we need to move start to mid or mid + 1, we need to ask can start move to mid + 1?
no, since the answer is 4, finally we need start to stop at 1, if start move to mid + 1, which is 2, it will be out of boundry.
so start should be updated to mid.

2. when missing == k, use nums=[1 4 6 7 8 9], k=2 as an example,
mid = 1, missing = nums[mid] - nums[0] - mid = 4 - 1 - 1 = 2 == k,
we need to move end to mid or mid - 1.
In the template, if we update start to mid, we should update end to mid - 1.
 
3. when missing > k, obviously we need to move end to mid - 1
*/
