// https://leetcode.com/problems/valid-parenthesis-string/discuss/543521/Java-Count-Open-Parenthesis-O(n)-time-O(1)-space-Clean-Explain
/*
We count the number of ')' we are waiting for,
and it's equal to the number of open parenthesis.
This number will be in a range and we count it as [cmin, cmax]

cmax counts the maximum open parenthesis,
which means the maximum number of unbalanced '(' that COULD be paired.
cmin counts the minimum open parenthesis,
which means the number of unbalanced '(' that MUST be paired.

When you met "(", you know you need one only one ")", cmin = 1 and cmax = 1.
When you met "(*(", you know you need one/two/three ")", cmin = 1 and cmax = 3.
When you met "(***(", you know you need 0/1/2/3/4/5... ")", cmin = 0 and cmax = 5. cmin 不能小于0，因为The ')' shouldn't be used to match any '(' appearing after it.
*/
class Solution {
    public boolean checkValidString(String s) {
        // *当做（
        int openMax = 0;
        // *当做）
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
            // 任何时候如果openMax小于0，表示即使所有的*全部当做（，也比）少，即）在（前没有配对
            if (openMax < 0) {
                return false;
            }
            // 任何时候如果openMin小于0就设置成0，因为*可以当“”和（
            openMin = Math.max(openMin, 0);
        }
        return openMin == 0;
    }
}
