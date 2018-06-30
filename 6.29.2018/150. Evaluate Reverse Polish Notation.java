// pay attention to .equals() and == in String comparison.
// == check physical address, .equals() check same string.

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
