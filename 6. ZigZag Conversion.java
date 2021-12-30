class Solution {
    public String convert(String s, int numRows) {
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        int idx = 0;
        int len = s.length();
        while (idx < len) {
            for (int i = 0; i < numRows && idx < len; i++) {
                sbs[i].append(s.charAt(idx++));
            }
            for (int i = numRows - 2; i > 0 && idx < len; i--) {
                sbs[i].append(s.charAt(idx++));
            }
        }
        for (int i = 1; i < numRows; i++) {
            sbs[0].append(sbs[i]);
        }
        return sbs[0].toString();
    }
}

class Solution:
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """
        if numRows == 1 or len(s) <= numRows:
            return s
        L = [''] * numRows
        index = 0
        step = 1
        for c in s:
            L[index] += c
            if index == 0:
                step = 1
            elif index == numRows - 1:
                step = -1
            index += step
        return ''.join(L)
