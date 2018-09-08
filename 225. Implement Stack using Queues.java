class MyStack {
    
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {
            queue.add(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */


class MyStack {

    LinkedList<Integer> queue1 = null;
    LinkedList<Integer> queue2 = null;
    
    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new LinkedList<Integer>();
        queue2 = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        if (empty()) {
            queue1.offer(x);
        } else {
            if (queue1.size() > 0) {
                queue2.offer(x);
                for (int i = 0; i < queue1.size(); i++)
                    queue2.offer(queue1.poll());
            } else if (queue2.size() > 0) {
                queue1.offer(x);
                for (int i = 0; i < queue2.size(); i++)
                    queue1.offer(queue2.poll());
            }
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (queue1.size() > 0)
            return queue1.poll();
        else if (queue2.size() > 0)
            return queue2.poll();
        
        return -1;
    }
    
    /** Get the top element. */
    public int top() {
         if (queue1.size() > 0)
            return queue1.peek();
        else if (queue2.size() > 0)
            return queue2.peek();
        
        return -1;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        if (queue1.size() == 0 && queue2.size() == 0)
            return true;
        
        return false;
    }
}
