class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 2) {
            return nums.length;
        }
        int j = 2;
        for (int i = 2; i < nums.length; i++) {
            // 注意这里是j - 2，不是i - 2，因为现在要给nums[j]赋值，当然要从j往前看2个。we have to look k steps backward in new updated array.
            if (nums[i] > nums[j - 2]) { // 把j - 2换成j - k就推广到最多k个重复。
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
}

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        if (nums.length <= 2) {
            return nums.length;
        }
        int j = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (count < 2) { //把2换成k就推广到最多k个重复。
                    nums[j++] = nums[i];
                    count++;
                }
                
            } else {               
                nums[j++] = nums[i];                
                count = 1;
            }
        }
        return j;
    }
}
