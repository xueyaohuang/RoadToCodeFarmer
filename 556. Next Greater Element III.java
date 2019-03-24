class Solution {
    public int nextGreaterElement(int n) {
        int[] nums = toArray(n);
        int index1 = -1;
        int index2 = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i- 1]) {
                index1 = i - 1;
                break;
            }        
        }
        if (index1 == -1) {
            return -1;
        }
        for (int i = nums.length - 1; i > index1; i--) {
            if (nums[i] > nums[index1]) {
                index2 = i;
                break;
            }
        }
        swap(nums, index1, index2);
        reverse(nums, index1 + 1, nums.length - 1);
        long res = toLong(nums);
        return res > Integer.MAX_VALUE ? -1 : (int)res;
    }
    
    private int[] toArray(int n) {
        List<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n % 10);
            n /= 10;
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(res.length - 1 - i);
        }
        return res;
    }
    
    private long toLong(int[] nums) {
        long res = 0;
        for (int i : nums) {
            res *= 10;
            res += i;
        }
        return res;
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
