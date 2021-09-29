class Solution {
    public boolean isMonotonic(int[] A) {
        return isMonotonicIncreasing(A) || isMonotonicDecreasing(A);
    }
    
    private boolean isMonotonicIncreasing(int[] A) {
        int len = A.length;
        for (int i = 0; i < len - 1; i++) {
            if (A[i] > A[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isMonotonicDecreasing(int[] A) {
        int len = A.length;
        for (int i = 0; i < len - 1; i++) {
            if (A[i] < A[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

// soltion 2: 检查A或者reverse A是Monotonic Increasing

// one pass
class Solution {
    public boolean isMonotonic(int[] nums) {
        boolean isMonotoneIncreasing = true;
        boolean isMonotoneDecreasing = true;
        for (int i = 1; i < nums.length; i++) {
            isMonotoneIncreasing &= nums[i] <= nums[i - 1];
            isMonotoneDecreasing &= nums[i] >= nums[i - 1];
            if (!(isMonotoneIncreasing || isMonotoneDecreasing)) {
                return false;
            }
        }
        return true;
    }
}
