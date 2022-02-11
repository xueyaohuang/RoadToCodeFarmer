class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] map = new int[128];
        int start = 0;
        int count = 0;
        int maxLen = 0;
        for (int end = 0; end < s.length(); end++) {
            char cEnd = s.charAt(end);
            if (map[cEnd] == 0) { // ++之前等于0，说明加完后多出一个distinct的char 
                count++;
            }
            map[cEnd]++;
            while (count > k) {
                char cStart = s.charAt(start);
                if (map[cStart] == 1) { // -- 之前 等于1，说明减完后会消除一个distinct的char
                    count--;
                }
                map[cStart]--;
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}
