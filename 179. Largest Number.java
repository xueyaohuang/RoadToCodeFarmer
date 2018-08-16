class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        String[] digits = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            digits[i] = String.valueOf(nums[i]);
            // digits[i] = Integer.toString(nums[i]);
        }
        // Comparator<String> comp = new Comparator<String>() {
        //     public int compare(String str1, String str2) {
        //         String s1 = str1 + str2;
        //         String s2 = str2 + str1;
        //         return s2.compareTo(s1);
        //     }
        // };
        // Arrays.sort(digits, comp);
        Arrays.sort(digits, (a, b) -> (b + a).compareTo(a + b));
        if (digits[0].charAt(0) == '0') {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < digits.length; i++) {
            res.append(digits[i]);
        }
        return res.toString();
    }
}
