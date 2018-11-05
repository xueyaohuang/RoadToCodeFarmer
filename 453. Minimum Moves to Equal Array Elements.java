/*
Adding 1 to n - 1 elements is the same as subtracting 1 from one element, w.r.t goal of making the elements in the array equal.
So, best way to do this is make all the elements in the array equal to the min element.
sum(array) - n * minimum
*/
class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return (int) (sum - min * n);
    }
}
