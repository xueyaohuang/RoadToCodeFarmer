class Solution {
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }
        int len = A.length;
        int j = 0;
        for (int i = 0; i < len; i++) {
            if (A[i] % 2 == 0) {
                swap(A, i, j);
                j++;
            }
        }
        return A;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
