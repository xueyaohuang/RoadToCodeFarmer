// pay attention to .equals() and == in String comparison.
// == check physical address, .equals() check same string.
// 用stack。遇到数字就push，遇到符号就pop两个数字出来，算出结果再push
class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            if (s.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (s.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            } else if (s.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (s.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            } else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.peek();
    }
}

class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        // 用int array mimic stack，stack里面只放数字，数字有tokens.length / 2 + 1个
        // 符号有tokens.length / 2 个
        // jvm 的virtual machine level language 就是这样实现的
        int[] stack = new int[tokens.length / 2 + 1];
        int idx = 0;
        for (String s : tokens) {
            switch (s) {
                case "+" :
                    stack[idx - 2] = stack[idx - 2] + stack[idx - 1];
                    idx--;
                    break;
                case "-" :
                    stack[idx - 2] = stack[idx - 2] - stack[idx - 1];
                    idx--;
                    break;
                case "*" :
                    stack[idx - 2] = stack[idx - 2] * stack[idx - 1];
                    idx--;
                    break;
                case "/" :
                    stack[idx - 2] = stack[idx - 2] / stack[idx - 1];
                    idx--;
                    break;
                default :
                    stack[idx] = Integer.valueOf(s);
                    idx++;
                    break;
            }
        }
        return stack[0];
    }
}
