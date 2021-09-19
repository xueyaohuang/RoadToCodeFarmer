/*
I don't think any one can understand this solution without seeing an example, here is an example:
2,3,6,5,4,1

Solution:
Step1, from right to left, find the first number which not increase in a ascending order. In this case which is 3.
Step2, here we can have two situations:

2a.We cannot find the number, all the numbers increasing in a ascending order. This means this permutation is the last permutation, we need to rotate back to the first permutation. So we reverse the whole array, for example, 6,5,4,3,2,1 we turn it to 1,2,3,4,5,6.

2b.We can find the number, then the next step, we will start from right most to leftward, try to find the first number which is larger than 3, in this case it is 4.
Then we swap 3 and 4, the list turn to 2,4,6,5,3,1.
Last, we reverse numbers on the right of 4, we finally get 2,4,1,3,5,6.
*/

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length < 2) {
            return;
        }
        // step 1
        int idx = nums.length - 2;
        while  (idx >= 0 && nums[idx] >= nums[idx + 1]) {
            idx--;
        }
        // step 2a
        if (idx < 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        // step 2b
        int idx1 = nums.length - 1;
        while (nums[idx1] <= nums[idx]) {
            idx1--;
        }
        swap(nums, idx, idx1);
        reverse(nums, idx + 1, nums.length - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int index1 = -1;
        int index2 = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i- 1]) {
                index1 = i - 1;
                break;
            }        
        }
        if (index1 == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        for (int i = nums.length - 1; i > index1; i--) {
            if (nums[i] > nums[index1]) {
                index2 = i;
                break;
            }
        }
        swap(nums, index1, index2);
        reverse(nums, index1 + 1, nums.length - 1);
        
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;        
    }
    
    private void reverse(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        for (int i = start; i <= (start + end) / 2; i++) {
            swap(nums, i, start + end - i);
        }
    }
}

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int idx1 = nums.length - 1;
        while (idx1 > 0 && nums[idx1 - 1] >= nums[idx1]) { 
            idx1--;   
        }
        if (idx1 == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        idx1--;
        int idx2 = nums.length - 1;
        while (idx2 > idx1 && nums[idx2] <= nums[idx1]) {
            idx2--;
        }
        swap(nums, idx1, idx2);
        reverse(nums, idx1 + 1, nums.length - 1);  
    }
    
    private void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
