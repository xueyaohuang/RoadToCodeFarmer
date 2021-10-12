/*
There's no edge case for this question. The initial step is to extend the window to its limit, that is,
the longest we can get to with maximum number of modifications. Until then the variable start will remain at 0.

Then as end increase, the whole substring from 0 to end will violate the rule, so we need to update start accordingly (slide the window).
We move start to the right until the whole string satisfy the constraint again. Then each time we reach such situation, we update our max length.
*/

class Solution {
    public int characterReplacement(String s, int k) {
        int len = s.length();
        // count记录window内每个char出现的次数
        int[] count = new int[26];
        int maxLen = 0;
        // maxCount记录window内出现次数最多的char出现的次数
        int maxCount = 0;
        int start = 0;
        for (int end = 0; end < len; end++) {
            maxCount = Math.max(maxCount, ++count[s.charAt(end) - 'A']);
            // while loop only runs 1 step every time, so we don't need to update maxCount.
            // Also, we can replace while loop with a if statement.
            // 用来取代的char应该和window内出现次数最多的char相同， end - start + 1 - maxCount就表示要想使window内所有char一样，需要改变的char的个数
            while (end - start + 1 - maxCount > k) {
                count[s.charAt(start++) - 'A']--;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}
