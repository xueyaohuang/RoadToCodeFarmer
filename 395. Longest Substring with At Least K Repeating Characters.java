// O(n), sliding window
// 直接没法用sliding window，需要增加限定条件
class Solution {
    public int longestSubstring(String s, int k) {
        int max = 0;
        for (int i = 1; i <= 26; i++) {
            max = Math.max(max, longestSubstringWithNUniqueChar(s, k, i));
        }
        return max;
    }
    
    private int longestSubstringWithNUniqueChar(String s, int k, int n) {
        int[] map = new int[26];
        int numUnique = 0;
        int numNoLessThank = 0;
        int start = 0;
        int max = 0;
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map[c - 'a'] == 0)  {
                numUnique++;
            }
            map[c - 'a']++;
            if (map[c - 'a'] == k)  {
                numNoLessThank++;
            }
            while (numUnique > n) {
                if (map[s.charAt(start) - 'a'] == 1)  {
                    numUnique--;
                }
                if (map[s.charAt(start) - 'a'] == k)  {
                    numNoLessThank--;
                }
                map[s.charAt(start) - 'a']--;
                start++;
            }
            if (numUnique == n && numUnique == numNoLessThank) {
                max = Math.max(max, end - start + 1);
            }
        }
        return max;
    }
}

class Solution {
    public int longestSubstring(String s, int k) {
        return helper(s.toCharArray(), 0, s.length(), k);
    }
    private int helper(char[] chs, int start, int end, int k) {
        if (end - start < k) {
            return 0;
        }
        int[] freq = new int[26];
        for (int i = start; i < end; i++) {
            freq[chs[i] - 'a']++;
        }
        for (int i = start; i < end; i++) {
            if (freq[chs[i] - 'a'] < k) {
                int j = i + 1;
                while (j < end && freq[chs[j] - 'a'] < k) {
                    j++;
                }
                return Math.max(helper(chs, start, i, k), helper(chs, j, end, k));
            }
        }
        return end - start;
    }
}
