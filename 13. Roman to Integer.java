class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int cur = map.get(s.charAt(i));
            int next = map.get(s.charAt(i + 1));
            if (cur >= next) {
                res += cur;
            } else {
                res -= cur;
            }
        }
        res += map.get(s.charAt(s.length() - 1));
        return res;
    }
}

class Solution {
    public int romanToInt(String s) {
        int[] nums = new int[s.length()];
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'I') {
                nums[i] = 1;
            }
            else if (c == 'V') {
                nums[i] = 5;
            }
            else if (c == 'X') {
                nums[i] = 10;
            }
            else if (c == 'L') {
                nums[i] = 50;
            }
            else if (c == 'C') {
                nums[i] = 100;
            }
            else if (c == 'D') {
                nums[i] = 500;
            }
            else if (c == 'M') {
                nums[i] = 1000;
            }
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                sum += nums[i];
            }
            else {
                sum -= nums[i];
            }
        }
        sum += nums[nums.length - 1];
        return sum;
    }
}
