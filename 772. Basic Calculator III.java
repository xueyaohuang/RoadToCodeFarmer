// 只用一个stack，变成recursion
// O(n^2)
// 比如input是 ((((((((1 + 2)))))))), there are lots of redundant parentheses.
// The recurrence would be T(n) = T(n-2) + O(n), so the time complexity is O(n^2).
class Solution {
    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int res = 0, num = 0;
        char sign = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            
            if (c == '(') {
                int count = 0, start = i + 1;
                while (i < len) {
                    if (s.charAt(i) == '(') {
                        count++;
                    }
                    if (s.charAt(i) == ')') {
                        count--;
                    }
                    if (count == 0) {
                        break;
                    }
                    i++;
                }
                num = calculate(s.substring(start, i));
            }
            
            if (i == len - 1 || c == '+' || c == '-' || c == '*' || c == '/') {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
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


public int basicCalculatorIII(String s) {
    int l1 = 0, o1 = 1;
    int l2 = 1, o2 = 1;
        
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
            
        if (Character.isDigit(c)) {
            int num = c - '0';
                
            while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                num = num * 10 + (s.charAt(++i) - '0');
            }
            
            l2 = (o2 == 1 ? l2 * num : l2 / num);
                
        } else if (c == '(') {
            int j = i;
                
            for (int cnt = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') cnt++;
                if (s.charAt(i) == ')') cnt--;
                if (cnt == 0) break;
            }
            
            int num = basicCalculatorIII(s.substring(j + 1, i));
            
            l2 = (o2 == 1 ? l2 * num : l2 / num);
                
        } else if (c == '*' || c == '/') {
            o2 = (c == '*' ? 1 : -1);
                
        } else if (c == '+' || c == '-') {
            l1 = l1 + o1 * l2;
            o1 = (c == '+' ? 1 : -1);

            l2 = 1; o2 = 1;
        }
    }
        
    return (l1 + o1 * l2);
}

class Solution {
public int calculate(String s) {
    int l1 = 0, o1 = 1;
    int l2 = 1, o2 = 1;

    Deque<Integer> stack = new ArrayDeque<>(); // stack to simulate recursion

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (Character.isDigit(c)) {
            int num = c - '0';

            while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                num = num * 10 + (s.charAt(++i) - '0');
            }

            l2 = (o2 == 1 ? l2 * num : l2 / num);

        } else if (c == '(') {
            // First preserve current calculation status
            stack.offerFirst(l1); stack.offerFirst(o1);
            stack.offerFirst(l2); stack.offerFirst(o2);
            
            // Then reset it for next calculation
            l1 = 0; o1 = 1;
            l2 = 1; o2 = 1;

        } else if (c == ')') {
            // First preserve the result of current calculation
            int num = l1 + o1 * l2;

            // Then restore previous calculation status
            o2 = stack.poll(); l2 = stack.poll();
            o1 = stack.poll(); l1 = stack.poll();
            
            // Previous calculation status is now in effect
            l2 = (o2 == 1 ? l2 * num : l2 / num);

        } else if (c == '*' || c == '/') {
            o2 = (c == '*' ? 1 : -1);

        } else if (c == '+' || c == '-') {
            l1 = l1 + o1 * l2;
            o1 = (c == '+' ? 1 : -1);

            l2 = 1; o2 = 1;
        }
    }

    return (l1 + o1 * l2);
}
}
