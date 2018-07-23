// 这种带括号，前向运算的题，套路是用若干个stack，遇到'('把暂时结果push进stack，遇到')'把结果pop出来，与()里面的结果结合
class Solution {
    public int calculate(String s) {
        Stack<Integer> temp = new Stack<>(); // 记录暂时的累加结果
        Stack<Integer> mul = new Stack<>(); // 记录'('前面的符号
        mul.push(1); // 重要，默认是1，必须先加一个进去
        int idx = 0;
        int res = 0;
        int sign = 1;
        while (idx < s.length()) {
            if (s.charAt(idx) == '(') {
                temp.push(res);
                res = 0;
                sign = 1; // 注意，需要把sign reset 成1，因为之前的sign只能管到()里面的运算
                idx++; // 注意每个if里面都要idx++，否则会TLE
            }
            else if (s.charAt(idx) == '+') {
                if (s.charAt(idx + 1) == '(') { // 考虑 '+' '-'后面是否紧跟的是'('
                    mul.push(1);
                }
                else {
                    sign = 1;
                }
                idx++;
            }
            else if (s.charAt(idx) == '-') {
                if (s.charAt(idx + 1) == '(') {
                    mul.push(-1);
                }
                else {
                    sign = -1;
                }
                idx++;
            }
            else if (s.charAt(idx) == ' ') {
                idx++;
            }
            else if (Character.isDigit(s.charAt(idx))) {
                int toAdd = s.charAt(idx++) - '0';
                while (idx < s.length() && Character.isDigit(s.charAt(idx))) { // 数字可能不止1位
                    toAdd = toAdd * 10 + s.charAt(idx++) - '0'; // = 不是 +=
                }
                res += sign == 1 ? toAdd : -toAdd;       
            }
            else {
                int multipler = mul.pop();
                res *= multipler;
                res += temp.pop();
                sign = 1; // 注意，需要把sign reset 成1，因为之前的sign只能管到()里面的运算
                idx++;
            }
        }
        return res;
    }
}
