// pay attention to .equals() and == in String comparison.
// == check physical address, .equals() check same string.
// 用stack。遇到数字就push，遇到符号就pop两个数字出来，算出结果再push
class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        for (String str : tokens) {
            if (str.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            }
            else if (str.equals("-")) {
                stack.push(-stack.pop() + stack.pop());
            }
            else if (str.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            }
            else if (str.equals("/")) {
                stack.push((int)(1.0 / stack.pop() * stack.pop()));
            }
            else {
                stack.push(Integer.parseInt(str));
            }
        }
        return stack.pop();
    }
}
