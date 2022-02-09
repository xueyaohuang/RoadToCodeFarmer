class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length(), start = 0, maxLen = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < len; end++) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                start = Math.max(start, map.get(c) + 1); // 需要Math.max, 比如abba，在两个a之间还有别的重复的b
            }
            maxLen = Math.max(maxLen, end - start + 1);
            map.put(c, end);
        }
        return maxLen;
    }
}
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int max = 0;
        int[] map = new int[256];
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map[c]++;
            while (map[c] > 1) {
                map[s.charAt(left)]--;
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
