class Solution {
    public String nextClosestTime(String time) {
        StringBuilder sb = new StringBuilder(time);
        int[] nums = new int[4];
        nums[0] = time.charAt(0) - '0';
        nums[1] = time.charAt(1) - '0';
        nums[2] = time.charAt(3) - '0';
        nums[3] = time.charAt(4) - '0';
        Arrays.sort(nums);
        
        // 先改最后一位
        for (int i = 1; i < 4; i++) {
            if (nums[i] > time.charAt(4) - '0') {
                sb.replace(4, 5, String.valueOf(nums[i]));
                return sb.toString();
            }
        }
        // 再改倒数第二位
        for (int i = 1; i < 4; i++) {
            if (nums[i] < 6 && nums[i] > time.charAt(3) - '0') {
                sb.replace(3, 5, String.valueOf(nums[i]) + String.valueOf(nums[0]));
                return sb.toString();
            }
        }
        // 改倒数第三位，要分第一位是2或不是2讨论一下，因为第一位是2，倒数第三位必须小于3，否则没有这个限制。
        for (int i = 1; i < 4; i++) {
            if (time.charAt(0) - '0' == 2 && nums[i] < 4 && nums[i] > time.charAt(1) - '0') {
                sb.replace(1, 2, String.valueOf(nums[i]));
                sb.replace(3, 5, String.valueOf(nums[0]) + String.valueOf(nums[0]));
                return sb.toString();
            }
            if (time.charAt(0) - '0' < 2 && nums[i] > time.charAt(1) - '0') {
                sb.replace(1, 2, String.valueOf(nums[i]));
                sb.replace(3, 5, String.valueOf(nums[0]) + String.valueOf(nums[0]));
                return sb.toString();
            }   
        }
        // 最后，如果前面都没有改动，说明第一位是最小的，就把第一位重复4次，但是是第二天的时间了。
        return String.valueOf(nums[0]) + String.valueOf(nums[0]) + ":" + String.valueOf(nums[0]) + String.valueOf(nums[0]);
    }
}
