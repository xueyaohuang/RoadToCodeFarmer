// 11.9
// 这种带括号，前向运算的题，套路是用若干个stack，遇到'('把暂时结果push进stack，遇到')'把结果pop出来，与()里面的结果结合

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0, sign = 1;
        int len = s.length(), idx = 0;
        
        while (idx < len) {
            char c = s.charAt(idx);
            if (c >= '0' && c <= '9') {
                int num = 0;
                while (idx < len && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
                    num *= 10;
                    num += s.charAt(idx) - '0';
                    idx++;
                }
                res += sign * num;
            } else if (c == '+') {
                sign = 1;
                idx++;
            } else if (c == '-') {
                sign = -1;
                idx++;
            } else if (c == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
                idx++;
            } else if (c == ')') {
                res = res * stack.pop() + stack.pop();
                idx++;
            } else {
                idx++;
            }
        }
        return res;
    }
}

// 不用stack，变成recursion
// O(n^2)
// 比如input是 ((((((((1 + 2)))))))), there are lots of redundant parentheses.
// The recurrence would be T(n) = T(n-2) + O(n), so the time complexity is O(n^2).

class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int res = 0, sign = 1;
        int len = s.length(), idx = 0;
        
        while (idx < len) {
            char c = s.charAt(idx);
            if (c >= '0' && c <= '9') {
                int num = 0;
                while (idx < len && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
                    num *= 10;
                    num += s.charAt(idx) - '0';
                    idx++;
                }
                res += sign * num;
            } else if (c == '+') {
                sign = 1;
                idx++;
            } else if (c == '-') {
                sign = -1;
                idx++;
            } else if (c == '(') {
                int j = idx, count = 0;
                while (idx < len) {
                    if (s.charAt(idx) == '(') {
                        count++;
                    }
                    if (s.charAt(idx) == ')') {
                        count--;
                    }
                    if (count == 0) {
                        break;
                    }
                    idx++;
                }
                int temp = calculate(s.substring(j + 1, idx));
                res += sign * temp;
                idx++;
            } else {
                idx++;
            }
        }
        return res;
    }
}
