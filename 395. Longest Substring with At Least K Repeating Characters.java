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
