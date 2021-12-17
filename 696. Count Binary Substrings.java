/*
Maintain the current character run length and previous character run length. If prevRunLength >= curRunLength, we have found a valid string.
*/

class Solution {
    public int countBinarySubstrings(String s) {
        int curconsecutiveLen = 1;
        int prevconsecutiveLen = 0;
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == s.charAt(i)) {
                curconsecutiveLen++;
            } else {
                prevconsecutiveLen = curconsecutiveLen;
                curconsecutiveLen = 1;
            }
            if (prevconsecutiveLen >= curconsecutiveLen) {
                res++;
            }
        }
        return res;
    }
}
