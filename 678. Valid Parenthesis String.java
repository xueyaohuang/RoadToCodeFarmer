// https://leetcode.com/problems/valid-parenthesis-string/discuss/543521/Java-Count-Open-Parenthesis-O(n)-time-O(1)-space-Clean-Explain
class Solution {
    public boolean checkValidString(String s) {
        int openMax = 0;
        int openMin = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openMax++;
                openMin++;
            } else if (c == ')') {
                openMax--;
                openMin--;
            } else {
                openMax++;
                openMin--;
            }
            if (openMax < 0) {
                return false;
            }
            openMin = Math.max(openMin, 0);
        }
        return openMin == 0;
    }
}
