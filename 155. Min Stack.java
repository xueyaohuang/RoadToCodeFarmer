class MinStack {
    
    Deque<Integer> stack;
    Deque<Integer> min;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<>();
        min = new ArrayDeque<>();
    }
    
    public void push(int x) {
        stack.push(x);
        // peek 之前要检查是否是empty
        if (min.isEmpty() || x < min.peek()) {
            min.push(x);
        } else {
            min.push(min.peek());
        }
    }
    
    public void pop() {
        stack.pop();
        min.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

class MinStack {
    
    private Deque<Integer> stack;
    private int min;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new ArrayDeque<>();
        min = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= min) {
            stack.push(min); // 注意是把之前的min push进去
            min = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}
