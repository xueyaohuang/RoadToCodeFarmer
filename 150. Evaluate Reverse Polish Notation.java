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
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num1 + num2);
            } else if (s.equals("-")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 - num1);
            } else if (s.equals("*")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num1 * num2);
            } else if (s.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            } else {
                int num = Integer.parseInt(s);
                stack.push(num);
            }
        }
        
        return stack.peek();
    }
}
