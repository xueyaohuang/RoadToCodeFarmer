/*
There's no edge case for this question. The initial step is to extend the window to its limit, that is,
the longest we can get to with maximum number of modifications. Until then the variable start will remain at 0.

Then as end increase, the whole substring from 0 to end will violate the rule, so we need to update start accordingly (slide the window).
We move start to the right until the whole string satisfy the constraint again. Then each time we reach such situation, we update our max length.

Awesome solution, but it needs some more detailed explanation.
Many people know the problem can be reiterated as :
"longest substring where (length - max occurrence) <= k"
The key point is we are focusing on "longest".
So assume we initially found a valid substring, what do we do next?

    Still valid even appended by one more char——then we are happy, just expand the window
    It became invalid. But at this time, do we have to shrink the window?
    ——No, we shift the whole window rightwards by one unit. So that the length is unchanged.
    The reason for that is , any index smaller than original "start", will never have the chance to lead a longer valid substring than current length of our window.
    Do we need to update max in time?
    ——No. Once again, we focus on "longest". The length of valid substring is determined by what?
    Max Occurrence + k
    We only need to update max occurrence when it becomes larger, because only that can we generate a longer valid substring.
    If we can't surpass the historic max occurrence, then we can not generate a longer valid substring from current "start", I mean the new windows's left bound. To some extend, this becomes a game to find max occurrence.
    Or , a better understanding is:
    "A game to eliminate inferior 'left bounds'.

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
            // Since we are only interested in the longest valid substring, our sliding windows need not shrink, even if a window may
            // cover an invalid substring. We either grow the window by appending one char on the right, or shift the WHOLE window to
            // the right by one. And we only grow the window when the count of the new char exceeds the historical max count (from a
            // previous window that covers a valid substring). That is, we do not need the accurate max count of the current window;
            // we only care if the max count exceeds the historical max count; and that can only happen because of the new char.
            // so that explains why we use if here instead of while, since when the window is invalid, we move the WHOLE window to the right by 1.
            // 用来取代的char应该和window内出现次数最多的char相同， end - start + 1 - maxCount就表示要想使window内所有char一样，需要改变的char的个数
            if (end - start + 1 - maxCount > k) {
                count[s.charAt(start++) - 'A']--;
            }
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
}
