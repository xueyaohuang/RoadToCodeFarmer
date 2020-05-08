class Solution {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        int len = A.length;
        for (int i = 1; i <= len - 1; i++) {
            int maxIdx = indexOfMax(A, len - i);
            res.add(maxIdx + 1);
            reverse(A, 0, maxIdx);
            reverse(A, 0, len - i);
            res.add(len);
        }
        return res;
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end)--;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private int find(int[] nums, int end) {
        int max = nums[0];
        int idx = 0;
        for (int i = 0; i <= end; i++) {
            if (max < nums[i]) {
                max = nums[i];
                idx = i;
            }
        }
        return idx;
    }
}
