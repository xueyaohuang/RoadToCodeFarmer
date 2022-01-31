
/*
The application for this implementation is to separate read & write of a queue in multi-processing.
One of the stack is for read, and another is for write. They only interfere each other when the former
one is full or latter is empty. When there's only one thread doing the read/write operation to the stack,
there will always one stack empty. However, in a multi-thread application, if we have only one queue,
for thread-safety, either read or write will lock the whole queue. In the two stack implementation, as
long as the second stack is not empty, push operation will not lock the stack for pop.
*/
class MyQueue {
    
    // 一个用来接收push的新元素，一个用来pop和peek，只有当out为空时，才从in把元素都转到out
    Deque<Integer> in;
    Deque<Integer> out;

    public MyQueue() {
        in = new ArrayDeque<>();
        out = new ArrayDeque<>();
    }
    
    public void push(int x) {
        in.push(x);
    }
    
    public int pop() {
        //往sOut里面push要注意，为了保持顺序，只有sOut是空的时候才能push新的元素
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }
    
    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.peek();
    }
    
    public boolean empty() {
        return in.isEmpty() &&  out.isEmpty();
    }
}

class MyQueue {
    
    Deque<Integer> stack1;
    Deque<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(x);
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
