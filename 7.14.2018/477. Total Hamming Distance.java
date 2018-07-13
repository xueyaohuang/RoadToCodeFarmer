class Solution {
    public int totalHammingDistance(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < 32; i++) {
            count += getHD(nums, i);
        }
        return count;
    }
    public int getHD(int[] arr, int b) {
        int countOne = 0;
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            countOne += (arr[i] >> b) & 1;
        }
        return countOne * (len - countOne);
    }
}
