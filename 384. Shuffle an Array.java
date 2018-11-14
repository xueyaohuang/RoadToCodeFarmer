// https://www.geeksforgeeks.org/shuffle-a-given-array/
/*
Fisher–Yates shuffle Algorithm : O(n), assuming that the function rand() takes O(1) time.
数学归纳法证明：某个数在ith position的概率是1/n = (n-1)/n * (n-2)/(n-1) * ... * (n-i)/(n-i+1) * (1)/(n-i)
*/

class Solution {
    
    int[] sol;

    public Solution(int[] nums) {
        sol = nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return sol;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] shu = sol.clone();
        for (int i = 0; i < shu.length; i++) {// i可以从前往后，也可以从后往前
            // Math.random() returns a pseudorandom double greater than or equal to 0.0 and less than 1.0.
            int j = (int) (Math.random() * (i + 1));
            swap(shu, i, j);
        }
        return shu;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
