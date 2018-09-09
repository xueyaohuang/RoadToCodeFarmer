class NumArray {
    
    private int[] sum;
    private boolean isNull;

    public NumArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            isNull = true;
        }
        else {
            sum = new int[nums.length + 1];
            sum[1] = nums[0];
            for (int i = 2; i < sum.length; i++) {
                sum[i] = sum[i - 1] + nums[i - 1];
            }
        }
        
    }
    
    public int sumRange(int i, int j) {
        if (isNull) {
            return 0;
        }
        return sum[j + 1] - sum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
