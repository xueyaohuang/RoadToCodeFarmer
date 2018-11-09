// stack solution
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int res = 0;
        int num = 0;
        
        // 最前面相当于有个加号
        char sign = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            
            // 注意数字可能是多位
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            
            // c是operator
            // 注意检查i == len - 1，最后的时候也要清算
            if (i == len - 1 || (c != ' ' && !Character.isDigit(c))) {
                // 看的是之前记录的符号
                if (sign == '+') { 
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {  // 当时*或者/的时候，需要与前面的数一起运算
                    stack.push(stack.pop() * num);
                } else {
                    stack.push(stack.pop() / num);
                }
                sign = c;
                num = 0;
            }
        }
        
        for (int i : stack) {
            res += i;
        }
        
        return res;
    }
}


// Non stack solution.
class Solution {
    public int calculate(String s) {
        int res = 0;
        int num = 0;
        int sign = 1;
        int temp = 0;
        boolean isMulSign = true; 
        boolean isPlusMinus = true;// isPlusMinus=true 表示+或-号前的数字num参与的是加法或减法比如，1+1，isPlusMinus=true表示+前的1参与加法。1*1+1，isPlusMinus=false，表示+前的1参与的是乘法或除法。
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            } else if (c == '+' || c == '-') {
                if (isPlusMinus) {
                    res += sign * num;
                } else {
                    if (isMulSign) {
                        res += sign * temp * num;
                    } else {
                        res += sign * temp / num;
                    }
                }
                num = 0;
                sign = c == '+' ? 1 : -1;
                isPlusMinus = true; // 重置isPlusMinus，比如1*3+1+1，isPlusMinus在第一个+号后重置，遇到第二个+号，isPlusMinus=true表示第二个+号前的1参与加法运算。
            } else if (c == '*' || c == '/') {
                if (isPlusMinus) {
                    temp = num;
                } else {
                    if (isMulSign) {
                        temp *= num;
                    } else {
                        temp /= num;

                    }
                }
                num = 0;
                isMulSign = c == '*';
                isPlusMinus = false; // 重置isPlusMinus，乘除法之后重置isPlusMinus显然为false。
            }
        }
        if (isPlusMinus) {
            res += sign * num;
        } else {
            if (isMulSign) {
                res += sign * temp * num;
            } else {
                res += sign * temp / num;
            }
        }
        return res;
    }
}
