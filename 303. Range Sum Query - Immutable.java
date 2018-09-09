class NumArray {
    
    private boolean isNull;
    private int[] sumEndHere;

    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            isNull = true;
        }
        int len = nums.length;
        sumEndHere = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sumEndHere[i] = sumEndHere[i - 1] + nums[i - 1];
        }
    }
    
    public int sumRange(int i, int j) {
        if (isNull) {
            return 0;
        }
        return sumEndHere[j + 1] - sumEndHere[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
