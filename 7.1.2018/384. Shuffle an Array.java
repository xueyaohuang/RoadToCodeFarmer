// https://www.geeksforgeeks.org/shuffle-a-given-array/

class Solution {
    
    private int[] sol;

    public Solution(int[] nums) {
        sol = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return sol;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] shuf = sol.clone();
        for (int i = 1; i < sol.length; i++) {
            int j = (int) (Math.random() * (i + 1));
            swap(shuf, i, j);
        }
        return shuf;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
