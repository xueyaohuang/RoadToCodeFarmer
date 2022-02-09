/*
总的score是最基础的单层括号()的(2^i + 2^j + 2^k + ... + 2^m ....)倍
比如 (([][])[])[]，score=1*4+1*4+1*2+1，其中的基础括号用[]表示，剩下的是倍数
所以要找出每个基础括号的倍数
用open表示当前(的个数，遇到(时增加(
遇到)的时候需要计算res，有的)组成基础括号，有的)组成倍数
很明显，组成基础括号的前面一个char一定是(，而组成倍数的)的前面一个char一定是)
*/ 
class Solution {
    public int scoreOfParentheses(String s) {
        int open = 0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                open++;
            } else {
                if (s.charAt(i - 1) != ')') {
                    res += (int)Math.pow(2, open - 1);
                }
                open--;
            }
        }
        return res;
    }
}
