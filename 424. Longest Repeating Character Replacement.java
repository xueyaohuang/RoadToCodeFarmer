class Solution {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        int[] count = new int[26];
        int maxLen = 0;
        int maxCount = 0;
        int start = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            // while loop only runs 1 step every time, so we don't need to update maxCount.
            // Also, we can replace while loop with a if statement.
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start++) - 'A']--;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}
