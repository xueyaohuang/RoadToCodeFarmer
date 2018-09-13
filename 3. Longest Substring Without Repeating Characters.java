class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() < 2) {
            return s.length();
        }
        int len = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1); // 需要Math.max, 比如abcbda，在两个a之间还有别的重复的character
            }
            len = Math.max(len, i - j + 1);
            map.put(s.charAt(i), i);
        }
        return len;
    }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int start = 0;
        int maxLen = 0;
        int count = 0; // 有几个重复
        int[] map = new int[256];
        for (int end = 0; end < len; end++) {
            char cend = s.charAt(end);
            if (map[cend]++ > 0) {
                count++;
            }
            while (count > 0) {
                char cstart = s.charAt(start);
                if (map[cstart]-- > 1) { // map[cstart]-- > 1，在--之前大于1，表示之前有重复的，现在被消除了一个重复
                    count--;
                }
                start++;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}
