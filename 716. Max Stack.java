class MaxStack {

    Deque<Integer> stack;
    Deque<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new ArrayDeque<>();
        maxStack = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack.push(x);

        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        } else if (x < maxStack.peek()) {
            maxStack.push(maxStack.peek());
        }
    }
    
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int peekMax() {
        return maxStack.peek();
    }
    
    public int popMax() {
        int res = maxStack.peek();
        Deque<Integer> temp = new ArrayDeque<>();
        while (stack.peek() != res) {
            temp.push(stack.pop());
            maxStack.pop();
        }
        
        stack.pop();
        maxStack.pop();
        while (!temp.isEmpty()) {
            int cur = temp.pop();
            stack.push(cur);
            if (maxStack.isEmpty() || cur >= maxStack.peek()) {
                maxStack.push(cur);
            } else if (cur < maxStack.peek()) {
                maxStack.push(maxStack.peek());
            }
        }
        return res;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
