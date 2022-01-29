// one queue, keep elements in queue in reverse order
// push 1: 1
// push 2: 2 1
// push 3: 3 2 1
// push() is O(n), pop() and top() are O(1)
class MyStack {
    
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        // after you add one element to the queue, move the tail to be the head.
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {
            queue.offer(queue.poll());
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


// two queues
// keep one queue empty and the other one has element in order
// push() is O(1), pop() and top() are O(n)
class MyStack {
    
    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    public void push(int x) {
        if (queue2.isEmpty()) {
            queue1.offer(x);
        } else {
            queue2.offer(x);
        }
    }
    
    public int pop() {
        if (!queue1.isEmpty()) {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            return queue1.poll();
        }
        while (queue2.size() > 1) {
            queue1.offer(queue2.poll());
        }
        return queue2.poll();
    }
    
    public int top() {
        if (!queue1.isEmpty()) {
            while (queue1.size() > 1) {
                queue2.offer(queue1.poll());
            }
            int res = queue1.peek();
            queue2.offer(queue1.poll());
            return res;
        }
        while (queue2.size() > 1) {
            queue1.offer(queue2.poll());
        }
        int res = queue2.peek();
        queue1.offer(queue2.poll());
        return res;
    }
    
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}
